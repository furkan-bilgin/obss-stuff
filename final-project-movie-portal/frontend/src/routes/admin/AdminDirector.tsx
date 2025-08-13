import { api } from '../../api';
import type { DirectorDto } from '../../client/types.gen';
import { GenericCRUD, type CRUDConfig } from '../../lib/admin/GenericCRUD';

const directorConfig: CRUDConfig<DirectorDto> = {
  entityName: 'Director',
  initialEntity: {
    name: '',
  },
  getAll: api.directorService.getAll,
  getById: api.directorService.getById,
  create: api.directorService.create,
  update: api.directorService.update,
  delete: api.directorService.delete,
  fields: [{ name: 'name', label: 'Name', type: 'text', required: true }],
  tableColumns: [{ header: 'Name', render: (director) => director.name }],
};

export const AdminDirector = () => <GenericCRUD config={directorConfig} />;
