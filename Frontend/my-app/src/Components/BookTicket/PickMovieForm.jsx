function PickMovieForm({ title, options, handler }) {
  console.log(options);

  return (
    <>
      <div className="options-section">
        <h2 className="options-title">{title}</h2>
        <div className="options-container">
          {Object.keys(options).map((option, index) => {
            return (
              <p
                className="option"
                key={index}
                onClick={() => {
                  handler(option);
                }}
              >
                {option}
              </p>
            );
          })}
        </div>
      </div>
    </>
  );
}

export default PickMovieForm;
