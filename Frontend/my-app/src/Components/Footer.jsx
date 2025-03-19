import facebookSVG from "../../public/images/facebook-icon.svg";
import instagramSVG from "../../public/images/instagram.svg";

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
          <img src={facebookSVG} className="footer-socials" />
          <img src={instagramSVG} className="footer-socials" />
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
