import { useState } from "react";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import imageBg from "../assets/motorista.png";
import ReCAPTCHA from "react-google-recaptcha";
import { Link } from "react-router-dom";
import styles from "./styles.module.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const Home = () => {
  const notify = () => toast.error("CPF ou senha incorreto!");

  const [cpf, setCpf] = useState("");

  const handleInputChange = (event) => {
    let inputValue = event.target.value.replace(/\D/g, "");

    const formattedValue = inputValue
      .split("")
      .map((char, index) => {
        if (index === 3 || index === 6) {
          return "." + char;
        } else if (index === 9) {
          return "-" + char;
        }
        return char;
      })
      .join("");

    setCpf(formattedValue);
  };

  return (
    <div className={styles.FullScreenContainer}>
      <ToastContainer />
      <div className={styles.MainContainer}>
        <div className={styles.ContainerImage}>
          <img src={imageBg} />
        </div>
        <div className={styles.ContainerLogin}>
          <div className={styles.TitleSection}>
            <h1>DETRAN-BA</h1>
            <div className={styles.FormSection}>
              <FloatingLabel
                controlId="floatingInput"
                label="CPF"
                className="mb-3"
              >
                <Form.Control
                  type="text"
                  maxLength={14}
                  placeholder="000.000.000-00"
                  value={cpf}
                  onChange={handleInputChange}
                />
              </FloatingLabel>
              <FloatingLabel controlId="floatingPassword" label="Senha">
                <Form.Control type="password" placeholder="Password" />
              </FloatingLabel>
            </div>
            <ReCAPTCHA
              sitekey="6Ld54sMpAAAAACmhsG5fqSDuuZuD4JGrtYK0oWV0"
              size="normal"
              style={{ marginTop: "10px" }}
            />
          </div>
          <div className={styles.ButtonSection}>
            <Button variant="primary" style={{ width: "50%" }} onClick={notify}>
              Entrar
            </Button>
            <Link to="/register" style={{ width: "50%" }}>
              <Button variant="primary" style={{ width: "100%" }}>
                Cadastre-se
              </Button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
