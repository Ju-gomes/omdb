const MovieCard = ({ movie, onClick }) => {
  const poster =
    movie.Poster && movie.Poster !== "N/A"
      ? movie.Poster
      : "/placeholder.jpg";

  const formatType = (type) => {
    const map = {
      movie: "Filme",
      series: "Série",
      episode: "Episódio",
    };
    return map[type] || type;
  };

  return (
    <button
      className="movie-card"
      onClick={() => onClick(movie.imdbID)}
      aria-label={`Ver detalhes de ${movie.Title}`}
    >
      <div className="movie-poster">
        <img src={poster} alt={movie.Title} loading="lazy" />
      </div>

      <div className="movie-info">
        <h3 className="movie-title">{movie.Title}</h3>
        <p className="movie-meta">
          {movie.Year} • {formatType(movie.Type)}
        </p>
      </div>
    </button>
  );
};

export default MovieCard;