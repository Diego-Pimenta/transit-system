import React from "react";
import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import styles from "./styles.module.css";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import TicketsCard from "../../components/TicketsCard/ticketsCard";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link, useNavigate } from "react-router-dom";

const formValidation = yup.object().shape({
  name: yup.string().required("Campo obrigatório"),
  email: yup.string().required("Campo obrigatório"),
  cpf: yup.string().required("Campo obrigatório"),
  address: yup.string().required("Campo obrigatório"),
  phone_number: yup.string().required("Campo obrigatório"),
});

const UserDetails = () => {
  const {
    register,
    handleSubmit,
    setValue,
    watch,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(formValidation),
  });
  const navigate = useNavigate();

  const { id } = useParams();
  const [userData, setUserData] = useState("");
  const [ticketsData, setTicketsData] = useState([]);

  useEffect(() => {
    const getUser = async () => {
      try {
        const resp = await axios.get(`http://localhost:8081/api/users/${id}`, {
          headers: {
            Authorization: `${localStorage.getItem("token")}`,
          },
        });

        if (resp.status === 200) {
          setUserData(resp.data);
        } else {
          toast.error("Resposta inesperada do servidor, contate o suporte!");
        }
      } catch (error) {
        console.log(error);
      }
    };

    getUser();
  }, []);

  useEffect(() => {
    if (userData) {
      setValue("name", userData.name);
      setValue("cpf", userData.cpf);
      setValue("email", userData.email);
      setValue("address", userData.address);
      setValue("phone_number", userData.phone_number);
      setValue("role", userData.role);

      const getTicketsUser = async () => {
        try {
          const resp = await axios.get(
            `http://localhost:8081/api/search/user/${userData.cpf}`,
            {
              headers: {
                Authorization: `${localStorage.getItem("token")}`,
              },
            }
          );

          if (resp.status === 200) {
            setTicketsData(resp.data);
          } else {
            toast.error("Resposta inesperada do servidor, contate o suporte!");
          }
        } catch (error) {
          console.log(error);
        }
      };

      getTicketsUser();
    }
  }, [userData]);

  const editUser = async () => {
    try {
      const resp = await axios.put(
        `http://localhost:8081/api/users/${id}`,
        {
          name: watch("name"),
          email: watch("email"),
          cpf: watch("cpf"),
          address: watch("address"),
          phone_number: watch("phone_number"),
        },
        {
          headers: {
            Authorization: `${localStorage.getItem("token")}`,
          },
        }
      );

      if (resp.status === 200) {
        toast.success("Usuário editado com sucesso!");
      } else {
        toast.error("Resposta inesperada do servidor, contate o suporte!");
      }
    } catch (error) {
      toast.error("Resposta inesperada do servidor, contate o suporte!");
    }
  };

  const removeUser = async () => {
    try {
      const resp = await axios.delete(`http://localhost:8081/api/users/${id}`, {
        headers: {
          Authorization: `${localStorage.getItem("token")}`,
        },
      });

      if (resp.status === 204) {
        toast.success("Usuário excluído com sucesso!");
        navigate("/users");
      } else {
        toast.error("Resposta inesperada do servidor, contate o suporte!");
      }
    } catch (error) {
      toast.error("Resposta inesperada do servidor, contate o suporte!");
    }
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <ToastContainer />
      <div className={styles.titleContainer}>
        <h1>{userData?.name}</h1>
      </div>
      <div className={styles.userDataContainer}>
        <h1>Dados do usuário:</h1>
        <div className={styles.FormRow}>
          <FloatingLabel
            controlId="floatingInput"
            label="Nome"
            className="mb-3 w-50"
          >
            <Form.Control
              type="text"
              placeholder="Nome"
              value={watch("name")}
              {...register("name")}
            />
          </FloatingLabel>

          <FloatingLabel
            controlId="floatingInput"
            label="Email"
            className="mb-3 w-50"
          >
            <Form.Control
              type="text"
              placeholder="name@example.com"
              value={watch("email")}
              {...register("email")}
            />
          </FloatingLabel>

          <FloatingLabel
            controlId="floatingInput"
            label="CPF"
            className="mb-3 w-50"
          >
            <Form.Control
              type="text"
              placeholder="xxx.xxx.xxx-xx"
              value={watch("cpf")}
              {...register("cpf")}
            />
          </FloatingLabel>
        </div>
        <div className={styles.FormRow}>
          <FloatingLabel
            controlId="floatingInput"
            label="Endereço"
            className="mb-3 w-50"
          >
            <Form.Control
              type="text"
              placeholder="Avenida Tancredo Neves"
              value={watch("address")}
              {...register("address")}
            />
          </FloatingLabel>

          <FloatingLabel
            controlId="floatingInput"
            label="Celular"
            className="mb-3 w-50"
          >
            <Form.Control
              type="text"
              placeholder="(00)00000-0000"
              value={watch("phone_number")}
              {...register("phone_number")}
            />
          </FloatingLabel>

          <FloatingLabel
            controlId="floatingInput"
            label="Função/Cargo"
            className="mb-3 w-50"
          >
            <Form.Control type="text" value={watch("role")} disabled />
          </FloatingLabel>
        </div>
        <div className={styles.FormRowBtn}>
          <Button variant="danger" onClick={() => handleSubmit(removeUser())}>
            Excluir usuário
          </Button>
          <Button variant="primary" onClick={() => handleSubmit(editUser())}>
            Editar usuário
          </Button>
        </div>
      </div>
      <div
        className={styles.userDataContainer}
        style={{ marginBottom: "20px" }}
      >
        <h1>Infrações do usuário:</h1>
        <div className={styles.ticketsContainer}>
          {ticketsData?.driver_tickets ? (
            Object.entries(ticketsData?.driver_tickets).map(
              ([ticketId, ticket], index) => (
                <TicketsCard
                  key={index}
                  ticketsData={ticket}
                  ticketId={ticketId}
                  userData={userData}
                />
              )
            )
          ) : (
            <h3>O usuário não possui infrações pendentes!</h3>
          )}
        </div>
      </div>
    </div>
  );
};

export default UserDetails;
