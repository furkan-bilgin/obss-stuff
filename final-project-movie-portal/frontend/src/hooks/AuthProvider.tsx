import { useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from './useAuth.tsx';
import { useUserStore, type UserState } from '../lib/state/user';
import type { UserDto } from '../client/types.gen.ts';
import { apiClient } from '../api.tsx';

// Cool stuff: https://blog.logrocket.com/authentication-react-router-v6/
export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const user = useUserStore((state) => state.user);
  const navigate = useNavigate();

  const login = async (data: UserState) => {
    useUserStore.setState(data);
    apiClient.setConfig({
      ...apiClient.getConfig(),
      headers: {
        Authorization: `Bearer ${data.token}`,
      },
    });
    navigate('/profile');
  };

  const logout = () => {
    useUserStore.setState({ user: null, token: null });
    navigate('/', { replace: true });
  };

  const setUser = (data: UserDto | null) => {
    useUserStore.setState({ user: data });
  };

  const value = useMemo(
    () => ({
      user,
      login,
      logout,
      setUser,
    }),
    [user]
  );
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
