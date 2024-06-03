import styles from "./styles.module.css";
import { useEffect, useState } from "react";
import axios from "axios";
import { FaTrafficLight } from "react-icons/fa";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Accordion from "react-bootstrap/Accordion";
import TicketsCard from "../../components/TicketsCard/ticketsCard";
import ModalTicketCreate from "../../components/ModalTicketCreate/modalTicketCreate";
import ModalTicketCreateCategory from "../../components/ModalTicketCreateCategory/modalTicketCreateCategory";
import Spinner from "react-bootstrap/Spinner";

const Home = (props) => {
  const [role, setRole] = useState("");
  const [userId, setUserId] = useState("");
  const [ticketsData, setTicketsData] = useState([]);
  const [userData, setUserData] = useState("");
  const [show, setShow] = useState(false);
  const [showCreateCategory, setShowCreateCategory] = useState(false);

  const handleShow = () => setShow(true);
  const handleShowCreateCategory = () => setShowCreateCategory(true);

  useEffect(() => {
    setRole(props?.userData?.role);
    setUserId(props?.userData?.id);
  }, [props?.userData]);

  useEffect(() => {
    if (props?.userData?.role === "USER") {
      const getTicketsUser = async () => {
        try {
          const resp = await axios.get(
            `http://localhost:8081/api/search/user/${props?.userData?.cpf}`,
            {
              headers: {
                Authorization: `${localStorage.getItem("token")}`,
              },
            }
          );

          if (resp.status === 200) {
            setTicketsData(resp.data);
            setUserData(resp.data.user);
          } else {
            toast.error("Resposta inesperada do servidor, contate o suporte!");
          }
        } catch (error) {
          console.log(error);
        }
      };

      getTicketsUser();
    } else if (props?.userData?.role === "WORKER") {
      const getTicketsWorker = async () => {
        try {
          const resp = await axios.get("http://localhost:8081/api/tickets", {
            headers: {
              Authorization: `${localStorage.getItem("token")}`,
            },
          });

          if (resp.status === 200) {
            setTicketsData(resp.data);
            setUserData(resp.data.user);
          } else {
            toast.error("Resposta inesperada do servidor, contate o suporte!");
          }
        } catch (error) {
          console.log(error);
        }
      };

      getTicketsWorker();
    }
  }, [props?.userData]);

  return (
    <div className={styles.mainContent}>
      <ModalTicketCreate show={show} setShow={setShow} />
      <ModalTicketCreateCategory
        show={showCreateCategory}
        setShow={setShowCreateCategory}
      />
      <div className={styles.titleContainer}>
        <h1>
          <FaTrafficLight style={{ marginRight: "10px" }} />
          {role === "USER"
            ? "Minhas infrações de trânsito"
            : "Infrações de trânsito"}
        </h1>
        {/* {role === "WORKER" && (
          <Form className="d-flex" style={{ marginRight: "50px" }}>
            <Form.Control
              type="search"
              placeholder="Pesquisar infrações..."
              className="me-2"
              aria-label="Search"
            />
          </Form>
        )} */}
      </div>
      {role === "WORKER" && (
        <div className={styles.contaierNewTicket}>
          <Button variant="primary" onClick={handleShowCreateCategory}>
            Criar nova categoria de infração
          </Button>
          <Button variant="primary" onClick={handleShow}>
            Aplicar infração
          </Button>
        </div>
      )}
      <div className={styles.ticketsContainer}>
        {ticketsData?.driver_tickets ? (
          Object.values(ticketsData?.driver_tickets).map((ticket, index) => (
            <TicketsCard
              key={index}
              role={role}
              ticketsData={ticket}
              userData={userData}
            />
          ))
        ) : ticketsData && ticketsData.length > 0 ? (
          ticketsData.map((value, index) => (
            <TicketsCard
              key={index}
              role={role}
              ticketsData={value}
              userData={userData}
            />
          ))
        ) : (
          <h3>
            {role === "USER"
              ? "Você não possui infrações pendentes!"
              : "Não existe categorias de infração!"}
          </h3>
        )}
      </div>
    </div>
  );
};

export default Home;
