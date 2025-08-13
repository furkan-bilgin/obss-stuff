import {
  addToWatchlist,
  deleteUser,
  favoriteMovie,
  getAllRoles,
  getAllUsers,
  getUserById,
  getUserByUsername,
  getUserFavorites,
  getUserWatchlist,
  removeFromWatchlist,
  unfavoriteMovie,
  updateUser,
} from '../../client/sdk.gen';
import { api } from '..';
import type { MovieDto, RoleDto, UserDto } from '../../client';

export interface UserService {
  getAll: () => Promise<UserDto[]>;
  getById: (id: number) => Promise<UserDto>;
  update: (id: number, entity: UserDto) => Promise<void>;
  delete: (id: number) => Promise<void>;
  getAllRoles: () => Promise<RoleDto[]>;
  favoriteMovie: (id: number) => Promise<void>;
  unfavoriteMovie: (id: number) => Promise<void>;
  addMovieToWatchlist: (id: number) => Promise<void>;
  removeMovieFromWatchlist: (id: number) => Promise<void>;
  getByUsername: (username: string) => Promise<UserDto>;
  getWatchlistByUsername: (username: string) => Promise<MovieDto[]>;
  getFavoritesByUsername: (username: string) => Promise<MovieDto[]>;
}

export const userService = {
  getAll: async () => {
    const res = await getAllUsers({ client: api.client });
    return res.data ?? [];
  },
  getById: async (id: number) => {
    const res = await getUserById({ path: { id }, client: api.client });
    return res.data;
  },
  update: async (id: number, entity: UserDto) => {
    await updateUser({ path: { id }, body: entity, client: api.client });
  },
  delete: async (id: number) => {
    await deleteUser({ path: { id }, client: api.client });
  },
  getAllRoles: async () => {
    const res = await getAllRoles({ client: api.client });
    return res.data ?? [];
  },
  favoriteMovie: async (id: number) => {
    await favoriteMovie({
      client: api.client,
      body: { movieId: id },
    });
  },
  unfavoriteMovie: async (id: number) => {
    await unfavoriteMovie({
      client: api.client,
      body: { movieId: id },
    });
  },
  addMovieToWatchlist: async (id: number) => {
    await addToWatchlist({
      client: api.client,
      body: { movieId: id },
    });
  },
  removeMovieFromWatchlist: async (id: number) => {
    await removeFromWatchlist({
      client: api.client,
      body: { movieId: id },
    });
  },
  getByUsername: async (username: string) => {
    const res = await getUserByUsername({
      client: api.client,
      path: { username },
    });
    return res.data;
  },
  getWatchlistByUsername: async (username: string) => {
    const res = await getUserWatchlist({
      client: api.client,
      path: { username },
    });
    return res.data?.watchlist;
  },
  getFavoritesByUsername: async (username: string) => {
    const res = await getUserFavorites({
      client: api.client,
      path: { username },
    });
    return res.data?.favorites;
  },
} as UserService;
