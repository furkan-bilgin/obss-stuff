import { useEffect, useState } from 'react';
import { api } from '../../api';
import { useParams } from 'react-router-dom';
import type { MovieDto } from '../../client';
import { MovieCard } from '../../lib/user/MovieCard';

export const Search = () => {
  const { query } = useParams<{ query: string }>();
  const [movies, setMovies] = useState<MovieDto[]>([]);
  useEffect(() => {
    const fetchData = async () => {
      setMovies(await api.movieService.search(query ?? ''));
    };
    fetchData();
  }, [query]);
  return (
    <div className="flex flex-col items-center">
      <h2 className="text-3xl">
        Search Results for '<span className="italic">{query}</span>'
      </h2>
      <span className="mb-4 text-sm text-gray-500">
        Found {movies.length} results.
      </span>
      {movies.length > 0 ? (
        <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
          {movies.map((movie: MovieDto) => (
            <MovieCard key={movie.id} movie={movie} />
          ))}
        </div>
      ) : (
        <p>No results found.</p>
      )}
    </div>
  );
};
