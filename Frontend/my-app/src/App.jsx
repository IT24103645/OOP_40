import { useState } from "react";

import Footer from "./Components/Footer.jsx";
import Navbar from "./Components/Navbar.jsx";
import LandingPage from "./Pages/LandingPage.jsx";

function App() {
  return (
    <>
      {/* absolute positioned navbar */}
      <Navbar />

      <LandingPage />

      {/* footer and privacy section */}
      <Footer />
    </>
  );
}

export default App;
