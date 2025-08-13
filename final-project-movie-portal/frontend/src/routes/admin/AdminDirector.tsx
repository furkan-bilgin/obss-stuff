import { apiClient } from '../../api';
import type { DirectorDto } from '../../client/types.gen';
import { GenericCRUD, type CRUDConfig } from '../../lib/admin/GenericCRUD';

import {
  getAllDirectors,
  getDirectorById,
  createDirector,
  updateDirector,
  deleteDirector,
} from '../../client/sdk.gen';

const directorConfig: CRUDConfig<DirectorDto> = {
  entityName: 'Director',
  initialEntity: {
    name: '',
  },
  getAll: async () => {
    const res = await getAllDirectors({
      client: apiClient.client,
      query: { pageable: {} },
    });
    return res.data ?? [];
  },
  getById: async (id: number) => {
    const res = await getDirectorById({
      path: { id },
      client: apiClient.client,
    });
    return res.data ?? directorConfig.initialEntity;
  },
  create: async (entity: DirectorDto) => {
    await createDirector({ body: entity, client: apiClient.client });
  },
  update: async (id: number, entity: DirectorDto) => {
    await updateDirector({
      path: { id },
      body: entity,
      client: apiClient.client,
    });
  },
  delete: async (id: number) => {
    await deleteDirector({ path: { id }, client: apiClient.client });
  },
  fields: [{ name: 'name', label: 'Name', type: 'text', required: true }],
  tableColumns: [{ header: 'Name', render: (director) => director.name }],
};

export const AdminDirector = () => <GenericCRUD config={directorConfig} />;
