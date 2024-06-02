import Modal from "react-bootstrap/Modal";
import axios from "axios";
import { useState, useEffect } from "react";
import { FiAlertOctagon } from "react-icons/fi";
import Button from "react-bootstrap/Button";

const ModalCaution = (props) => {
  const handleClose = () => {
    props.setShow(false);
  };

  const makeRequest = () => {
    props.setShow(false);
    props.setMakeRequest(true);
  };

  return (
    <Modal show={props.show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>
          <FiAlertOctagon /> {props.title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Não
        </Button>
        <Button variant="primary" onClick={makeRequest}>
          Sim
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ModalCaution;
