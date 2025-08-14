import * as restClient from '../client';
import { createClient, createConfig, type Client } from '../client/client';
import { useUserStore } from '../state/user';
import { authService, type AuthService } from './services/authService';
import {
  categoryService,
  type CategoryService,
} from './services/categoryService';
import { commentService, type CommentService } from './services/commentService';
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
  commentService: CommentService;
  authService: AuthService;

  init: () => void;
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
  commentService: commentService,
  authService: authService,
  init: () => {
    api.authService.setClientToken(useUserStore.getState().token);
    api.refreshUser();
  },
  refreshUser: async () => {
    // Get user profile
    const me = await userService.getMe();
    useUserStore.setState({ user: me });

    // Get user watchlist
    const username = me.username ?? '';
    const watchlistRes = await restClient.getUserWatchlist({
      client: api.client,
      path: { username },
    });
    useUserStore.setState({ watchlist: watchlistRes.data?.watchlist });

    // Get user favorites
    const favoritesRes = await restClient.getUserFavorites({
      client: api.client,
      path: { username },
    });
    useUserStore.setState({ favorites: favoritesRes.data?.favorites });
  },
};
