document.addEventListener("DOMContentLoaded", () => {
    const movieSelect = document.querySelector("select[name='movieId']");
    const timeSelect = document.querySelector("select[name='time']");
    const hallSelect = document.querySelector("select[name='hall']");
    const seatSelect = document.querySelector("select[name='seat']");

    if (movieSelect) {
        fetch("/movies")
            .then(res => res.json())
            .then(movies => {
                movies.forEach(movie => {
                    const option = document.createElement("option");
                    option.value = movie.id;
                    option.textContent = movie.title;
                    movieSelect.appendChild(option);
                });
            });

        movieSelect.addEventListener("change", () => {
            fetch(`/showtime?movieId=${movieSelect.value}`)
                .then(res => res.json())
                .then(showtimes => {
                    timeSelect.innerHTML = "";
                    hallSelect.innerHTML = "";
                    seatSelect.innerHTML = "";

                    showtimes.forEach(s => {
                        const timeOpt = document.createElement("option");
                        timeOpt.value = s.time;
                        timeOpt.textContent = new Date(s.time).toLocaleString();
                        timeSelect.appendChild(timeOpt);

                        const hallOpt = document.createElement("option");
                        hallOpt.value = s.hall;
                        hallOpt.textContent = s.hall;
                        hallSelect.appendChild(hallOpt);

                        s.seats.forEach((available, index) => {
                            if (available) {
                                const seatOpt = document.createElement("option");
                                seatOpt.value = index + 1;
                                seatOpt.textContent = `Seat ${index + 1}`;
                                seatSelect.appendChild(seatOpt);
                            }
                        });
                    });
                });
        });
    }

    const signupForm = document.querySelector("form[action='/customer']");
    if (signupForm && signupForm.querySelector("input[name='password2']")) {
        signupForm.addEventListener("submit", e => {
            const pass1 = signupForm.querySelector("input[name='password1']").value;
            const pass2 = signupForm.querySelector("input[name='password2']").value;
            const errorDiv = document.getElementById("error");

            if (pass1 !== pass2) {
                e.preventDefault();
                errorDiv.textContent = "Passwords don't match.";
                errorDiv.style.color = "red";
            } else {
                errorDiv.textContent = "";
            }
        });
    }
});
