import * as restClient from '../client';
import { createClient, createConfig, type Client } from '../client/client';
import { useUserStore } from '../state/user';
import {
  categoryService,
  type CategoryService,
} from './services/categoryService';
import {
  directorService,
  type DirectorService,
} from './services/directorService';
import { imdbService, type IMDBService } from './services/imdbService';
import { movieService, type MovieService } from './services/movieService';
import { userService, type UserService } from './services/userService';

interface APIClientInterface {
  client: Client;
  categoryService: CategoryService;
  directorService: DirectorService;
  movieService: MovieService;
  userService: UserService;
  imdbService: IMDBService;

  init: () => void;
  login: (username: string, password: string) => Promise<void>;
  register: (
    username: string,
    password: string,
    email: string
  ) => Promise<void>;
  logout: () => void;
  setClientToken: (token: string | null) => void;
  refreshUser: () => Promise<void>;
}

export const api: APIClientInterface = {
  client: createClient(
    createConfig({
      baseURL: 'http://127.0.0.1:8080',
      throwOnError: true,
    })
  ),
  categoryService: categoryService,
  directorService: directorService,
  movieService: movieService,
  userService: userService,
  imdbService: imdbService,
  init: () => {
    api.setClientToken(useUserStore.getState().token);
    api.refreshUser();
  },
  login: async (username: string, password: string) => {
    try {
      const loginResult = await restClient.login({
        client: api.client,
        body: { username, password },
      });
      const token = loginResult.data?.token;
      if (!token) {
        return;
      }
      useUserStore.setState({ token });
      api.setClientToken(token);
      await api.refreshUser();
    } catch (error) {
      useUserStore.setState({ user: null, token: null });
      console.error('Login failed:', error);
      throw error;
    }
  },
  register: async (username: string, password: string, email: string) => {
    try {
      await restClient.register({
        client: api.client,
        body: { username, password, email },
      });
      await api.login(username, password);
    } catch (error) {
      useUserStore.setState({ user: null, token: null });
      console.error('Register failed:', error);
      throw error;
    }
  },
  logout: () => {
    useUserStore.setState({ user: null, token: null });
    api.setClientToken(null);
  },
  setClientToken: (token: string | null) => {
    if (!token) {
      return;
    }
    api.client.setConfig({
      ...api.client.getConfig(),
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  },
  refreshUser: async () => {
    // Get user profile
    const me = await restClient.getMe({ client: api.client });
    useUserStore.setState({ user: me.data });
    // Get user watchlist
    const username = useUserStore.getState().user?.username ?? '';
    const watchlistRes = await restClient.getUserWatchlist({
      client: api.client,
      path: { username },
    });
    useUserStore.setState({ watchlist: watchlistRes.data?.watchlist });

    const favoritesRes = await restClient.getUserFavorites({
      client: api.client,
      path: { username },
    });
    useUserStore.setState({ favorites: favoritesRes.data?.favorites });
  },
};
