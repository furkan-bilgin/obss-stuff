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
  setClientToken: (token: string | null) => void;
}

export const authService = {
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
      authService.setClientToken(token);
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
      await authService.login(username, password);
    } catch (error) {
      useUserStore.setState({ user: null, token: null });
      console.error('Register failed:', error);
      throw error;
    }
  },
  logout: () => {
    useUserStore.setState({ user: null, token: null });
    authService.setClientToken(null);
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
};
