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

  useEffect(() => {
    if (props.ticketsData) {
      setValue("category", props.ticketsData?.category);
      setValue("description", props.ticketsData?.description);
      setValue("cost", props.ticketsData?.cost);
      const fullDateTime = props.ticketsData?.emission_date;
      let isoDate = "";

      if (fullDateTime) {
        const datePart = fullDateTime.split(" ")[0];
        isoDate = convertDateToISO(datePart);
        setValue("emission_date", isoDate);
      }
    }
  }, [props.ticketsData]);

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
      const resp = await axios.put(
        `http://localhost:8081/api/tickets/${props.ticketsData?.id}`,
        {
          category: watch("category"),
          description: watch("description"),
          cost: watch("cost"),
         // emission_date: formattedDate,
        },
        {
          headers: {
            Authorization: `${localStorage.getItem("token")}`,
          },
        }
      );

      if (resp.status === 200) {
        toast.success("Infração editada com sucesso!");
        props.setShow(false);
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
            error={errors.category}
          >
            <Form.Control
              type="text"
              placeholder="Categoria"
              value={watch("category")}
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
              value={watch("description")}
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
              value={watch("cost")}
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
              class="form-control"
              placeholder="Data de emissão"
              value={watch("emission_date")}
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
          Editar
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ModalTicketEdit;
