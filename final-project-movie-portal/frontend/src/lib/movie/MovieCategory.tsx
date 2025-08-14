import { Link } from 'react-router-dom';
import type { CategoryDto } from '../../client';

export const MovieCategory = ({ category }: { category: CategoryDto }) => {
  return (
    <Link
      key={category.id}
      className={`badge badge-outline cursor-click hover:badge-primary transition ${
        window.location.pathname.endsWith(`/search/${category.name}`)
          ? 'text-warning'
          : ''
      }`}
      to={`/user/search/${category.name}`}
    >
      {category.name}
    </Link>
  );
};
