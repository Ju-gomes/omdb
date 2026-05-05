import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import MovieDetails from './pages/MovieDetails';
import './App.css'; // ou App.scss

function App() {
  return (
    <Router>
      <div className="app">
        <header className="header">
          <h1>🎬 OMDb Filmes e Séries</h1>
          <br />                    
        </header>

        <main>
          <Routes>
            {/* Página inicial com busca */}
            <Route path="/" element={<Home />} />

            {/* Detalhes do filme */}
            <Route path="/movie/:id" element={<MovieDetails />} />

            {/* Rota para caso digite URL errada */}
            <Route path="*" element={<h2>Página não encontrada</h2>} />
          </Routes>
        </main>

        <footer className="footer">
          <br />
          <p>OMDb - API</p>
        </footer>
      </div>
    </Router>
  );
}

export default App;