import { IoHeart } from 'react-icons/io5';
import { useUserStore } from '../../state/user';
import { CiBoxList } from 'react-icons/ci';
import type { MovieDto } from '../../client';
import { api } from '../../api';

export const MovieProps = ({ movie }: { movie: MovieDto }) => {
  const userData = useUserStore((state) => state);
  return (
    <div className="flex items-center gap-2">
      {userData.watchlist?.find((watch) => watch.id === movie.id) ? (
        <a
          className="badge badge-primary cursor-pointer hover:bg-transparent transition"
          onClick={async () =>
            await api.userService.removeMovieFromWatchlist(movie.id as number)
          }
        >
          <CiBoxList size={20} />
        </a>
      ) : (
        ''
      )}
      {userData.favorites?.find((fav) => fav?.id === movie.id) ? (
        <a
          className="badge badge-error cursor-pointer hover:bg-transparent transition hover:text-white"
          onClick={async () =>
            await api.userService.unfavoriteMovie(movie.id as number)
          }
        >
          <IoHeart size={20} />
        </a>
      ) : (
        ''
      )}
    </div>
  );
};
