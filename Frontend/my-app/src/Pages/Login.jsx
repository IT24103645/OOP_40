function Login() {
  return (
    <>
      <form action="">
        <div id="login-signup-form-input-container">
          <input type="text" placeholder="email address" />
          <div id="login-signup-form-password-container">
            <input type="password" placeholder="password" />
            <a href="#" className="login-signup-links">
              forgot password?
            </a>
          </div>
          <p type="" id="login-signup-form-submit-btn">
            Login
          </p>
        </div>
      </form>

      <div id="not-a-member">
        <p>not a member?</p>
        <a href="#" className="login-signup-links">
          Signup now
        </a>
      </div>
    </>
  );
}

export default Login;
