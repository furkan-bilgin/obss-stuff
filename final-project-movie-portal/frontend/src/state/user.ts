import { create } from 'zustand';
import type { MovieDto, MovieScorePairDto, UserDto } from '../../client';
import { persist } from 'zustand/middleware';

export type UserState = {
  user: UserDto | null;
  token: string | null;
  watchlist: MovieDto[] | null;
  favorites: MovieScorePairDto[] | null;
};

export const useUserStore = create<UserState>()(
  persist(
    (set) => ({
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
