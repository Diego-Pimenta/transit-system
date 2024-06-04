import styles from "./styles.module.css";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import { MdOutlineContactSupport } from "react-icons/md";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const Contact = () => {
  const sendMessage = () => {
    toast.success("Mensagem enviada com sucesso!");
  };

  return (
    <div className={styles.mainContent}>
      <ToastContainer />
      <div className={styles.titleContainer}>
        <h1>
          <MdOutlineContactSupport />
          Entre em contato
        </h1>
      </div>
      <div className={styles.pContainer}>
        <div className={styles.form}>
          <FloatingLabel
            controlId="floatingInput"
            label="Nome"
            className="mb-3"
          >
            <Form.Control type="text" placeholder="Nome" />
          </FloatingLabel>
          <FloatingLabel
            controlId="floatingInput"
            label="Email"
            className="mb-3"
          >
            <Form.Control type="text" placeholder="Email" />
          </FloatingLabel>
          <FloatingLabel
            controlId="floatingInput"
            label="Mensagem..."
            className="mb-3"
          >
            <Form.Control
              as="textarea"
              rows={5}
              style={{ resize: "none", height: "100px" }}
            />
          </FloatingLabel>
          <Button variant="primary" onClick={() => sendMessage()}>
            Enviar mensagem
          </Button>
        </div>
        <div className={styles.contact}>
          <h6>CENTRAL DE ATENDIMENTO DETRAN-BA</h6>
          <div>
            SHOPPING BARRA (71) 3267-7392 <br />
            SHOPPING BELA VISTA (71) 3450-0565 <br /> SHOPPING DA BAHIA (71)
            3450-1198 / 2927 <br /> SHOPPING SALVADOR (71) 3341-1286 <br />{" "}
            CAJAZEIRAS (71) 3395-1994 <br /> CAMAÇARI (71) 3395-1994 <br />{" "}
            CANDEIAS (71) 3601-3719 <br />
            COMÉRCIO (71) 3601-3719 <br /> GUARAJUBA (71) 3674-2340 <br /> LAURO
            DE FREITAS (71) 3378-5806/3283-3322 <br /> PAU DA LIMA (71)
            3212-8072 <br />
            PERIPERI (71) 3217-1385 <br /> PERNAMBUÉS (71) 3460-2186 <br />{" "}
            PITUAÇU (71) 3360-1466 <br /> POSTO PARIPE (71) 3521-4435 <br />{" "}
            SIMÕES FILHO (71) 3396-0394.
          </div>
        </div>
      </div>
    </div>
  );
};

export default Contact;
