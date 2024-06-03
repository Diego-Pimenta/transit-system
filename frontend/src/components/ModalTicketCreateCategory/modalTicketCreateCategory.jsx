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
  cost: yup.string().required("Campo obrigatório"),
  emission_date: yup.string().required("Campo obrigatório"),
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
      const currentTime = moment().format("HH:mm");
      const formattedDate = `${convertDateToCustom(
        watch("emission_date")
      )} ${currentTime}`;

      console.log(formattedDate);
      const resp = await axios.post(
        `http://localhost:8081/api/tickets`,
        {
          category: watch("category"),
          description: watch("description"),
          cost: watch("cost"),
          //  emission_date: formattedDate,
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

  const convertDateToISO = (dateString) => {
    const [day, month, year] = dateString.split("-");
    return `${year}-${month}-${day}`;
  };

  const convertDateToCustom = (dateString) => {
    const [year, month, day] = dateString.split("-");
    return `${day}-${month}-${year}`;
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
            {errors.category && <span>{errors.category.message}</span>}
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
            {errors.description && <span>{errors.description.message}</span>}
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
            {errors.cost && <span>{errors.cost.message}</span>}
          </FloatingLabel>
          {/* <FloatingLabel
            controlId="floatingInput"
            label="Data de emissão"
            className="mb-3"
            style={{ marginTop: "20px" }}
            error={errors.emission_date}
          >
            <input
              type="date"
              className="form-control"
              placeholder="Data de emissão"
              {...register("emission_date")}
            />
            {errors.emission_date && (
              <span>{errors.emission_date.message}</span>
            )}
          </FloatingLabel> */}
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
