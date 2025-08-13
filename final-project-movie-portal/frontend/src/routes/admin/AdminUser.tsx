import { apiClient } from '../../api';
import type { UserDto } from '../../client/types.gen';
import { GenericCRUD, type CRUDConfig } from '../../lib/admin/GenericCRUD';
import {
  getAllUsers,
  getUserById,
  updateUser,
  deleteUser,
  getAllRoles,
} from '../../client/sdk.gen';

const userConfig: CRUDConfig<UserDto> = {
  entityName: 'User',
  initialEntity: {
    username: '',
    email: '',
    roles: [],
  },
  getAll: async () => {
    const res = await getAllUsers({
      client: apiClient.client,
    });
    return res.data ?? [];
  },
  getById: async (id: number) => {
    const res = await getUserById({
      path: { id },
      client: apiClient.client,
    });
    return res.data ?? userConfig.initialEntity;
  },
  update: async (id: number, entity: UserDto) => {
    await updateUser({
      path: { id },
      body: entity,
      client: apiClient.client,
    });
  },
  delete: async (id: number) => {
    await deleteUser({ path: { id }, client: apiClient.client });
  },
  create: async () => {}, // they should register instead
  fields: [
    { name: 'username', label: 'Username', type: 'text', required: true },
    { name: 'email', label: 'Email', type: 'email', required: true },
    {
      name: 'roles',
      label: 'Roles',
      type: 'multiselect',
      required: true,
      dataFetcher: async () => {
        const res = await getAllRoles({
          client: apiClient.client,
        });
        return res.data ?? [];
      },
    },
  ],
  tableColumns: [
    { header: 'Username', render: (user) => user.username },
    { header: 'Email', render: (user) => user.email },
    {
      header: 'Roles',
      render: (user) => user.roles?.map((role) => role.name).join(', '),
    },
  ],
};

export const AdminUser = () => <GenericCRUD config={userConfig} />;
