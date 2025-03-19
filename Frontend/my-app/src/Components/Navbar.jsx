import { useState, useRef } from "react";
import { NavLink } from "react-router-dom";

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
      //trigger slideUp animation first
      menuRef.current.classList.add("nav-items-slideUp");

      //wait 0.5sec then make menu disappear
      setTimeout(() => {
        //first make menu disappear
        menuRef.current.style.display = "none";
        setMenuIcon("./src/images/menu_icon.svg");

        //then switch animation to slideDown for next menu opening
        menuRef.current.classList.remove("nav-items-slideUp");
        menuRef.current.classList.add("nav-items-slideDown");

        //return color bgcolor to transparent when we close menu
        navbarRef.current.style.backgroundColor = "rgba(0,0,0,0.76)";
      }, 200);
    }
  }

  return (
    <>
      <div id="navbar" ref={navbarRef}>
        <div id="menu-container">
          <p>Cinelux</p>
          <img src={menuIcon} id="burger-icon" onClick={menuClick} />
        </div>
        <div id="nav-items" className="nav-items-slideDown" ref={menuRef}>
          <NavLink
            className="nav-item"
            to="/"
            style={({ isActive }) => ({
              borderColor: isActive ? "rgb(91, 192, 190)" : "rgba(255, 255, 255, 0.195)",
              color: isActive ? "rgb(91, 192, 190)" : "rgb(255,255,255)",
            })}
            onClick={menuClick}
          >
            Home
          </NavLink>

          <NavLink className="nav-item buy-ticket-btn" to="/buy-ticket" onClick={menuClick}>
            Buy ticket
          </NavLink>

          <NavLink
            className="nav-item"
            to="/login-signup"
            style={({ isActive }) => ({
              borderColor: isActive ? "rgb(91, 192, 190)" : "rgba(255, 255, 255, 0.195)",
              color: isActive ? "rgb(91, 192, 190)" : "rgb(255,255,255)",
            })}
            onClick={menuClick}
          >
            Log in / Sign up
          </NavLink>

          <NavLink
            className="nav-item"
            to="/admin"
            style={({ isActive }) => ({
              borderColor: isActive ? "rgb(91, 192, 190)" : "rgba(255, 255, 255, 0.195)",
              color: isActive ? "rgb(91, 192, 190)" : "rgb(255,255,255)",
            })}
            onClick={menuClick}
          >
            Admin page
          </NavLink>

          <NavLink
            className="nav-item"
            to="/user"
            style={({ isActive }) => ({
              borderColor: isActive ? "rgb(91, 192, 190)" : "rgba(255, 255, 255, 0.195)",
              color: isActive ? "rgb(91, 192, 190)" : "rgb(255,255,255)",
            })}
            onClick={menuClick}
          >
            User page
          </NavLink>
        </div>
      </div>
    </>
  );
}

export default Navbar;
