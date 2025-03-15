import { useState } from "react";

import PickMovieForm from "../Components/BookTicket/PickMovieForm";

import "../Pages/bookTicket.css";

function BookTicket() {
  const [date, setDate] = useState("placeholder");
  const [movie, setMovie] = useState(null);
  const [hall, setHall] = useState(null);
  const [time, setTime] = useState(null);

  function handleDateClick(date) {
    setDate(date);
    setMovie("placeholder");
    setHall(null);
    setTime(null);
  }
  function handleMovieClick(movie) {
    setMovie(movie);
    setHall("placeholder");
    setTime(null);
  }
  function handleHallClick(hall) {
    // setHall(hall);
    // setTime("placeholder");
  }
  function handleTimeClick(time) {
    // setTime(time);
  }

  let bookMovie = {
    Today: { "Karate kid": { cinema: "c1", showTimes: ["3:30 PM", "4.30 PM"] }, "Superman 2025": { cinema: "c1", showTimes: ["12:30 PM", "2.30 PM"] } },
    "Sun, 16 Mar": { "Moana 2 (3D)": { cinema: "c2", showTimes: ["5.30 PM"] } },
    Today: { "Karate kid": { cinema: "c1", showTimes: ["3:30 PM", "4.30 PM"] }, "Superman 2025": { cinema: "c1", showTimes: ["12:30 PM", "2.30 PM"] } },
    "Mon, 17 Mar": { "Moana 2 (3D)": { cinema: "c2", showTimes: ["5.30 PM"] } },
    "Tue, 18 Mar": { "Karate kid": { cinema: "c1", showTimes: ["3:30 PM", "4.30 PM"] }, "Superman 2025": { cinema: "c1", showTimes: ["12:30 PM", "2.30 PM"] } },
    "Thu, 20 Mar": { "Moana 2 (3D)": { cinema: "c2", showTimes: ["5.30 PM"] } },
  };

  return (
    <div id="book-ticket-container">
      <h1 id="book-ticket-header">Book A Ticket</h1>
      <div id="form-container">
        <PickMovieForm title={"Select a date"} options={bookMovie} handler={handleDateClick} />
        {movie ? <PickMovieForm title={"Select a Movie"} options={bookMovie[date]} handler={handleMovieClick} /> : null}
        {hall ? <PickMovieForm title={"Select a Hall"} options={bookMovie[date][movie]} handler={handleHallClick} /> : null}
        {time ? <PickMovieForm title={"Select a time"} options={bookMovie[date][movie][hall]} handler={handleTimeClick} /> : null}
      </div>
      {hall ? (
        <div id="book-ticket-btns">
          <p id="next-btn">Next</p>
          <p id="cancel-btn">Cancel</p>
        </div>
      ) : null}
    </div>
  );
}

export default BookTicket;
