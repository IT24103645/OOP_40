import "./user.css";

function User() {
  let userName = "Ravindu";

  return (
    <>
      <div className="user-actions-container">
        <div className="user-profile" id="regular-user-profile">
          <img className="user-icon" src="./src/images/user.svg"></img>
          <h2>Welcome {userName} !</h2>
        </div>
        <div className="user-action">
          <img src="../src/images/sales.svg" className="admin-action-icons"></img>
          <p>Edit profile</p>
        </div>
        <div className="user-action">
          <img src="../src/images/add_movie.svg" className="admin-action-icons"></img>
          <p>Movie history</p>
        </div>
        <div className="user-action">
          <img src="../src/images/edit_movie.svg" className="admin-action-icons"></img>
          <p>Manage Bookings</p>
        </div>
        <div className="user-action" id="delete-user-profile">
          <img src="../src/images/delete.svg" className="admin-action-icons"></img>
          <p>Delete profile</p>
        </div>
      </div>
    </>
  );
}

export default User;
