import { useState } from "react";
import styles from "./styles.module.css";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Button from "react-bootstrap/Button";
// import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

const formValidation = yup.object().shape({
  name: yup.string().required("Campo obrigatório"),
  cpf: yup.string().required("Campo obrigatório"),
  phone: yup.string().required("Campo obrigatório"),
  address: yup.string().required("Campo obrigatório"),
  password: yup.string().required("Campo obrigatório"),
  email: yup.string().required("Campo obrigatório"),
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

  const Register = (data) => {
    console.log(data);
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
              {errors.name && <span>{errors.name.message}</span>}
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
            <Button
              variant="primary"
              style={{ width: "50%" }}
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
