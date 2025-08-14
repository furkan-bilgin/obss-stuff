import { useEffect, useState } from 'react';
import { type MovieDto } from '../../client';
import { api } from '../../api';
import { MovieCard } from '../../lib/movie/MovieCard';
import { ErrorMessage } from '../../lib/ErrorMessage';
import { LoadingSpinner } from '../../lib/LoadingSpinner';

export const Home = () => {
  const [error, setError] = useState<Error | null>(null);
  const [movies, setMovies] = useState<MovieDto[] | null>(null);
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    const fetchData = async () => {
      try {
        setMovies(await api.movieService.getAll());
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
  }, []);
  if (error) return <ErrorMessage error={error} />;
  if (loading) return <LoadingSpinner />;

  return (
    <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
      {Array.isArray(movies) &&
        movies.map((movie) => <MovieCard key={movie.id} movie={movie} />)}
    </div>
  );
};
