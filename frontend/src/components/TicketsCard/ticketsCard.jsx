import Button from "react-bootstrap/Button";
import Accordion from "react-bootstrap/Accordion";
import styles from "./ticketsCard.module.css";
import { useState } from "react";
import ModalTicketPayment from "../ModalTicketPayment/modalTicketPayment";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";

const TicketsCard = (props) => {
  const navigate = useNavigate();
  const [show, setShow] = useState(false);
  const [showEdit, setShowEdit] = useState(false);

  const handleShow = () => setShow(true);

  const deleteTicket = async () => {
    try {
      const resp = await axios.delete(
        `http://localhost:8081/api/driver-tickets/${props.ticketId}`,
        {
          headers: {
            Authorization: `${localStorage.getItem("token")}`,
          },
        }
      );

      if (resp.status === 204) {
        toast.success("Infração excluída com sucesso!");
      } else {
        toast.error("Resposta inesperada do servidor, contate o suporte!");
      }
    } catch (error) {
      toast.error("Resposta inesperada do servidor, contate o suporte!");
    }
  };

  return (
    <>
      <ModalTicketPayment
        show={show}
        setShow={setShow}
        title={props.ticketsData?.category}
      />
      <ToastContainer />

      <Accordion style={{ marginTop: "10px" }}>
        {props.role === "USER" ? (
          <Accordion.Item eventKey={"0"}>
            <Accordion.Header>
              <div className={styles.ticketsTitle}>
                <>
                  <span>Categoria: {props.ticketsData?.category}</span>
                  <span>
                    Preço: R$ {props.ticketsData?.cost}
                    ,00
                  </span>
                </>
              </div>
            </Accordion.Header>
            <Accordion.Body>
              <div className={styles.ticketsBody}>
                <div className={styles.ticketsData}>
                  <span> Descrição: {props.ticketsData?.description}</span>
                  <span> Nome: {props.userData?.name || ""}</span>
                  <span> CPF: {props.userData?.cpf || ""}</span>
                  <span> Endereço: {props.userData?.address || ""}</span>
                  <span> Email: {props.userData?.email || ""}</span>
                  <span> Celular: {props.userData?.phone_number || ""}</span>
                </div>
                <div className={styles.ticketsBtn}>
                  <Button
                    variant="primary"
                    style={{ width: "150px" }}
                    onClick={handleShow}
                  >
                    Pagar
                  </Button>
                </div>
              </div>
            </Accordion.Body>
          </Accordion.Item>
        ) : (
          <>
            <Accordion.Item eventKey="0">
              <Accordion.Header>
                <div className={styles.ticketsTitle}>
                  <>
                    <span>Categoria: {props.ticketsData?.category}</span>
                    <span>
                      Preço: R$ {props.ticketsData?.cost}
                      ,00
                    </span>
                  </>
                </div>
              </Accordion.Header>
              <Accordion.Body>
                <div className={styles.ticketsBody}>
                  <div className={styles.ticketsData}>
                    <span> Descrição: {props.ticketsData?.description}</span>
                  </div>
                  {props?.userData ? (
                    <div className={styles.ticketsBtn}>
                      <Button variant="danger" onClick={() => deleteTicket()}>
                        Excluir infração
                      </Button>
                    </div>
                  ) : (
                    <div className={styles.ticketsBtn}>
                      <Button variant="primary">Editar infração</Button>
                    </div>
                  )}
                </div>
              </Accordion.Body>
            </Accordion.Item>
          </>
        )}
      </Accordion>
    </>
  );
};

export default TicketsCard;
