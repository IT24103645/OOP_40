import "./landingPage.css";

import Card from "../Components/LandingPage/Card.jsx";

import { useState, useRef, useEffect } from "react";

function LandingPage() {
  const [movieId, setMovieId] = useState(0);
  const [carouselImg, setCarouselImg] = useState("slideInAnimation");

  let movieObjects = [
    {
      posterPath: "../src/images/Movieposters/superman.jpg",
      movieTitle: "Superman (2025)",
      movieDescription: "As Metropolis faces destruction, Clark Kent must confront his inner demons and team up with old allies to stop a villain unlike any he has faced before",
      year: "2025",
      watchTime: "1h 32m",
      rating: "PG-13",
    },
    {
      posterPath: "../src/images/Movieposters/karate_kid.jpg",
      movieTitle: "Karate Kid: Legends",
      movieDescription: "To defend the honor of the dojo, he must face a legendary opponent in a high-stakes battle that will test his courage and skills like never before",
      year: "2025",
      watchTime: "1h 45m",
      rating: "PG-13",
    },
    {
      posterPath: "../src/images/Movieposters/it_comes_at_night.jpg",
      movieTitle: "It Comes At Night",
      movieDescription: "a family seeks refuge behind locked doors. But when another group arrives, fear and mistrust tear them apart, and soon, the greatest danger comes from within",
      year: "2022",
      watchTime: "1h 56m",
      rating: "PG-18",
    },
    {
      posterPath: "../src/images/Movieposters/jumanji_3.jpeg",
      movieTitle: "Jumanji 3",
      movieDescription: "When Spencer disappears, his friends enter the game again, only to find that it has evolved with new landscapes and even more perilous challenges",
      year: "2024",
      watchTime: "2h 02m",
      rating: "PG-13",
    },
  ];

  // //this function runs once manually at first otherwise nothing will be shown on page load
  // cyclePosters();
  // //then setInterval cycles through them
  // setInterval(cyclePosters, 8000);

  useEffect(() => {
    let num = 0;

    const carouselInterval = setInterval(() => {
      // add and remove the class that triggers animation
      setCarouselImg("slideInAnimation");

      setTimeout(() => {
        setCarouselImg("");
      }, 2000);

      if (num == 3) {
        num = 0;
        setMovieId(0);
      } else {
        num++;
        setMovieId(num);
      }
    }, 8000);

    return () => clearInterval(carouselInterval);
  }, []);

  return (
    <>
      {/*  carousel/hero section MOBILE */}
      <div id="carousel">
        <img id="carousel-image" src={movieObjects[movieId].posterPath} />
        <div id="movie-details">
          <div id="title-description" className={carouselImg}>
            <h2 id="movie-title">{movieObjects[movieId].movieTitle}</h2>
            <p id="movie-description">{movieObjects[movieId].movieDescription}</p>
          </div>
        </div>
      </div>

      {/*  new arrivals section  */}
      <div id="new-section">
        <h1 id="new-header">Now Showing</h1>
        <div id="card-container">
          {movieObjects.map((movie, index) => {
            return <Card title={movie.movieTitle} description={movie.movieDescription} path={movie.posterPath} key={index} year={movie.year} rating={movie.rating} watchTime={movie.watchTime} />;
          })}
        </div>
      </div>
    </>
  );
}

export default LandingPage;
