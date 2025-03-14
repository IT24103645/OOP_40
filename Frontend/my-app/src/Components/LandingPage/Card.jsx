function Card({ title, description, path, year, rating, watchTime }) {
  return (
    <>
      <div className="card">
        <img className="card-poster" src={path} />
        <p className="card-title">{title}</p>
        <div className="card-details">
          <p className="card-year">{year}</p>
          <p className="card-rating">{rating}</p>
          <p className="card-length">{watchTime}</p>
        </div>
        <div className="card-btn-container">
          <p className="card-buy-ticket">Buy tickets</p>
          <p className="card-watch-trailer">Watch trailer</p>
        </div>
      </div>
    </>
  );
}

export default Card;
