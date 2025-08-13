import { api } from '..';
import { fetchAndSaveTitles, type MovieDto } from '../../client';

export interface IMDBService {
  fetchAndSaveTitles: () => Promise<MovieDto[]>;
}

export const imdbService = {
  fetchAndSaveTitles: async () => {
    const res = fetchAndSaveTitles({
      client: api.client,
    });
    return (await res).data?.titles;
  },
} as IMDBService;
