import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { getMovieById } from '../services/Api';

const MovieDetails = () => {
  const { id } = useParams();
  const [movie, setMovie] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchMovie = async () => {
      try {
        const data = await getMovieById(id);
        setMovie(data);
      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false);
      }
    };

    fetchMovie();
  }, [id]);

  if (loading) return <p>Carregando detalhes...</p>;
  if (!movie) return <p>Filme não encontrado.</p>;

  return (
    <div className="movie-details">
      <h1>{movie.Title}</h1>
      <img src={movie.Poster} alt={movie.Title} />
      <p><strong>ID:</strong> {movie.imdbID}</p>
      <p><strong>Ano:</strong> {movie.Year}</p>
      <p><strong>Gênero:</strong> {movie.Genre}</p>
      <p><strong>Diretor:</strong> {movie.Director}</p>
      <p><strong>Plot:</strong> {movie.Plot}</p>
      <p><strong>IMDB Rating:</strong> {movie.imdbRating}</p>
      <button className="back-button" onClick={() => navigate(-1)}>      
        ← Voltar
      </button>

    </div>
  );
};

export default MovieDetails;