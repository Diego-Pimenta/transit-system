// __mocks__/react-google-recaptcha.js
import React from "react";

const ReCAPTCHA = ({ onChange }) => {
  return (
    <div data-testid="recaptcha-mock" onClick={() => onChange("mock-token")}>
      ReCAPTCHA Mock
    </div>
  );
};

export default ReCAPTCHA;
