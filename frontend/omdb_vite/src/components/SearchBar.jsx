import { useState, useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';

const SearchBar = ({ onSearch, loading }) => {
  const [searchParams] = useSearchParams();

  const [title, setTitle] = useState('');
  const [year, setYear] = useState('');
  const [type, setType] = useState('');

  // Preenche campos ao voltar
  useEffect(() => {
    const s = searchParams.get('s') || '';
    const yearParam = searchParams.get('year') || '';
    const typeParam = searchParams.get('type') || '';

    setTitle(s);
    setYear(yearParam);
    setType(typeParam);
  }, [searchParams]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!title.trim()) return;

    onSearch({ 
      s: title.trim(), 
      year: year || undefined,
      type: type || undefined,
    });
  };

  return (    
    <div className="search-container">           
      <p className="search-subtitle">
        Pesquise por título para encontrar sua obra favorita!
      </p>
    
      <form onSubmit={handleSubmit} className="search-bar">
        <input
          type="text"
          placeholder="Buscar filmes ou séries..."
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        
        <input
          type="number"
          placeholder="Ano"
          value={year}
          onChange={(e) => setYear(e.target.value)}
          min="1900"
          max="2026"
        />

        <select value={type} onChange={(e) => setType(e.target.value)}>
          <option value="">Todos</option>
          <option value="movie">Filmes</option>
          <option value="series">Séries</option>
          <option value="episode">Episódios</option>
        </select>

        <button type="submit" disabled={loading}>
          {loading ? 'Buscando...' : '🔍 Buscar'}
        </button>
      </form>
    </div>
  );
};

export default SearchBar;