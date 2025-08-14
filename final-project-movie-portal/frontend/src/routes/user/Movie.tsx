import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { type CommentDto, type MovieDto } from '../../client';
import { api } from '../../api';
import { FaStar } from 'react-icons/fa';
import { BiCalendar, BiTime } from 'react-icons/bi';
import {
  MdLanguage,
  MdLocationCity,
  MdMovie,
  MdOutlinePlaylistAdd,
  MdOutlinePlaylistRemove,
} from 'react-icons/md';
import { IoHeart, IoHeartOutline } from 'react-icons/io5';
import { useUserStore } from '../../state/user';
import { MovieProps } from '../../lib/movie/MovieProps';
import { LoadingSpinner } from '../../lib/LoadingSpinner';
import { ErrorMessage } from '../../lib/ErrorMessage';
import { SocialComment } from '../../lib/social/SocialComment';
import { SocialCommentForm } from '../../lib/social/SocialCommentForm';
import { MovieCategory } from '../../lib/movie/MovieCategory';

export const Movie = () => {
  const { id } = useParams<{ id: string }>();
  const [movie, setMovie] = useState<MovieDto | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  const [comments, setComments] = useState<CommentDto[] | null>(null);
  const userData = useUserStore((state) => state);

  const fetchComments = async () => {
    const comments = await api.commentService.getByMovie(parseInt(id ?? ''));
    return comments;
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (!id) throw new Error('Movie ID is missing.');
        setMovie(await api.movieService.getById(parseInt(id)));
        setComments(await fetchComments());
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
  }, [id]);

  if (error) return <ErrorMessage error={error} />;
  if (loading || !movie) return <LoadingSpinner />;

  return (
    <div>
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
              <MovieCategory key={category.id} category={category} />
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
              <strong>Language:</strong> {movie.language || 'N/A'}
            </div>
            <div className="flex items-center gap-2">
              <MdLocationCity className="text-xl" />
              <strong>Country:</strong> {movie.country || 'N/A'}
            </div>
          </div>
          <div className="flex flex-wrap gap-4">
            <div className="badge badge-lg badge-warning gap-2 p-4">
              <FaStar />
              IMDb: {movie.imdbRating}
            </div>
            {movie.metacriticRating && (
              <div className="badge badge-lg badge-info gap-2 p-4">
                <FaStar />
                Metacritic: {movie.metacriticRating}
              </div>
            )}
          </div>
          <div className="card-actions justify-end mt-4">
            {userData.watchlist?.find((watch) => watch.id === movie.id) ? (
              <button
                className="btn btn-outline btn-primary"
                onClick={async () =>
                  await api.userService.removeMovieFromWatchlist(
                    movie.id as number
                  )
                }
              >
                <MdOutlinePlaylistRemove />
                Remove from Watchlist
              </button>
            ) : (
              <button
                className="btn btn-primary"
                onClick={async () =>
                  await api.userService.addMovieToWatchlist(movie.id as number)
                }
              >
                <MdOutlinePlaylistAdd />
                Add to Watchlist
              </button>
            )}
            {userData.favorites?.find((fav) => fav?.id === movie.id) ? (
              <button
                className="btn btn-outline btn-secondary"
                onClick={async () =>
                  api.userService.unfavoriteMovie(movie.id as number)
                }
              >
                <IoHeart size={20} />
                Remove from Favorites
              </button>
            ) : (
              <button
                className="btn btn-secondary"
                onClick={async () =>
                  await api.userService.favoriteMovie(movie.id as number)
                }
              >
                <IoHeartOutline size={20} />
                Add to Favorites
              </button>
            )}
          </div>
        </div>
      </div>
      <SocialCommentForm
        movieId={parseInt(id ?? '')}
        handleCommentAdded={async () => {
          setComments(await fetchComments());
        }}
      />
      <hr className="my-4 border-gray-600" />
      {comments && (
        <div className="mb-8">
          <h2 className="text-2xl font-bold mb-2">
            {comments
              ? comments.length +
                comments.reduce(
                  (acc, cur) => acc + (cur.children?.length ?? 0),
                  0
                )
              : '-'}{' '}
            Comments
          </h2>
          <div className="flex flex-col gap-2">
            {!Array.isArray(comments) && <LoadingSpinner />}
            {Array.isArray(comments) && !comments.length && (
              <p className="italic">Be the first to comment!</p>
            )}
            {comments.map((comment) => (
              <SocialComment
                key={comment.id}
                movie={movie}
                comment={comment}
                handleCommentDeleted={async () =>
                  setComments(await fetchComments())
                }
                isChild={false}
              />
            ))}
          </div>
        </div>
      )}
    </div>
  );
};
