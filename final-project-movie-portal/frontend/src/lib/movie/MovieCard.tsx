import { FaStar } from 'react-icons/fa';
import type { MovieDto } from '../../client';
import { MovieProps } from './MovieProps';
import { Link } from 'react-router-dom';
import { IoIosArrowForward } from 'react-icons/io';
import { BiCalendar } from 'react-icons/bi';
import { MovieCategory } from './MovieCategory';

export const MovieCard = ({ movie }: { movie: MovieDto }) => (
  <div className="card bg-base-100 w-90 shadow-sm">
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
          <MovieCategory key={category.id} category={category} />
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
        {movie.metacriticRating && (
          <div className="badge badge-outline badge-secondary">
            <FaStar />
            {movie.metacriticRating}
          </div>
        )}
        <div className="badge badge-outline badge-primary">
          <BiCalendar />
          {movie.releaseDate}
        </div>
      </div>
      <p>{movie.description}</p>
      <div className="card-actions justify-end">
        <Link to={`/user/movie/${movie.id}`} className="btn btn-sm btn-primary">
          <IoIosArrowForward />
          Details
        </Link>
      </div>
    </div>
  </div>
);
