import { useState } from 'react';
import { MovieCard } from '../../lib/movie/MovieCard';
import type { MovieDto } from '../../client';
import { api } from '../../api';
import { ErrorMessage } from '../../lib/ErrorMessage';
import { TiTick } from 'react-icons/ti';
import { LuImport } from 'react-icons/lu';

export const AdminFetchIMDb = () => {
  const [movies, setMovies] = useState<MovieDto[] | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<Error | null>(null);

  const handleFetch = async () => {
    setLoading(true);
    setError(null);
    try {
      setMovies(await api.imdbService.fetchAndSaveTitles());
    } catch (err) {
      if (err instanceof Error) {
        setError(err);
      }
    } finally {
      setLoading(false);
    }
  };
  if (error) return <ErrorMessage error={error} />;

  return (
    <>
      <h1 className="text-2xl font-bold mb-4">IMDB Title Fetcher</h1>
      <button
        className="btn btn-primary mb-4"
        onClick={handleFetch}
        disabled={loading}
      >
        {loading ? (
          <>
            <span className="loading loading-spinner"></span>
            Fetching...
          </>
        ) : (
          <>
            <LuImport size={16} />
            Fetch IMDB Titles
          </>
        )}
      </button>
      {error && <div className="text-red-500 mb-4">{error}</div>}
      {movies && (
        <div className="alert alert-success shadow-lg mb-2 font-bold gap-1">
          <TiTick size={20} />
          Created and/or updated {movies.length} titles.
        </div>
      )}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {movies &&
          movies.map((movie) => <MovieCard key={movie.id} movie={movie} />)}
      </div>
    </>
  );
};
