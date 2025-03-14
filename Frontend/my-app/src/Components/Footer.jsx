function Footer() {
  return (
    <>
      {/*  footer */}
      <div id="footer">
        <p id="footer-logo">CINELUX</p>
        <p>privacy policy</p>
        <p>terms and conditions</p>
        <p>COVID-19 policies</p>
        <div id="footer-socials-container">
          <img src="./src/images/instagram.svg" className="footer-socials" />
          <img src="./src/images/facebook-icon.svg" className="footer-socials" />
        </div>
      </div>

      {/* privacy policy section  */}
      <div id="privacy-section">
        <p>This website is made purely for educational purposes. All content belongs to their respective owners</p>
      </div>
    </>
  );
}

export default Footer;
