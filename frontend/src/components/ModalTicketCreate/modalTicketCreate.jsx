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
import moment from "moment";

const formValidation = yup.object().shape({
  userCpf: yup.string().required("Campo obrigatório"),
  ticket_id: yup.string().required("Campo obrigatório"),
  vehiclePlate: yup.string().required("Campo obrigatório"),
  emission_date: yup.string().required("Campo obrigatório"),
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
  const [vehiclesData, setVehiclesData] = useState("");

  const handleClose = () => {
    props.setShow(false);
    setValue("userCpf", "");
    setValue("ticket_id", "");
    setValue("vehiclePlate", "");
    setValue("emission_date", "");
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

      const getVehicles = async () => {
        try {
          const resp = await axios.get("http://localhost:8081/api/vehicles", {
            headers: {
              Authorization: `${localStorage.getItem("token")}`,
            },
          });

          if (resp.status === 200) {
            setVehiclesData(resp.data);
          } else {
            toast.error("Resposta inesperada do servidor, contate o suporte!");
          }
        } catch (error) {
          console.log(error);
        }
      };
      getTickets();
      getVehicles();
    }
  }, [props.show]);

  const removeSpecialChars = (str) => str.replace(/[.-]/g, "");

  const TicketCreate = async () => {
    try {
      const currentTime = moment().format("HH:mm");
      const formattedDate = `${convertDateToCustom(
        watch("emission_date")
      )} ${currentTime}`;
      setValue("userCpf", removeSpecialChars(watch("userCpf")));
      const resp = await axios.post(
        "http://localhost:8081/api/driver-tickets",
        {
          user_cpf: watch("userCpf"),
          ticket_id: watch("ticket_id"),
          vehicle_plate: watch("vehiclePlate"),
          emission_date: formattedDate,
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
      toast.error("Resposta inesperada do servidor, contate o suporte!");
    }
  };

  const convertDateToCustom = (dateString) => {
    const [year, month, day] = dateString.split("-");
    return `${day}/${month}/${year}`;
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

          <Form.Select
            style={{ marginTop: "20px" }}
            value={watch("vehiclePlate")}
            {...register("vehiclePlate")}
          >
            <option hidden value="">
              Selecione o carro
            </option>
            {Array.isArray(vehiclesData) &&
              vehiclesData?.map((value) => (
                <option key={value.id} value={value.plate}>
                  Placa: {value.plate} Modelo:
                  {value.model}
                </option>
              ))}
          </Form.Select>

          <FloatingLabel
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
