import { FaStar } from 'react-icons/fa';
import type { MovieDto } from '../../client/types.gen';
import { GenericCRUD, type CRUDConfig } from '../../lib/admin/GenericCRUD';
import { api } from '../../api';
import { MovieCategory } from '../../lib/movie/MovieCategory';

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
  getAll: api.movieService.getAll,
  getById: async (id: number) => {
    return (await api.movieService.getById(id)) ?? movieConfig.initialEntity;
  },
  create: api.movieService.create,
  update: api.movieService.update,
  delete: api.movieService.delete,
  fields: [
    { name: 'title', label: 'Title', type: 'text', required: true },
    { name: 'description', label: 'Description', type: 'textarea' },
    {
      name: 'director',
      label: 'Director',
      type: 'dropdown',
      dataFetcher: api.directorService.getAll,
    },
    {
      name: 'categories',
      label: 'Categories',
      type: 'multiselect',
      dataFetcher: api.categoryService.getAll,
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
    { header: 'Director', render: (movie) => movie.director?.name ?? 'N/A' },
    {
      header: 'Categories',
      render: (movie) => (
        <div className="flex flex-wrap gap-1">
          {movie.categories?.map((category) => (
            <MovieCategory key={category.id} category={category} />
          ))}
        </div>
      ),
      className: 'w-1/4',
    },
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
