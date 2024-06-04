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
import moment from "moment";

const formValidation = yup.object().shape({
  category: yup.string().required("Campo obrigatório"),
  description: yup.string().required("Campo obrigatório"),
  cost: yup
    .number()
    .transform((value, originalValue) => {
      return originalValue === "" ? null : value;
    })
    .typeError('Apenas números e "." ')
    .required("Campo obrigatório"),
});

const ModalTicketCreateCategory = (props) => {
  const {
    register,
    handleSubmit,
    setValue,
    watch,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(formValidation),
  });

  const handleClose = () => {
    props.setShow(false);
  };

  const editTicket = async () => {
    try {
      const resp = await axios.post(
        `http://localhost:8081/api/tickets`,
        {
          category: watch("category"),
          description: watch("description"),
          cost: watch("cost"),
        },
        {
          headers: {
            Authorization: `${localStorage.getItem("token")}`,
          },
        }
      );

      if (resp.status === 201) {
        toast.success("Infração criada com sucesso!");
        props.setShow(false);
        window.location.reload();
      } else {
        toast.error("Resposta inesperada do servidor, contate o suporte!");
      }
    } catch (error) {
      toast.error("Resposta inesperada do servidor, contate o suporte!");
    }
  };

  return (
    <Modal show={props.show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>
          <FiAlertOctagon /> Nova categoria
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <FloatingLabel
            controlId="floatingInput"
            label="Nome da categoria"
            className="mb-3"
            style={{ marginTop: "20px" }}
            error={errors.category}
          >
            <Form.Control
              type="text"
              placeholder="Categoria"
              {...register("category")}
            />
            {errors.category && (
              <span style={{ color: "red" }}>{errors.category.message}</span>
            )}
          </FloatingLabel>
          <FloatingLabel
            controlId="floatingInput"
            label="Descrição"
            className="mb-3"
            style={{ marginTop: "20px" }}
            error={errors.description}
          >
            <Form.Control
              type="text"
              placeholder="Descrição"
              {...register("description")}
            />
            {errors.description && (
              <span style={{ color: "red" }}>{errors.description.message}</span>
            )}
          </FloatingLabel>
          <FloatingLabel
            controlId="floatingInput"
            label="Preço"
            className="mb-3"
            style={{ marginTop: "20px" }}
            error={errors.cost}
          >
            <Form.Control
              type="text"
              placeholder="Preço"
              {...register("cost")}
            />
            {errors.cost && (
              <span style={{ color: "red" }}>{errors.cost.message}</span>
            )}
          </FloatingLabel>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Fechar
        </Button>
        <Button variant="primary" onClick={handleSubmit(editTicket)}>
          Criar
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ModalTicketCreateCategory;
