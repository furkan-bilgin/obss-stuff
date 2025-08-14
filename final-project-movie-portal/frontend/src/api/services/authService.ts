import { api } from '..';
import * as restClient from '../../client';
import { useUserStore } from '../../state/user';

export interface AuthService {
  login: (username: string, password: string) => Promise<void>;
  register: (
    username: string,
    password: string,
    email: string
  ) => Promise<void>;
  logout: () => void;
  setClientAccessToken: (token: string | null) => void;
  refreshAccessToken: () => Promise<void>;
}

export const authService = {
  login: async (username: string, password: string) => {
    try {
      const loginResult = await restClient.login({
        client: api.client,
        body: { username, password },
      });
      const refreshToken = loginResult.data?.refreshToken;
      if (!refreshToken) {
        return;
      }
      useUserStore.setState({ refreshToken: refreshToken });
      // get access token using our refresh token
      await authService.refreshAccessToken();
      await api.refreshUser();
    } catch (error) {
      authService.logout();
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
      await authService.login(username, password);
    } catch (error) {
      authService.logout();
      console.error('Register failed:', error);
      throw error;
    }
  },
  logout: () => {
    useUserStore.setState({
      user: null,
      refreshToken: null,
      accessToken: null,
    });
    authService.setClientAccessToken(null);
  },
  setClientAccessToken: (token: string | null) => {
    api.client.setConfig({
      ...api.client.getConfig(),
      headers: token
        ? {
            Authorization: `Bearer ${token}`,
          }
        : {
            Authorization: null,
          },
    });
  },
  refreshAccessToken: async () => {
    // Should have no access token before trying to refresh
    authService.setClientAccessToken(null);
    const token = await restClient.refreshAccessToken({
      client: api.client,
      body: { refreshToken: useUserStore.getState().refreshToken ?? '' },
    });
    useUserStore.setState({ accessToken: token.data?.accessToken });
    authService.setClientAccessToken(token.data?.accessToken ?? null);
  },
};
