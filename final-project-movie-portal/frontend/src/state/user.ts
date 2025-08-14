import { create } from 'zustand';
import type { MovieDto, UserDto } from '../client';
import { persist } from 'zustand/middleware';

export type UserState = {
  user: UserDto | null;
  refreshToken: string | null;
  accessToken: string | null;
  watchlist: MovieDto[] | null;
  favorites: MovieDto[] | null;
};

export const useUserStore = create<UserState>()(
  persist(
    (): UserState => ({
      user: null,
      refreshToken: null,
      accessToken: null,
      watchlist: null,
      favorites: null,
    }),
    {
      name: 'user-store',
    }
  )
);
