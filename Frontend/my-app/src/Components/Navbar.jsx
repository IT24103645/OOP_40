import { useState, useRef } from "react";

function Navbar() {
  const [isVisible, setIsVisible] = useState(false);
  const [menuIcon, setMenuIcon] = useState("./src/images/menu_icon.svg");
  const menuRef = useRef();
  const navbarRef = useRef();

  function menuClick() {
    //registers the click
    setIsVisible((prev) => !prev);

    if (isVisible) {
      menuRef.current.style.display = "flex";
      setMenuIcon("./src/images/close.svg");

      //we also darken the bgcolor
      navbarRef.current.style.backgroundColor = "rgb(0,0,0)";
    }
    //when the menu is closed on mobile
    else {
      menuRef.current.style.display = "none";
      setMenuIcon("./src/images/menu_icon.svg");

      //return color back to normal when we close menu
      navbarRef.current.style.backgroundColor = "rgba(0,0,0,0.76)";
    }
  }

  return (
    <>
      <div id="navbar" ref={navbarRef}>
        <div id="menu-container">
          <p>Cinelux</p>
          <img src={menuIcon} id="burger-icon" onClick={menuClick} />
        </div>
        <div id="nav-items" ref={menuRef}>
          <p className="nav-item" id="active-page">
            Home
          </p>
          <p className="nav-item" id="buy-ticket-btn">
            Buy ticket
          </p>
          <p className="nav-item">Cinemas</p>
          <p className="nav-item">Contact us</p>
        </div>
      </div>
    </>
  );
}

export default Navbar;
