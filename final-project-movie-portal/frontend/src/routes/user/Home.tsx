import { useEffect, useState } from 'react';
import { type MovieDto } from '../../client';
import { apiClient } from '../../api';
import { FaStar } from 'react-icons/fa';
import { BiCalendar } from 'react-icons/bi';
import { IoIosArrowForward } from 'react-icons/io';
import { Link } from 'react-router-dom';
import { useUserStore } from '../../state/user';
import { IoHeart } from 'react-icons/io5';
import { CiBoxList } from 'react-icons/ci';
import { MovieProps } from '../../lib/user/MovieProps';

export const Home = () => {
  // Fetch movies from api
  const [movies, setMovies] = useState<MovieDto[]>([]);
  const [loading, setLoading] = useState(true);
  const userData = useUserStore((state) => state);
  useEffect(() => {
    apiClient
      .getAllMovies()
      .then((res) => setMovies(res.data ?? []))
      .catch((err) => console.log(err))
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return <span className="loading loading-spinner loading-lg"></span>;
  }

  return (
    <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
      {movies.map((movie) => (
        <div className="card bg-base-100 w-96 shadow-sm">
          <figure>
            <img src={movie.posterUrl} />
          </figure>
          <div className="card-body">
            <div className="flex justify-between items-center">
              <h2 className="card-title text-2xl">{movie.title}</h2>
              <MovieProps movie={movie} />
            </div>
            <div className="flex flex-wrap gap-1">
              {movie.categories?.map((category) => (
                <div key={category.id} className="badge badge-outline">
                  {category.name}
                </div>
              ))}
            </div>
            <div className="flex flex-wrap gap-1">
              {movie.director?.name && (
                <p className="text-sm">{movie.director.name}</p>
              )}
              <div className="badge badge-outline badge-accent">
                <FaStar />
                {movie.imdbRating}
              </div>
              <div className="badge badge-outline badge-primary">
                <BiCalendar />
                {movie.releaseDate}
              </div>
            </div>
            <p>
              {movie.description?.substring(0, 150)}
              {movie.description?.length ?? 0 > 150 ? '...' : ''}
            </p>
            <div className="card-actions justify-end">
              <Link
                to={`/user/movie/${movie.id}`}
                className="btn btn-sm btn-primary"
              >
                <IoIosArrowForward />
                Details
              </Link>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};
