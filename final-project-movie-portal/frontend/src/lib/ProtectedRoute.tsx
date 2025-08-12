import { Navigate } from 'react-router-dom';
import { useUserStore } from '../state/user';

export const ProtectedRoute = ({ children }: { children: React.ReactNode }) => {
  const user = useUserStore((state) => state.user);
  if (!user) {
    return <Navigate to="/login" />;
  }
  return children;
};
