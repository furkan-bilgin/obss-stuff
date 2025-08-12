import { IoHeart } from 'react-icons/io5';
import { useUserStore } from '../../state/user';
import { CiBoxList } from 'react-icons/ci';
import type { MovieDto } from '../../client';

export const MovieProps = ({ movie }: { movie: MovieDto }) => {
  const userData = useUserStore((state) => state);
  return (
    <div className="flex items-center gap-2">
      {userData.favorites?.find((fav) => fav.movie?.id === movie.id) ? (
        <span className="badge badge-error">
          <IoHeart size={20} />
        </span>
      ) : (
        ''
      )}
      {userData.watchlist?.find((watch) => watch.id === movie.id) ? (
        <span className="badge badge-primary">
          <CiBoxList size={20} />
        </span>
      ) : (
        ''
      )}
    </div>
  );
};
