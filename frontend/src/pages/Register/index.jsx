import { useState } from "react";
import styles from "./styles.module.css";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

const formValidation = yup.object().shape({
  name: yup.string().required("Campo obrigatório"),
  cpf: yup.string().required("Campo obrigatório"),
  phone: yup.string().required("Campo obrigatório"),
  address: yup.string().required("Campo obrigatório"),
  password: yup
    .string()
    .min(8, "Mínimo de 8 caracteres!")
    .max(20, "Máximo de 20 caracteres!")
    .required("Campo obrigatório"),
  email: yup.string().required("Campo obrigatório").email("Email inválido"),
});

const Register = () => {
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

  //const notify = () => toast.error("Todos os campos são obrigatórios!");

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

    setValue("cpf", formattedValue);
  };

  const removeSpecialChars = (str) => str.replace(/[.-]/g, "");

  const Register = async (data) => {
    try {
      const cpf = removeSpecialChars(watch("cpf"));

      const resp = await axios.post("http://localhost:8081/api/auth/register", {
        cpf: cpf,
        name: watch("name"),
        address: watch("address"),
        phone_number: watch("phone"),
        email: watch("email"),
        password: watch("password"),
      });

      if (resp.status === 201) {
        toast.success("Usuário cadastrado com sucesso!");
        navigate("/login");
      }
    } catch (error) {
      toast.error(error.response?.data?.message);
    }
  };

  return (
    <div className={styles.FullScreenContainer}>
      <ToastContainer />
      <div className={styles.MainContainer}>
        <div className={styles.TitleSection}>
          <h1>CRIAR CONTA</h1>
        </div>
        <div className={styles.FormSection}>
          <div className={styles.FormRow}>
            <FloatingLabel
              controlId="floatingInput"
              label="Nome"
              className="mb-3"
              error={errors.name}
            >
              <Form.Control
                type="text"
                placeholder="Nome"
                value={watch("name")}
                {...register("name")}
              />
              {errors.name && (
                <span className={styles.error}>{errors.name.message}</span>
              )}
            </FloatingLabel>
            <FloatingLabel
              controlId="floatingInput"
              label="CPF"
              className="mb-3"
              error={errors.cpf}
            >
              <Form.Control
                type="text"
                maxLength={14}
                placeholder="000.000.000-00"
                value={watch("cpf")}
                {...register("cpf")}
                onChange={handleInputChange}
              />
              {errors.cpf && <span>{errors.cpf.message}</span>}
            </FloatingLabel>
          </div>
          <div className={styles.FormRow}>
            <FloatingLabel
              controlId="floatingInput"
              label="Telefone"
              className="mb-3"
              error={errors.phone}
            >
              <Form.Control
                type="text"
                placeholder="Telefone"
                value={watch("phone")}
                {...register("phone")}
              />
              {errors.phone && <span>{errors.phone.message}</span>}
            </FloatingLabel>
            <FloatingLabel
              controlId="floatingInput"
              label="Endereço"
              className="mb-3"
              error={errors.address}
            >
              <Form.Control
                type="text"
                placeholder="E-mail"
                value={watch("address")}
                {...register("address")}
              />
              {errors.address && <span>{errors.address.message}</span>}
            </FloatingLabel>
          </div>
          <div className={styles.FormRow}>
            <FloatingLabel
              controlId="floatingPassword"
              label="Senha"
              error={errors.password}
            >
              <Form.Control
                type="password"
                placeholder="Password"
                value={watch("password")}
                {...register("password")}
              />
              {errors.password && <span>{errors.password.message}</span>}
            </FloatingLabel>

            <FloatingLabel
              controlId="floatingInput"
              label="E-mail"
              error={errors.email}
            >
              <Form.Control
                type="email"
                placeholder="E-mail"
                value={watch("email")}
                {...register("email")}
              />
              {errors.email && <span>{errors.email.message}</span>}
            </FloatingLabel>
          </div>
          <div className={styles.ButtonSection}>
            <Link style={{ width: "25%" }} to={"/"}>
              <Button variant="primary" style={{ width: "100%" }}>
                Voltar
              </Button>
            </Link>
            <Button
              variant="primary"
              style={{ width: "25%" }}
              onClick={handleSubmit(Register)}
            >
              Cadastrar
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
