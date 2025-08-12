import { create } from 'zustand';
import type { UserDto } from '../../client';
import { persist } from 'zustand/middleware';

export type UserState = {
  user: UserDto | null;
  token: string | null;
};

export const useUserStore = create<UserState>()(
  persist(
    (set) => ({
      user: null,
      token: null,
    }),
    {
      name: 'user-store',
    }
  )
);
