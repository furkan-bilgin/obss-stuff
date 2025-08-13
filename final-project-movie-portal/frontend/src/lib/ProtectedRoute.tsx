import { Navigate } from 'react-router-dom';
import { useUserStore } from '../state/user';

export const ProtectedRoute = ({
  children,
  neededRoles,
}: {
  children: React.ReactNode;
  neededRoles?: string[];
}) => {
  const user = useUserStore((state) => state.user);
  if (!user) {
    return <Navigate to="/login" />;
  }
  if (
    neededRoles &&
    !user.roles?.some((role) => neededRoles?.includes(role.name ?? ''))
  ) {
    return <Navigate to="/user" />;
  }
  return children;
};
