import { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import styles from "./modalTicketPayment.module.css";
import { FiAlertOctagon } from "react-icons/fi";
import { FaCopy } from "react-icons/fa6";

const ModalTicketPayment = (props) => {
  const handleClose = () => props.setShow(false);

  const copyToClipboard = () => {
    const copyText = document.getElementById("boletoCode").textContent;

    const textArea = document.createElement("textarea");
    textArea.value = copyText;
    document.body.appendChild(textArea);

    textArea.select();
    textArea.setSelectionRange(0, 99999);

    document.execCommand("copy");

    document.body.removeChild(textArea);
  };

  function generateBoletoCode() {
    const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    const length = 48;
    let code = "";

    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * characters.length);
      code += characters.charAt(randomIndex);
    }

    return code;
  }

  return (
    <Modal show={props.show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>
          <FiAlertOctagon /> {props.title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className={styles.containerPayment}>
          <span>Código do boleto para efetuar o pagamento da infração:</span>
          <br />
          <p id="boletoCode">{generateBoletoCode()}</p>
          <Button onClick={copyToClipboard}>
            <FaCopy /> Copiar Código
          </Button>
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Fechar
        </Button>
      </Modal.Footer>
    </Modal>
  );
};
export default ModalTicketPayment;
