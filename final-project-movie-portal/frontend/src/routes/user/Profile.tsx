import { useParams } from 'react-router-dom';
import type { MovieDto, UserDto } from '../../client';
import { api } from '../../api';
import { useEffect, useState } from 'react';
import { MovieCard } from '../../lib/movie/MovieCard';
import { IoHeart } from 'react-icons/io5';
import { CiBoxList } from 'react-icons/ci';
import { FaUser } from 'react-icons/fa';
import { ErrorMessage } from '../../lib/ErrorMessage';
import type { IconType } from 'react-icons';
import React from 'react';
import { LoadingSpinner } from '../../lib/LoadingSpinner';

const MovieListContainer = ({
  title,
  icon,
  movies,
}: {
  title: string;
  icon: IconType;
  movies: MovieDto[];
}) => {
  return (
    <div>
      <h2 className="text-3xl font-bold mb-6 flex items-center gap-1">
        {React.createElement(icon)}
        {title}
      </h2>
      {movies.length > 0 ? (
        <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
          {movies.map((movie: MovieDto) => (
            <MovieCard key={movie.id} movie={movie} />
          ))}
        </div>
      ) : (
        <p>No {title} found.</p>
      )}
    </div>
  );
};

export const Profile = () => {
  const { username } = useParams<{ username: string }>();
  const [error, setError] = useState<Error | null>(null);
  const [user, setUser] = useState<UserDto | null>(null);
  const [watchlist, setWatchlist] = useState<MovieDto[]>([]);
  const [favorites, setFavorites] = useState<MovieDto[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [userRes, watchlistRes, favoritesRes] = await Promise.all([
          api.userService.getByUsername(username ?? ''),
          api.userService.getWatchlistByUsername(username ?? ''),
          api.userService.getFavoritesByUsername(username ?? ''),
        ]);
        setUser(userRes);
        setWatchlist(watchlistRes);
        setFavorites(favoritesRes);
      } catch (err) {
        console.error(err);
        if (err instanceof Error) {
          setError(err);
          return;
        }
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  });

  if (error) return <ErrorMessage error={error} />;
  if (loading || !user) return <LoadingSpinner />;

  return (
    <div className="flex flex-col w-full">
      <h2 className="text-3xl mb-6 flex items-center justify-center gap-2">
        <FaUser />
        {user.username}'s Profile
      </h2>
      <div className="space-y-12 flex m-12 mt-0 flex-col items-start">
        <MovieListContainer
          title="Favorite Movies"
          icon={IoHeart}
          movies={favorites}
        />
        <MovieListContainer
          title="Watchlist"
          icon={CiBoxList}
          movies={watchlist}
        />
      </div>
    </div>
  );
};
