import { Outlet, NavLink } from "react-router-dom";

import "./loginSignup.css";

function LoginSignup() {
  return (
    <>
      <div id="login-signup-form-container">
        <div id="login-signup-form-btn-container">
          <NavLink
            className="login-signup-form-btn"
            to="/login-signup"
            style={({ isActive }) => ({
              backgroundColor: isActive ? "rgb(91, 192, 190)" : "transparent",
              borderColor: isActive ? "rgb(91, 192, 190)" : "transparent",
              color: isActive ? "rgb(0,0,0)" : "rgb(255,255,255)",
            })}
            end
          >
            Login
          </NavLink>

          <NavLink
            className="login-signup-form-btn"
            to="/login-signup/signup"
            style={({ isActive }) => ({
              backgroundColor: isActive ? "rgb(91, 192, 190)" : "transparent",
              borderColor: isActive ? "rgb(91, 192, 190)" : "transparent",
              color: isActive ? "rgb(0,0,0)" : "rgb(255,255,255)",
            })}
          >
            Signup
          </NavLink>
        </div>
        <Outlet />
      </div>
    </>
  );
}

export default LoginSignup;
