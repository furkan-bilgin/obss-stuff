import { BiComment, BiTrash, BiUser } from 'react-icons/bi';
import { SocialCommentForm } from './SocialCommentForm';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import { useUserStore } from '../../state/user';
import type { CommentDto, MovieDto } from '../../client';
import { api } from '../../api';

export const SocialComment = ({
  movie,
  comment,
  handleCommentDeleted,
  isChild,
}: {
  movie: MovieDto;
  comment: CommentDto;
  handleCommentDeleted: () => void;
  isChild: boolean;
}) => {
  const [commentFormOpen, setCommentFormOpen] = useState(false);
  const userData = useUserStore((state) => state);
  const handleDelete = async () => {
    await api.commentService.delete(comment.id ?? 0);
    handleCommentDeleted();
  };
  return (
    <div
      className={`flex card w-full ${
        isChild ? 'bg-base-200' : 'bg-base-100 shadow-sm'
      }`}
      data-comment-id={comment.id}
    >
      <div className="card-body">
        <div className="flex justify-between">
          <div className="flex items-center gap-1">
            <BiUser size={20} />
            <Link
              className="card-title text-2xl cursor-pointer underline"
              to={`/user/profile/${comment.user?.username}`}
            >
              {comment.user?.username}
            </Link>
          </div>
          <div className="flex gap-2 items-center">
            <span className="text-sm text-gray-500">
              {new Date(comment.createdAt ?? '').toLocaleString('tr-TR')}
            </span>
            {(comment.user?.id === userData.user?.id ||
              userData.user?.roles?.some((role) => role.name === 'ADMIN')) && (
              <button
                className="btn btn-sm text-xl btn-error"
                onClick={handleDelete}
              >
                <BiTrash />
              </button>
            )}
            <button
              className="btn btn-sm text-xl btn-primary"
              onClick={() => setCommentFormOpen(!commentFormOpen)}
            >
              <BiComment />
            </button>
          </div>
        </div>
        <p className="text-sm">{comment.content}</p>
        {commentFormOpen && (
          <SocialCommentForm
            movieId={movie?.id ?? 0}
            parent={comment}
            handleCommentAdded={() => {
              handleCommentDeleted();
              setCommentFormOpen(false);
            }}
          />
        )}
      </div>
      {(comment.children?.length ?? 0) > 0 && (
        <div
          className={`flex flex-col gap-2 ms-8 mb-2 border-gray-200  ${
            !isChild ? 'pe-2' : 'border-s-1'
          }`}
        >
          <div className="ms-4">
            {comment.children?.map((child) => (
              <SocialComment
                key={child.id}
                movie={movie}
                comment={child}
                isChild={true}
                handleCommentDeleted={handleCommentDeleted}
              />
            ))}
          </div>
        </div>
      )}
    </div>
  );
};
