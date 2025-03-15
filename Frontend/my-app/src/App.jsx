import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Footer from "./Components/Footer.jsx";
import Navbar from "./Components/Navbar.jsx";
import BookTicket from "./Pages/BookTicket.jsx";
import LandingPage from "./Pages/LandingPage.jsx";

function App() {
  return (
    <>
      <Router>
        {/* absolute positioned navbar */}
        <Navbar />

        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/buy-ticket" element={<BookTicket />} />
        </Routes>

        {/* footer and privacy section */}
        <Footer />
      </Router>
    </>
  );
}

export default App;
