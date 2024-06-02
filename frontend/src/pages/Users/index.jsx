import styles from "./styles.module.css";
import { useState, useEffect } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import { FaUsers } from "react-icons/fa";
import { FaRegUser } from "react-icons/fa";
import { FaUser } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const Users = () => {
  const navigate = useNavigate();

  const [userData, setUserData] = useState([]);

  useEffect(() => {
    const getUsers = async () => {
      try {
        const resp = await axios.get("http://localhost:8081/api/users", {
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

    getUsers();
  }, []);

  const redirect = (id) => {
    navigate(`/users/${id}`);
  };

  function formatarCPF(cpf) {
    cpf = cpf?.replace(/\D/g, "");
    cpf = cpf?.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf?.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf?.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    return cpf;
  }

  return (
    <div className={styles.wrapper}>
      <div className={styles.titleContainer}>
        <h1>
          <FaUsers size={50} /> Usuários
        </h1>
        {/* <Form className="d-flex" style={{ marginRight: "50px" }}>
          <Form.Control
            type="search"
            placeholder="Pesquisar usuários..."
            className="me-2"
            aria-label="Search"
          />
        </Form> */}
      </div>
      <div className={styles.containerUser}>
        {userData?.map(
          (value, index) =>
            value.role !== "WORKER" && (
              <div
                key={index}
                className={styles.cardUser}
                onClick={() => redirect(value.id)}
              >
                <div className={styles.imgUser}>
                  <FaUser size={100} />
                </div>
                <div className={styles.descUser}>
                  <p>
                    <span>Nome:</span> {value.name}
                  </p>
                  <p>
                    <span>CPF:</span> {formatarCPF(value.cpf)}
                  </p>
                </div>
              </div>
            )
        )}
      </div>
    </div>
  );
};
export default Users;
