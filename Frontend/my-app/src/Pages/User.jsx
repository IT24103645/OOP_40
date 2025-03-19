import "./user.css";

import userSVG from "../../public/images/user.svg";
import edit_profileSVG from "../../public/images/edit_profile.svg";
import historySVG from "../../public/images/history.svg";
import ticketSVG from "../../public/images/ticket.svg";
import deleteSVG from "../../public/images/delete.svg";

function User() {
  let userName = "Ravindu";

  return (
    <>
      <div className="user-actions-container">
        <div className="user-profile" id="regular-user-profile">
          <img className="user-icon" src={userSVG}></img>
          <h2>Welcome {userName} !</h2>
        </div>
        <div className="user-action">
          <img src={edit_profileSVG} className="admin-action-icons"></img>
          <p>Edit profile</p>
        </div>
        <div className="user-action">
          <img src={historySVG} className="admin-action-icons"></img>
          <p>Movie history</p>
        </div>
        <div className="user-action">
          <img src={ticketSVG} className="admin-action-icons"></img>
          <p>Manage Tickets</p>
        </div>
        <div className="user-action" id="delete-user-profile">
          <img src={deleteSVG} className="admin-action-icons"></img>
          <p>Delete profile</p>
        </div>
      </div>
    </>
  );
}

export default User;
