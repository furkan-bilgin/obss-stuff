import { apiClient } from '../../api';
import type { CategoryDto } from '../../client/types.gen';
import { GenericCRUD, type CRUDConfig } from '../../lib/admin/GenericCRUD';

import {
  getAllCategories,
  getCategoryById,
  createCategory,
  updateCategory,
  deleteCategory,
} from '../../client/sdk.gen';

const categoryConfig: CRUDConfig<CategoryDto> = {
  entityName: 'Category',
  initialEntity: {
    name: '',
  },
  getAll: async () => {
    const res = await getAllCategories({
      client: apiClient.client,
      query: { pageable: {} },
    });
    return res.data ?? [];
  },
  getById: async (id: number) => {
    const res = await getCategoryById({
      path: { id },
      client: apiClient.client,
    });
    return res.data ?? categoryConfig.initialEntity;
  },
  create: async (entity: CategoryDto) => {
    await createCategory({ body: entity, client: apiClient.client });
  },
  update: async (id: number, entity: CategoryDto) => {
    await updateCategory({
      path: { id },
      body: entity,
      client: apiClient.client,
    });
  },
  delete: async (id: number) => {
    await deleteCategory({ path: { id }, client: apiClient.client });
  },
  fields: [{ name: 'name', label: 'Name', type: 'text', required: true }],
  tableColumns: [{ header: 'Name', render: (category) => category.name }],
};

export const AdminCategory = () => <GenericCRUD config={categoryConfig} />;
