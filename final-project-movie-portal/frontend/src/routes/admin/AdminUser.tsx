import { api } from '../../api';
import type { UserDto } from '../../client/types.gen';
import { GenericCRUD, type CRUDConfig } from '../../lib/admin/GenericCRUD';
// ...existing code...

const userConfig: CRUDConfig<UserDto> = {
  entityName: 'User',
  initialEntity: {
    username: '',
    email: '',
    roles: [],
  },
  getAll: api.userService.getAll,
  getById: async (id: number) => {
    return (await api.userService.getById(id)) ?? userConfig.initialEntity;
  },
  update: api.userService.update,
  delete: api.userService.delete,
  create: async () => {}, // they should register instead
  fields: [
    { name: 'username', label: 'Username', type: 'text', required: true },
    { name: 'email', label: 'Email', type: 'email', required: true },
    {
      name: 'roles',
      label: 'Roles',
      type: 'multiselect',
      required: true,
      dataFetcher: api.userService.getAllRoles,
    },
  ],
  tableColumns: [
    { header: 'Username', render: (user) => user.username },
    { header: 'Email', render: (user) => user.email },
    {
      header: 'Roles',
      render: (user) =>
        user.roles?.map((role) => (
          <div className="badge badge-outline me-2">{role.name}</div>
        )),
    },
  ],
  canCreate: false, // they SHOULD register
};

export const AdminUser = () => <GenericCRUD config={userConfig} />;
