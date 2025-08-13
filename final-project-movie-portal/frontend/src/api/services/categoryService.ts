import {
  createCategory,
  deleteCategory,
  getAllCategories,
  getCategoryById,
  updateCategory,
} from '../../client/sdk.gen';
import { api } from '..';
import type { CategoryDto } from '../../client';

export interface CategoryService {
  getAll: () => Promise<CategoryDto[]>;
  getById: (id: number) => Promise<CategoryDto>;
  create: (entity: CategoryDto) => Promise<void>;
  update: (id: number, entity: CategoryDto) => Promise<void>;
  delete: (id: number) => Promise<void>;
}

export const categoryService = {
  getAll: async () => {
    const res = await getAllCategories({
      client: api.client,
      query: { pageable: {} },
    });
    return res.data ?? [];
  },
  getById: async (id: number) => {
    const res = await getCategoryById({
      path: { id },
      client: api.client,
    });
    return res.data;
  },
  create: async (entity: CategoryDto) => {
    await createCategory({ body: entity, client: api.client });
  },
  update: async (id: number, entity: CategoryDto) => {
    await updateCategory({
      path: { id },
      body: entity,
      client: api.client,
    });
  },
  delete: async (id: number) => {
    await deleteCategory({ path: { id }, client: api.client });
  },
} as CategoryService;
