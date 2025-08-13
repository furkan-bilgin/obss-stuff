import { api } from '..';
import type { MovieDto } from '../../client';
import {
  createMovie,
  deleteMovie,
  getAllMovies,
  getMovieById,
  updateMovie,
} from '../../client/sdk.gen';

export interface MovieService {
  getAll: () => Promise<MovieDto[]>;
  getById: (id: number) => Promise<MovieDto>;
  create: (entity: MovieDto) => Promise<void>;
  update: (id: number, entity: MovieDto) => Promise<void>;
  delete: (id: number) => Promise<void>;
}

export const movieService = {
  getAll: async () => {
    const res = await getAllMovies({
      client: api.client,
    });
    return res.data ?? [];
  },
  getById: async (id: number) => {
    const res = await getMovieById({
      path: { id },
      client: api.client,
    });
    return res.data;
  },
  create: async (entity: MovieDto) => {
    await createMovie({ body: entity, client: api.client });
  },
  update: async (id: number, entity: MovieDto) => {
    await updateMovie({
      path: { id },
      body: entity,
      client: api.client,
    });
  },
  delete: async (id: number) => {
    await deleteMovie({ path: { id }, client: api.client });
  },
} as MovieService;
