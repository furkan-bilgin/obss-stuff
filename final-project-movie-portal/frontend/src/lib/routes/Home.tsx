import { useEffect, useState } from 'react';
import { getAllMovies, type MovieDto } from '../../client';
import { client } from '../../api';

const dummyData: MovieDto[] = [
  {
    title: 'The Grudge',
    description:
      'The Grudge is a 2020 American psychological supernatural horror film written and directed by Nicolas Pesce. Originally announced as a reboot of the 2004 American remake and the original 2002 Japanese horror film Ju-On: The Grudge, the film ended up taking place before and during the events of the 2004 film and its two direct sequels, and is the fourth installment in the American The Grudge film series. The film stars Andrea Riseborough, Demián Bichir, John Cho, Betty Gilpin, Lin Shaye, and Jacki Weaver, and follows a police officer who investigates several murders that are seemingly connected to a single house.',
    director: {
      name: 'Nicolas Pesce',
    },
    releaseDate: '2020',
    genre: 'Horror, Supernatural',
    posterUrl:
      'https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg',
  },
  {
    title: 'Underwater',
    description:
      'Underwater is a 2020 American science fiction action horror film directed by William Eubank. The film stars Kristen Stewart, Vincent Cassel, Jessica Henwick, John Gallagher Jr., Mamoudou Athie, and T.J. Miller.',
    director: {
      name: 'William Eubank',
    },
    releaseDate: '2020',
    genre: 'Action, Horror, Science Fiction',
    posterUrl:
      'https://upload.wikimedia.org/wikipedia/en/4/4a/Underwater_poster.jpeg',
  },
  {
    title: 'Like a Boss',
    description:
      'Like a Boss is a 2020 American comedy film directed by Miguel Arteta, written by Sam Pitman and Adam Cole-Kelly, and starring Tiffany Haddish, Rose Byrne, and Salma Hayek. The plot follows two friends who attempt to take back control of their cosmetics company from an industry titan.',
    director: {
      name: 'Miguel Arteta',
    },
    releaseDate: '2020',
    genre: 'Comedy',
    posterUrl:
      'https://upload.wikimedia.org/wikipedia/en/9/9a/LikeaBossPoster.jpg',
  },
  {
    title: 'Three Christs',
    description:
      "Three Christs, also known as State of Mind, is a 2017 American drama film directed, co-produced, and co-written by Jon Avnet and based on Milton Rokeach's nonfiction book The Three Christs of Ypsilanti. It screened in the Gala Presentations section at the 2017 Toronto International Film Festival. The film is also known as: Three Christs of Ypsilanti, The Three Christs of Ypsilanti, Three Christs of Santa Monica, and The Three Christs of Santa Monica.",
    director: {
      name: 'Jon Avnet',
    },
    releaseDate: '2020',
    genre: 'Drama',
    posterUrl:
      'https://upload.wikimedia.org/wikipedia/en/a/a1/Three_Christs_poster.jpg',
  },
  {
    title: 'Inherit the Viper',
    description:
      'Inherit the Viper is a 2019 American crime drama film directed by Anthony Jerjen, in his feature directorial debut, from a screenplay by Andrew Crabtree. It stars Josh Hartnett, Margarita Levieva, Chandler Riggs, Bruce Dern, Valorie Curry, Owen Teague, and Dash Mihok.',
    director: {
      name: 'Anthony Jerjen',
    },
    releaseDate: '2020',
    genre: 'Crime, Drama',
    posterUrl:
      'https://upload.wikimedia.org/wikipedia/en/1/1c/Inherit_the_Viper_%282019%29_Film_Poster.jpg',
  },
  {
    title: 'The Sonata',
    description:
      'The Sonata is a 2018 mystery thriller film, directed by Andrew Desmond, from a screenplay by Desmond and Arthur Morin. It stars Freya Tingley, Simon Abkarian, James Faulkner, Rutger Hauer, Matt Barber and James Kermack. It was released in the United States on January 10, 2020, by Screen Media Films. It grossed $146,595 at the box office and received mixed reviews from critics.',
    director: {
      name: 'Andrew Desmond',
    },
    releaseDate: '2020',
    genre: 'Mystery, Thriller',
    posterUrl:
      'https://upload.wikimedia.org/wikipedia/en/1/13/The_Sonata_%282018%29_Film_Poster.jpg',
  },
  {
    title: 'The Murder of Nicole Brown Simpson',
    description:
      "The Murder of Nicole Brown Simpson is a 2019 American crime horror film directed by Daniel Farrands. The film is loosely based on the murder of Nicole Brown Simpson, presenting a version of events in which Brown Simpson is murdered by serial killer Glen Edward Rogers, and not by O. J. Simpson, her ex-husband and the primary suspect in the case. Though Mena Suvari's performance as Nicole Brown was praised, the film was panned by critics.",
    director: {
      name: 'Daniel Farrands',
    },
    releaseDate: '2020',
    genre: 'Crime, Horror',
    posterUrl:
      'https://upload.wikimedia.org/wikipedia/en/e/ed/The_Murder_of_Nicole_Brown_Simpson_poster.jpg',
  },
  {
    title: 'Bad Boys for Life',
    description:
      "Bad Boys for Life is a 2020 American buddy cop action comedy film directed by Adil & Bilall. It is the sequel to Bad Boys II (2003) and the third installment in the Bad Boys franchise. Will Smith, Martin Lawrence, Joe Pantoliano and Theresa Randle reprise their roles in the film and are joined by Paola Núñez, Vanessa Hudgens, Jacob Scipio, Alexander Ludwig, Charles Melton, Kate del Castillo and Nicky Jam. The film was produced by Smith, Jerry Bruckheimer, and Doug Belgrad, with a screenplay written by Chris Bremner, Peter Craig and Joe Carnahan. In Bad Boys for Life, Miami detectives Mike Lowrey and Marcus Burnett investigate a string of murders tied to Lowrey's troubled past.",
    director: {
      name: 'Adil & Bilall',
    },
    releaseDate: '2020',
    genre: 'Action, Comedy',
    posterUrl:
      'https://upload.wikimedia.org/wikipedia/en/9/90/Bad_Boys_for_Life_poster.jpg',
  },
];

export default function Home() {
  // Fetch movies from api
  const [movies, setMovies] = useState<MovieDto[]>([]);
  const [loading, setLoading] = useState(false);
  /*useEffect(() => {
    getAllMovies({ client })
      .then((res) => setMovies(res.data ?? []))
      .catch((err) => console.log(err))
      .finally(() => setLoading(false));
  }, []);*/

  useEffect(() => setMovies(dummyData), []);

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-base-200">
      {loading ? (
        <div className="w-full max-w-sm p-8 shadow-lg rounded-lg bg-base-100">
          <span className="loading loading-spinner loading-lg"></span>
        </div>
      ) : (
        ''
      )}
      <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3 mt-2">
        {movies.map((movie) => (
          <div className="card bg-base-100 w-96 shadow-sm">
            <figure>
              <img src={movie.posterUrl} />
            </figure>
            <div className="card-body">
              <h2 className="card-title">{movie.title}</h2>
              <p>
                {movie.description?.substring(0, 150)}
                {movie.description?.length ?? 0 > 150 ? '...' : ''}
              </p>
              <div className="card-actions justify-end">
                <button className="btn btn-primary">Details</button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
