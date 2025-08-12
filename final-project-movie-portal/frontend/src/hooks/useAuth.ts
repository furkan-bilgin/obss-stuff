import { createContext, useContext } from 'react';
import type { UserDto } from '../client';
import type { UserState } from '../lib/state/user';

export interface AuthContextType {
  user: UserDto | null;
  login: (data: UserState) => Promise<void>;
  logout: () => void;
  setUser: (data: UserDto | null) => void;
}

export const AuthContext = createContext({} as AuthContextType);

export const useAuth = () => {
  return useContext(AuthContext);
};
