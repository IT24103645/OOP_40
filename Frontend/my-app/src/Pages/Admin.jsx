import "./admin.css";

function Admin() {
  let adminName = "Ravindu";

  return (
    <>
      <div className="user-actions-container">
        <div className="user-profile">
          <img className="user-icon" src="./src/images/admin.svg"></img>
          <div>
            <h2 className="user-name">Welcome {adminName} !</h2>
            <p className="user-status">Administrator</p>
          </div>
        </div>
        <div className="user-action">
          <img src="../src/images/sales.svg" className="user-action-icons"></img>
          <p>Sales Analytics</p>
        </div>
        <div className="user-action">
          <img src="../src/images/add_movie.svg" className="user-action-icons"></img>
          <p>New movie entry</p>
        </div>
        <div className="user-action">
          <img src="../src/images/edit_movie.svg" className="user-action-icons"></img>
          <p>Manage movies</p>
        </div>
      </div>
    </>
  );
}

export default Admin;
