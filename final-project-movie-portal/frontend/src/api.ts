import { getMe, login as loginApi, type LoginRequestDto } from './client';
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
    const meResult = await getMe({ client });
    if (!meResult.data) {
      useUserStore.setState({ token: null, user: null });
      throw new Error(
        'Authentication succeeded, but failed to fetch user profile.'
      );
    }

    useUserStore.setState({ user: meResult.data });
  } catch (error) {
    useUserStore.setState({ user: null, token: null });
    console.error('Login failed:', error);
    throw error;
  }
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

setClientToken(useUserStore.getState().token);
