import React from 'react';
import Slider from 'react-slick';
import { useNavigate, Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlay } from '@fortawesome/free-solid-svg-icons';

import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import './Hero.css';

const Hero = ({ movies = [] }) => {
  const navigate = useNavigate();

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1, // Se muestra un slide a la vez
    slidesToScroll: 1,
    arrows: true, // Activa las flechas de navegación
    autoplay: true,        // Puedes activar el autoplay si lo prefieres
    autoplaySpeed: 2000,     // Tiempo en milisegundos para el autoplay
  };

  if (!movies || movies.length === 0) {
    return <div>Cargando películas...</div>;
  }

  const goToReviews = (movieId) => {
    navigate(`/Reviews/${movieId}`);
  };

  return (
    <div className="movie-carousel-container">
      <Slider {...settings}>
        {movies.map((movie) => {
          const backdrop =
            movie.backdrops && movie.backdrops.length > 0
              ? movie.backdrops[0]
              : 'ruta/de/imagen/por/defecto.jpg';

          return (
            <div key={movie.imdbId} className="movie-card-container">
              <div
                className="movie-card"
                style={{
                  backgroundImage: `linear-gradient(to bottom, rgba(0,0,0,0), rgba(0,0,0,1)), url(${backdrop})`,
                  backgroundSize: 'cover',
                  backgroundPosition: 'center 0%',
                  height: '100%',
                }}
              >
                <div className="movie-detail">
                  <div className="movie-poster">
                    <img src={movie.poster} alt={movie.title} />
                  </div>
                  <div className="movie-title">
                    <h4>{movie.title}</h4>
                  </div>
                  <div className="movie-buttons-container">
                    <Link to={`/Trailer/${movie.trailerLink.substring(movie.trailerLink.length - 11)}`}>
                      <div className="play-button-icon-container">
                        <FontAwesomeIcon className="play-button-icon" icon={faCirclePlay} />
                      </div>
                    </Link>
                    <div className="movie-review-button-container">
                      <Button variant="info" onClick={() => goToReviews(movie.imdbId)}>
                        Reviews
                      </Button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          );
        })}
      </Slider>
    </div>
  );
};

export default Hero;

