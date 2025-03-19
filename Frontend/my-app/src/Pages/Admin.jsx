import "./admin.css";

import salesSVG from "../../public/images/sales.svg";
import adminSVG from "../../public/images/admin.svg";
import add_movieSVG from "../../public/images/add_movie.svg";
import edit_movieSVG from "../../public/images/edit_movie.svg";

function Admin() {
  let adminName = "Ravindu";

  return (
    <>
      <div className="user-actions-container">
        <div className="user-profile">
          <img className="user-icon" src={adminSVG}></img>
          <div>
            <h2 className="user-name">Welcome {adminName} !</h2>
            <p className="user-status">Administrator</p>
          </div>
        </div>
        <div className="user-action">
          <img src={salesSVG} className="user-action-icons"></img>
          <p>Sales Analytics</p>
        </div>
        <div className="user-action">
          <img src={add_movieSVG} className="user-action-icons"></img>
          <p>New movie entry</p>
        </div>
        <div className="user-action">
          <img src={edit_movieSVG} className="user-action-icons"></img>
          <p>Manage movies</p>
        </div>
      </div>
    </>
  );
}

export default Admin;
