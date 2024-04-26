import { useState } from "react";
import styles from "./styles.module.css";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
// import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const Register = () => {
  const notify = () => toast.error("Todos os campos são obrigatórios!");

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
        <div className={styles.TitleSection}>
          <h1>CRIAR CONTA</h1>
        </div>
        <div className={styles.FormSection}>
          <div className={styles.FormRow}>
            <FloatingLabel
              controlId="floatingInput"
              label="Nome"
              className="mb-3"
            >
              <Form.Control type="text" placeholder="Nome" />
            </FloatingLabel>
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
          </div>
          <div className={styles.FormRow}>
            <FloatingLabel
              controlId="floatingInput"
              label="Telefone"
              className="mb-3"
            >
              <Form.Control type="text" placeholder="Telefone" />
            </FloatingLabel>
            <FloatingLabel
              controlId="floatingInput"
              label="Endereço"
              className="mb-3"
            >
              <Form.Control type="text" placeholder="E-mail" />
            </FloatingLabel>
          </div>
          <div className={styles.FormRow}>
            <FloatingLabel controlId="floatingPassword" label="Senha">
              <Form.Control type="password" placeholder="Password" />
            </FloatingLabel>

            <FloatingLabel controlId="floatingInput" label="E-mail">
              <Form.Control type="email" placeholder="E-mail" />
            </FloatingLabel>
          </div>
          <div className={styles.ButtonSection}>
            <Button variant="primary" style={{ width: "50%" }} onClick={notify}>
              Cadastrar
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
