import Button from "react-bootstrap/Button";
import { useState, useEffect } from "react";
import styles from "./modalTicketCreate.module.css";
import Modal from "react-bootstrap/Modal";
import { FiAlertOctagon } from "react-icons/fi";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import axios from "axios";

const formValidation = yup.object().shape({
  userCpf: yup.string().required("Campo obrigatório"),
  ticket_id: yup.string().required("Campo obrigatório"),
  vehiclePlate: yup.string().required("Campo obrigatório"),
});

const ModalTicketCreate = (props) => {
  const {
    register,
    handleSubmit,
    setValue,
    watch,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(formValidation),
  });
  const [ticketsData, setTicketsData] = useState("");
  
  const handleClose = () => {
    props.setShow(false);
    setValue("userCpf", "");
    setValue("ticket_id", "");
    setValue("vehiclePlate", "");
  };

  useEffect(() => {
    if (props.show === true) {
      const getTickets = async () => {
        try {
          const resp = await axios.get("http://localhost:8081/api/tickets", {
            headers: {
              Authorization: `${localStorage.getItem("token")}`,
            },
          });

          if (resp.status === 200) {
            setTicketsData(resp.data);
          } else {
            toast.error("Resposta inesperada do servidor, contate o suporte!");
          }
        } catch (error) {
          console.log(error);
        }
      };
      getTickets();
    }
  }, [props.show]);

  const removeSpecialChars = (str) => str.replace(/[.-]/g, "");

  const TicketCreate = async () => {
    try {
      setValue("userCpf", removeSpecialChars(watch("userCpf")));
      const resp = await axios.post(
        "http://localhost:8081/api/driver-tickets",
        {
          user_cpf: watch("userCpf"),
          ticket_id: watch("ticket_id"),
          vehicle_plate: watch("vehiclePlate"),
        },
        {
          headers: {
            Authorization: `${localStorage.getItem("token")}`,
          },
        }
      );

      if (resp.status === 201) {
        toast.success("Infração aplicada com sucesso!");
        props.setShow(false);
      } else {
        toast.error("Resposta inesperada do servidor, contate o suporte!");
      }
    } catch (error) {
      toast.error("Cpf ou senha incorreta!");
    }
  };

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

    setValue("userCpf", formattedValue);
  };

  return (
    <Modal show={props.show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>
          <FiAlertOctagon /> Aplicar infração a algum usuário
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div>
          <FloatingLabel
            controlId="floatingInput"
            label="CPF"
            className="mb-3"
            error={errors.userCpf}
          >
            <Form.Control
              type="text"
              placeholder="CPF"
              maxLength={14}
              value={watch("userCpf")}
              {...register("userCpf")}
              onChange={handleInputChange}
            />
            {errors.userCpf && <span>{errors.userCpf.message}</span>}
          </FloatingLabel>

          <Form.Select value={watch("ticket_id")} {...register("ticket_id")}>
            <option hidden value="">
              Selecione a infração
            </option>
            {Array.isArray(ticketsData) &&
              ticketsData?.map((value) => (
                <option key={value.id} value={value.id}>
                  Categoria: {value.category} Preço: R$
                  {value.cost},00
                </option>
              ))}
          </Form.Select>

          <FloatingLabel
            controlId="floatingInput"
            label="Placa do carro"
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
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Fechar
        </Button>
        <Button variant="primary" onClick={handleSubmit(TicketCreate)}>
          Criar
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ModalTicketCreate;
