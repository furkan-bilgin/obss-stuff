import {
  createDirector,
  deleteDirector,
  getAllDirectors,
  getDirectorById,
  updateDirector,
} from '../../client/sdk.gen';

import { api } from '..';
import type { DirectorDto } from '../../client';

export interface DirectorService {
  getAll: () => Promise<DirectorDto[]>;
  getById: (id: number) => Promise<DirectorDto>;
  create: (entity: DirectorDto) => Promise<void>;
  update: (id: number, entity: DirectorDto) => Promise<void>;
  delete: (id: number) => Promise<void>;
}

export const directorService = {
  getAll: async () => {
    const res = await getAllDirectors({
      client: api.client,
      query: { pageable: {} },
    });
    return res.data ?? [];
  },
  getById: async (id: number) => {
    const res = await getDirectorById({
      path: { id },
      client: api.client,
    });
    return res.data;
  },
  create: async (entity: DirectorDto) => {
    await createDirector({ body: entity, client: api.client });
  },
  update: async (id: number, entity: DirectorDto) => {
    await updateDirector({
      path: { id },
      body: entity,
      client: api.client,
    });
  },
  delete: async (id: number) => {
    await deleteDirector({ path: { id }, client: api.client });
  },
} as DirectorService;
