import { api } from '../../api';
import type { CategoryDto } from '../../client/types.gen';
import { GenericCRUD, type CRUDConfig } from '../../lib/admin/GenericCRUD';

const categoryConfig: CRUDConfig<CategoryDto> = {
  entityName: 'Category',
  initialEntity: {
    name: '',
  },
  getAll: api.categoryService.getAll,
  getById: api.categoryService.getById,
  create: api.categoryService.create,
  update: api.categoryService.update,
  delete: api.categoryService.delete,
  fields: [{ name: 'name', label: 'Name', type: 'text', required: true }],
  tableColumns: [{ header: 'Name', render: (category) => category.name }],
};

export const AdminCategory = () => <GenericCRUD config={categoryConfig} />;
