import { create } from 'zustand';
import type { MovieDto, UserDto } from '../client';
import { persist } from 'zustand/middleware';

export type UserState = {
  user: UserDto | null;
  token: string | null;
  watchlist: MovieDto[] | null;
  favorites: MovieDto[] | null;
};

export const useUserStore = create<UserState>()(
  persist(
    (): UserState => ({
      user: null,
      token: null,
      watchlist: null,
      favorites: null,
    }),
    {
      name: 'user-store',
    }
  )
);
