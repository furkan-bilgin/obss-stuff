import { useState } from 'react';
import { api } from '../../api';
import type { CommentDto } from '../../client';
import { BiComment } from 'react-icons/bi';

export const SocialCommentForm = ({
  movieId,
  parent,
  handleCommentAdded,
}: {
  movieId: number;
  parent?: CommentDto;
  handleCommentAdded: () => void;
}) => {
  const [comment, setComment] = useState('');
  const [commentLoading, setCommentLoading] = useState(false);

  const handleComment = async () => {
    if (!comment) return;
    try {
      setCommentLoading(true);
      await api.commentService.create(movieId, comment, parent?.id);
      setComment('');
      handleCommentAdded();
    } finally {
      setCommentLoading(false);
    }
  };
  return (
    <div className="flex justify-center mt-4 flex-col">
      <textarea
        className="w-full textarea"
        placeholder="Add a comment..."
        rows={2}
        value={comment}
        onChange={(e) => setComment(e.target.value)}
      />
      <div className="flex w-full justify-end mt-2">
        <button
          className="btn btn-primary"
          onClick={handleComment}
          disabled={commentLoading}
        >
          {commentLoading ? (
            <>
              <span className="loading loading-spinner"></span>
              Adding...
            </>
          ) : (
            <>
              <BiComment />
              Add
            </>
          )}
        </button>
      </div>
    </div>
  );
};
