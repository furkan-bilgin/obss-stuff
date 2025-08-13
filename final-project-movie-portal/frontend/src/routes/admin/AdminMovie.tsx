import { FaStar } from 'react-icons/fa';
import { apiClient } from '../../api';
import {
  getAllMovies,
  getMovieById,
  createMovie,
  updateMovie,
  deleteMovie,
  getAllDirectors,
} from '../../client/sdk.gen';
import type { MovieDto } from '../../client/types.gen';
import { GenericCRUD, type CRUDConfig } from '../../lib/admin/GenericCRUD';

const movieConfig: CRUDConfig<MovieDto> = {
  entityName: 'Movie',
  initialEntity: {
    title: '',
    description: '',
    director: undefined,
    categories: [],
    releaseDate: '',
    language: '',
    country: '',
    posterUrl: '',
    metacriticRating: undefined,
    imdbRating: undefined,
    runtime: 0,
  },
  getAll: async () => {
    const res = await getAllMovies({ client: apiClient.client });
    return res.data ?? [];
  },
  getById: async (id: number) => {
    const res = await getMovieById({ path: { id }, client: apiClient.client });
    return res.data ?? movieConfig.initialEntity;
  },
  create: async (entity: MovieDto) => {
    await createMovie({ body: entity, client: apiClient.client });
  },
  update: async (id: number, entity: MovieDto) => {
    await updateMovie({ path: { id }, body: entity, client: apiClient.client });
  },
  delete: async (id: number) => {
    await deleteMovie({ path: { id }, client: apiClient.client });
  },
  fields: [
    { name: 'title', label: 'Title', type: 'text', required: true },
    { name: 'description', label: 'Description', type: 'textarea' },
    {
      name: 'director',
      label: 'Director',
      type: 'dropdown',
      dataFetcher: async () =>
        (
          await getAllDirectors({
            client: apiClient.client,
            query: { pageable: {} },
          })
        ).data,
    },
    { name: 'releaseDate', label: 'Release Date', type: 'text' },
    { name: 'language', label: 'Language', type: 'text' },
    { name: 'country', label: 'Country', type: 'text' },
    { name: 'posterUrl', label: 'Poster URL', type: 'text' },
    { name: 'metacriticRating', label: 'Metacritic Rating', type: 'number' },
    { name: 'imdbRating', label: 'IMDB Rating', type: 'number' },
    { name: 'runtime', label: 'Runtime (min)', type: 'number' },
  ],
  tableColumns: [
    { header: 'Title', render: (movie) => movie.title },
    { header: 'Director', render: (movie) => movie.director?.name },
    { header: 'Release Date', render: (movie) => movie.releaseDate },
    {
      header: 'Poster URL',
      render: (movie) => <img src={movie.posterUrl} className="w-20" />,
    },
    {
      header: 'IMDb Rating',
      render: (movie) => (
        <div className="flex items-center gap-2">
          <FaStar />
          {movie.imdbRating}
        </div>
      ),
    },
  ],
};

export const AdminMovie = () => <GenericCRUD config={movieConfig} />;
