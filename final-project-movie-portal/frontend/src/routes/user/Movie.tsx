import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { type MovieDto } from '../../client';
import { apiClient } from '../../api';
import { FaStar } from 'react-icons/fa';
import { BiCalendar, BiError, BiTime } from 'react-icons/bi';
import {
  MdLanguage,
  MdMovie,
  MdOutlinePlaylistAdd,
  MdOutlinePlaylistRemove,
} from 'react-icons/md';
import { IoHeart, IoHeartOutline } from 'react-icons/io5';
import { useUserStore } from '../../state/user';
import { MovieProps } from '../../lib/user/MovieProps';

export default function Movie() {
  const { id } = useParams<{ id: string }>();
  const [movie, setMovie] = useState<MovieDto | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const userData = useUserStore((state) => state);

  useEffect(() => {
    if (!id) {
      setError('Movie ID is missing.');
      setLoading(false);
      return;
    }

    apiClient
      .getMovieById(parseInt(id))
      .then((res) => {
        setMovie(res.data ?? null);
      })
      .catch((err) => {
        console.error(err);
        setError('Failed to fetch movie details.');
      })
      .finally(() => {
        setLoading(false);
      });
  }, [id]);

  if (loading) {
    return <span className="loading loading-spinner loading-lg"></span>;
  }

  if (error) {
    return (
      <div className="alert alert-error shadow-lg">
        <BiError size={20} />
        <span>Error! {error}</span>
      </div>
    );
  }

  if (!movie) {
    return null;
  }

  return (
    <div className="card lg:card-side bg-base-100 shadow-xl max-w-4xl">
      <figure className="p-4 sm:p-0 lg:w-1/3">
        <img
          src={movie.posterUrl}
          alt={`Poster for ${movie.title}`}
          className="rounded-xl"
        />
      </figure>
      <div className="card-body lg:w-2/3">
        <div className="flex justify-between items-center">
          <h1 className="card-title text-3xl md:text-4xl">{movie.title}</h1>
          <MovieProps movie={movie} />
        </div>
        <div className="flex flex-wrap gap-2 my-2">
          {movie.categories?.map((category) => (
            <div key={category.id} className="badge badge-outline">
              {category.name}
            </div>
          ))}
        </div>
        <p className="text-lg text-base-content/80">{movie.description}</p>
        <div className="my-4 space-y-2">
          {movie.director?.name && (
            <div className="flex items-center gap-2">
              <MdMovie className="text-xl" />
              <strong>Director:</strong> {movie.director.name}
            </div>
          )}
          <div className="flex items-center gap-2">
            <BiCalendar className="text-xl" />
            <strong>Release Date:</strong> {movie.releaseDate}
          </div>
          <div className="flex items-center gap-2">
            <BiTime className="text-xl" />
            <strong>Runtime:</strong> {movie.runtime} minutes
          </div>
          <div className="flex items-center gap-2">
            <MdLanguage className="text-xl" />
            <strong>Language:</strong> {movie.language}
          </div>
          <div className="flex items-center gap-2">
            <strong>Country:</strong> {movie.country}
          </div>
        </div>
        <div className="flex flex-wrap gap-4">
          <div className="badge badge-lg badge-warning gap-2 p-4">
            <FaStar />
            IMDb: {movie.imdbRating}
          </div>
          {movie.metacriticRating && (
            <div className="badge badge-lg badge-info gap-2 p-4">
              Metacritic: {movie.metacriticRating}
            </div>
          )}
        </div>
        <div className="card-actions justify-end mt-4">
          {userData.watchlist?.find((watch) => watch.id === movie.id) ? (
            <button
              className="btn btn-outline btn-primary"
              onClick={() =>
                apiClient.removeMovieFromWatchlist(movie.id as number)
              }
            >
              <MdOutlinePlaylistRemove />
              Remove from Watchlist
            </button>
          ) : (
            <button
              className="btn btn-primary"
              onClick={() => apiClient.addMovieToWatchlist(movie.id as number)}
            >
              <MdOutlinePlaylistAdd />
              Add to Watchlist
            </button>
          )}
          {userData.favorites?.find((fav) => fav.movie?.id === movie.id) ? (
            <button
              className="btn btn-outline btn-secondary"
              onClick={() => apiClient.unfavoriteMovie(movie.id as number)}
            >
              <IoHeart size={20} />
              Remove from Favorites
            </button>
          ) : (
            <button
              className="btn btn-secondary"
              onClick={() => apiClient.favoriteMovie(movie.id as number, 1)}
            >
              <IoHeartOutline size={20} />
              Add to Favorites
            </button>
          )}
        </div>
      </div>
    </div>
  );
}
