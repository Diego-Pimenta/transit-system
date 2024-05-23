import { useEffect, useState } from "react";
import styles from "./topbar.module.css";
import logoDetran from "../../assets/logo-detran.png";
import { FaRegUserCircle } from "react-icons/fa";
import Button from "react-bootstrap/Button";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";

const Topbar = (props) => {
  const [perfilVisivel, setPerfilVisivel] = useState(false);
  const [cpf, setCpf] = useState("");
  const navigate = useNavigate();

  const togglePerfil = () => {
    setPerfilVisivel(!perfilVisivel);
  };

  const Logout = async () => {
    try {
      const resp = await axios.post(
        "http://localhost:8081/api/auth/logout",
        {},
        {
          headers: {
            Authorization: `${localStorage.getItem("token")}`,
          },
        }
      );

      if (resp.status === 204) {
        localStorage.removeItem("token");
        setPerfilVisivel(false);
        navigate("/");
      } else {
        toast.error("Resposta inesperada do servidor, contate o suporte!");
      }
    } catch (error) {
      toast.error("Resposta inesperada do servidor, contate o suporte!");
    }
  };

  function formatarCPF(cpf) {
    cpf = cpf?.replace(/\D/g, "");
    cpf = cpf?.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf?.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf?.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    return cpf;
  }

  useEffect(() => {
    setCpf(formatarCPF(props?.userData?.cpf));
  }, [props?.userData]);

  return (
    <div className={styles.topbar}>
      <img src={logoDetran} alt="logo" height={80} className={styles.image} />
      <div className={styles.navigation}>
        <ul className={styles.nav}>
          <li className={styles.li}>
            <Link className={styles.a} to={"/home"}>
              {props?.userData?.role === "USER" ? "Home" : "Infrações"}
            </Link>
          </li>
          {props?.userData?.role === "WORKER" && (
            <li>
              <Link className={styles.a} to={"/users"}>
                Usuários
              </Link>
            </li>
          )}
          <li>
            <Link className={styles.a} to={"/about"}>
              Sobre
            </Link>
          </li>
          <li>
            <Link className={styles.a} to={"/contact"}>
              Contato
            </Link>
          </li>
          <li>
            <FaRegUserCircle color="white" onClick={togglePerfil} />
          </li>
        </ul>
      </div>
      {perfilVisivel && (
        <div className={styles.user}>
          <div className={styles.containerUser}>
            <span>NOME DO USUÁRIO:</span>
            <h6>{props?.userData?.name}</h6>
            <span>EMAIL:</span>
            <h6>{props?.userData?.email}</h6>
            <span>CPF:</span>
            <h6>{cpf}</h6>
            <Button
              variant="primary"
              style={{ width: "100%" }}
              onClick={Logout}
            >
              Sair
            </Button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Topbar;
