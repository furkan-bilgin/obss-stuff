import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getMovieById, type MovieDto } from '../../client';
import { client } from '../../api';
import { FaStar } from 'react-icons/fa';
import { BiCalendar, BiTime } from 'react-icons/bi';
import { MdLanguage, MdMovie } from 'react-icons/md';
import { CiBoxList } from 'react-icons/ci';
import { IoHeartOutline } from 'react-icons/io5';

export default function Movie() {
  const { id } = useParams<{ id: string }>();
  const [movie, setMovie] = useState<MovieDto | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!id) {
      setError('Movie ID is missing.');
      setLoading(false);
      return;
    }

    getMovieById({ client, path: { id: parseInt(id, 10) } })
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
    return (
      <div className="flex items-center justify-center min-h-screen bg-base-200">
        <span className="loading loading-spinner loading-lg"></span>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex items-center justify-center min-h-screen bg-base-200">
        <div className="alert alert-error shadow-lg">
          <div>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="stroke-current flex-shrink-0 h-6 w-6"
              fill="none"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
            <span>Error! {error}</span>
          </div>
        </div>
      </div>
    );
  }

  if (!movie) {
    return null;
  }

  return (
    <div className="min-h-screen bg-base-200 p-4 sm:p-8 flex justify-center items-start">
      <div className="card lg:card-side bg-base-100 shadow-xl max-w-4xl">
        <figure className="p-4 sm:p-0 lg:w-1/3">
          <img
            src={movie.posterUrl}
            alt={`Poster for ${movie.title}`}
            className="rounded-xl"
          />
        </figure>
        <div className="card-body lg:w-2/3">
          <h1 className="card-title text-3xl md:text-4xl">{movie.title}</h1>
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
            <button className="btn btn-primary">
              <CiBoxList size={20} />
              Add to Watchlist
            </button>
            <button className="btn btn-secondary">
              <IoHeartOutline size={20} />
              Add to Favorites
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
