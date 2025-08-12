import {
  getMe,
  getUserFavorites,
  getUserWatchlist,
  login as loginApi,
  type LoginRequestDto,
} from './client';
import { createClient, createConfig } from './client/client';
import { useUserStore } from './lib/state/user';

export const client = createClient(
  createConfig({
    baseURL: 'http://127.0.0.1:8080',
  })
);

export async function login(login: LoginRequestDto) {
  try {
    const loginResult = await loginApi({
      client: client,
      body: login,
    });
    const token = loginResult.data?.token;
    if (!token) {
      return;
    }
    useUserStore.setState({ token });
    setClientToken(token);
    fetchUserData();
  } catch (error) {
    useUserStore.setState({ user: null, token: null });
    console.error('Login failed:', error);
    throw error;
  }
}

export function logout() {
  useUserStore.setState({ user: null, token: null });
  setClientToken(null);
}

function setClientToken(token: string | null) {
  if (!token) {
    return;
  }
  client.setConfig({
    ...client.getConfig(),
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}

function fetchUserData() {
  getMe({ client }).then((res) => {
    useUserStore.setState({ user: res.data });
  });
  // Get user watchlist
  getUserWatchlist({
    client,
    path: { id: useUserStore.getState().user?.id ?? 0 },
  }).then((res) => {
    useUserStore.setState({ watchlist: res.data?.watchlist });
  });
  // Get user favorites
  getUserFavorites({
    client,
    path: { id: useUserStore.getState().user?.id ?? 0 },
  }).then((res) => {
    useUserStore.setState({ favorites: res.data?.favorites });
  });
}

function init() {
  setClientToken(useUserStore.getState().token);
  fetchUserData();
}

init();
