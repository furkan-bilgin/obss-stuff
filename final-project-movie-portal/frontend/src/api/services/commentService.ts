import { api } from '..';
import {
  addComment,
  deleteComment,
  getCommentsByMovie,
  type CommentDto,
} from '../../client';

export interface CommentService {
  getByMovie: (id: number) => Promise<CommentDto[]>;
  create: (
    id: number,
    comment: string,
    parentCommentId?: number
  ) => Promise<void>;
  delete: (id: number) => Promise<void>;
}

export const commentService = {
  getByMovie: async (id: number) => {
    const res = await getCommentsByMovie({
      path: { movieId: id },
      client: api.client,
    });
    return res.data ?? [];
  },
  create: async (id: number, comment: string, parentCommentId?: number) => {
    await addComment({
      body: { content: comment, parentCommentId },
      path: { movieId: id },
      client: api.client,
    });
  },
  delete: async (id: number) => {
    await deleteComment({
      body: { commentId: id },
      client: api.client,
    });
  },
} as CommentService;
