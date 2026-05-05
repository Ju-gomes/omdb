import { useState, useCallback, useEffect } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import SearchBar from '../components/SearchBar';
import MovieCard from '../components/MovieCard';
import { searchMovies } from '../services/Api';

const Home = () => {
  const [results, setResults] = useState([]);
  const [totalResults, setTotalResults] = useState(0);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const [page, setPage] = useState(1);
  const [searchParamsState, setSearchParamsState] = useState({});

  const [urlParams, setUrlParams] = useSearchParams();
  const navigate = useNavigate();

  const query = urlParams.get("s");
  const pageURL = parseInt(urlParams.get("page") || "1");

  // busca principal
  const handleSearch = useCallback(async (params, newPage = 1) => {
  const searchTerm = (params?.s || '').trim();
    if (!searchTerm) return;

    setLoading(true);
    setError('');

    try {
      const data = await searchMovies({
        title: searchTerm,
        type: params.type,
        page: newPage,
      });

      setResults(data.Search || []);
      setTotalResults(parseInt(data.totalResults) || 0);
      setPage(newPage);
    } catch (err) {
      console.error(err);
      setError('Erro ao buscar filmes. Tente novamente.');
      setResults([]);
    } finally {
      setLoading(false);
    }
  }, []);
  

  //restaura busca ao voltar/entrar na página
  useEffect(() => {
    if (!query || !query.trim()) return;

    const params = { s: query };
    setSearchParamsState(params);
    handleSearch(params, pageURL);
  }, [query, pageURL, handleSearch]);

  const onSearch = (params) => {
    if(!params.s || !params.s.trim()) return;
    setSearchParamsState(params);

    setUrlParams({
      s: params.s,
      page: "1",
    });

    handleSearch(params, 1);
  };

  //paginação
  const totalPages = Math.ceil(totalResults / 10);

  const goToPage = (newPage) => {
    if (!searchParamsState.s) return;
    if (newPage < 1 || newPage > totalPages) return;

    setUrlParams({
      s: searchParamsState.s,
      page: newPage.toString(),
    });

    handleSearch(searchParamsState, newPage);
  };

  // navegação para detalhes filme
  const handleMovieClick = (id) => {
    navigate(`/movie/${id}`);
  };

  return (
    <div className="home">

        <div className="search-section">
        <SearchBar onSearch={onSearch} loading={loading} />
        </div>

        {error && <p className="error">{error}</p>}
        {loading && <p className="loading">Carregando resultados...</p>}

        <div className="movies-grid">
        {results.map(movie => (
            <MovieCard
            key={movie.imdbID}
            movie={movie}
            onClick={handleMovieClick}
            />
        ))}
        </div>

        {totalPages > 1 && (
        <div className="pagination">
            <button onClick={() => goToPage(page - 1)} disabled={page === 1}>
            ⬅ Anterior
            </button>

            <span>Página {page} de {totalPages}</span>

            <button onClick={() => goToPage(page + 1)} disabled={page === totalPages}>
            Próxima ➡
            </button>
        </div>
        )}

    </div>
    );
};

export default Home;