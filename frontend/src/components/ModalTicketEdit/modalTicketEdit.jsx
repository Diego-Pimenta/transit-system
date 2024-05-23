import { useState, useEffect } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import axios from "axios";
import { FiAlertOctagon } from "react-icons/fi";

const formValidation = yup.object().shape({
  category: yup.string().required("Campo obrigatório"),
  description: yup.string().required("Campo obrigatório"),
  cost: yup.string().required("Campo obrigatório"),
  emission_date: yup.string().required("Campo obrigatório"),
});

const ModalTicketEdit = (props) => {
  const {
    register,
    handleSubmit,
    setValue,
    watch,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(formValidation),
  });

  const handleClose = () => props.setShow(false);

  return (
    <Modal show={props.show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>
          <FiAlertOctagon /> {props.ticketsData.category}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <FloatingLabel
            controlId="floatingInput"
            label="Categoria"
            className="mb-3"
            style={{ marginTop: "20px" }}
            error={errors.vehiclePlate}
          >
            <Form.Control
              type="text"
              placeholder="Placa do carro"
              value={watch("vehiclePlate")}
              {...register("vehiclePlate")}
            />
            {errors.vehiclePlate && <span>{errors.vehiclePlate.message}</span>}
          </FloatingLabel>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Fechar
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ModalTicketEdit;
