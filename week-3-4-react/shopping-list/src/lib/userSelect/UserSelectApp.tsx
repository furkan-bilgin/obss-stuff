import { useState } from 'react';

interface User {
  name: string;
  email: string;
}

const users: User[] = [
  { name: 'Furkan', email: 'info@furkanbilgin.net' },
  { name: 'Mehmet', email: 'mehmet@mehmet.com' },
  { name: 'Ali', email: 'ali@ali.com' },
];

export function UserSelectApp() {
  const [user, setUser] = useState<User | null>(null);
  return (
    <div>
      <h1>Select a User</h1>
      <select
        value={user?.name}
        onChange={(e) =>
          setUser(users.find((u) => u.name === e.target.value) as User)
        }
      >
        <option value="">Select a user</option>
        {users.map((u) => (
          <option key={u.name} value={u.name}>
            {u.name}
          </option>
        ))}
      </select>
      <div>
        {user ? (
          <>
            <h2>User Details</h2>
            <dl style={{ textAlign: 'start' }}>
              <dt>Name</dt>
              <dd>{user.name}</dd>
              <dt>Email</dt>
              <dd>{user.email}</dd>
            </dl>
          </>
        ) : (
          'No user selected'
        )}
      </div>
    </div>
  );
}
