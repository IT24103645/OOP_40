function Login() {
  return (
    <>
      <form action="">
        <div id="login-signup-form-input-container">
          <input type="text" placeholder="email address" />
          <div id="login-signup-form-password-container">
            <input type="password" placeholder="password" />
            <a href="#">forgot password?</a>
          </div>
          <button type="submit" id="login-signup-form-submit-btn">
            Login
          </button>
        </div>
      </form>

      <div id="not-a-member">
        <p>not a member?</p>
        <a href="#">Signup now</a>
      </div>
    </>
  );
}

export default Login;
