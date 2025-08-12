import * as restClient from './client';
import { createClient, createConfig, type Client } from './client/client';
import { useUserStore } from './state/user';

class APIClient {
  client: Client;
  constructor() {
    this.client = createClient(
      createConfig({
        baseURL: 'http://127.0.0.1:8080',
      })
    );
  }

  async login(username: string, password: string) {
    try {
      const loginResult = await restClient.login({
        client: this.client,
        body: { username, password },
      });
      const token = loginResult.data?.token;
      if (!token) {
        return;
      }
      useUserStore.setState({ token });
      this.setClientToken(token);
      this.fetchUserData();
    } catch (error) {
      useUserStore.setState({ user: null, token: null });
      console.error('Login failed:', error);
      throw error;
    }
  }

  logout() {
    useUserStore.setState({ user: null, token: null });
    this.setClientToken(null);
  }

  setClientToken(token: string | null) {
    if (!token) {
      return;
    }
    this.client.setConfig({
      ...this.client.getConfig(),
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  fetchUserData() {
    restClient.getMe({ client: this.client }).then((res) => {
      useUserStore.setState({ user: res.data });
    });
    // Get user watchlist
    restClient
      .getUserWatchlist({
        client: this.client,
        path: { id: useUserStore.getState().user?.id ?? 0 },
      })
      .then((res) => {
        useUserStore.setState({ watchlist: res.data?.watchlist });
      });
    // Get user favorites
    restClient
      .getUserFavorites({
        client: this.client,
        path: { id: useUserStore.getState().user?.id ?? 0 },
      })
      .then((res) => {
        useUserStore.setState({ favorites: res.data?.favorites });
      });
  }

  // TODO: Move crud stuff to a different class
  getAllMovies() {
    return restClient.getAllMovies({ client: this.client });
  }

  getMovieById(id: number) {
    return restClient.getMovieById({ client: this.client, path: { id } });
  }

  favoriteMovie(id: number, score: number) {
    return this.initAfter(
      restClient.favoriteMovie({
        client: this.client,
        body: { movieId: id, score },
      })
    );
  }

  unfavoriteMovie(id: number) {
    return this.initAfter(
      restClient.unfavoriteMovie({
        client: this.client,
        body: { movieId: id },
      })
    );
  }

  addMovieToWatchlist(id: number) {
    return this.initAfter(
      restClient.addToWatchlist({
        client: this.client,
        body: { movieId: id },
      })
    );
  }

  removeMovieFromWatchlist(id: number) {
    return this.initAfter(
      restClient.removeFromWatchlist({
        client: this.client,
        body: { movieId: id },
      })
    );
  }

  init() {
    this.setClientToken(useUserStore.getState().token);
    this.fetchUserData();
  }

  initAfter<T>(promise: Promise<T>): Promise<T> {
    return promise.finally(() => this.init());
  }
}

export const apiClient = new APIClient();
apiClient.init();
