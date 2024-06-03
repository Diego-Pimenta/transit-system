import Button from "react-bootstrap/Button";
import Accordion from "react-bootstrap/Accordion";
import styles from "./ticketsCard.module.css";
import { useEffect, useState } from "react";
import ModalTicketPayment from "../ModalTicketPayment/modalTicketPayment";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";
import ModalTicketEdit from "../ModalTicketEdit/modalTicketEdit";
import ModalCaution from "../ModalCaution/modalCaution";

const TicketsCard = (props) => {
  const navigate = useNavigate();
  const [show, setShow] = useState(false);
  const [showEdit, setShowEdit] = useState(false);
  const [showCaution, setShowCaution] = useState(false);
  const [makeRequest, setMakeRequest] = useState(false);

  const handleShow = () => setShow(true);
  const handleShowCaution = () => setShowCaution(true);
  const handleShowEdit = () => setShowEdit(true);

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
        window.location.reload();
      } else {
        toast.error("Resposta inesperada do servidor, contate o suporte!");
      }
    } catch (error) {
      toast.error("Resposta inesperada do servidor, contate o suporte!");
    }
  };

  useEffect(() => {
    if (makeRequest === true) {
      const deleteCategoryTicket = async () => {
        try {
          const resp = await axios.delete(
            `http://localhost:8081/api/tickets/${props.ticketsData?.id}`,
            {
              headers: {
                Authorization: `${localStorage.getItem("token")}`,
              },
            }
          );

          if (resp.status === 204) {
            toast.success("Infração excluída com sucesso!");
            window.location.reload();
          } else {
            toast.error("Resposta inesperada do servidor, contate o suporte!");
          }
        } catch (error) {
          toast.error("Resposta inesperada do servidor, contate o suporte!");
        }
      };
      deleteCategoryTicket();
      setMakeRequest(false);
    }
  }, [makeRequest]);

  function formatPhoneNumber(phoneNumber) {
    const cleaned = ("" + phoneNumber).replace(/\D/g, "");

    if (cleaned.length !== 10) {
      return phoneNumber;
    }

    const match = cleaned.match(/^(\d{2})(\d{4})(\d{4})$/);

    if (match) {
      return `(${match[1]})${match[2]}-${match[3]}`;
    }

    return phoneNumber;
  }

  return (
    <>
      <ModalCaution
        show={showCaution}
        setShow={setShowCaution}
        makeRequest={makeRequest}
        setMakeRequest={setMakeRequest}
        title={"Tem certeza que deseja excluír esta categoria de infração?"}
      />
      <ModalTicketPayment
        show={show}
        setShow={setShow}
        title={props.ticketsData?.category}
      />
      <ModalTicketEdit
        show={showEdit}
        setShow={setShowEdit}
        title={props.ticketsData?.category}
        ticketsData={props.ticketsData}
      />
      <ToastContainer />

      <Accordion style={{ marginTop: "10px" }}>
        {props.role === "USER" ? (
          <Accordion.Item eventKey={"0"}>
            <Accordion.Header>
              <div className={styles.ticketsTitle}>
                <>
                  <span>Categoria: {props.ticketsData?.ticket?.category}</span>
                  <span>
                    Preço: R$ {props.ticketsData?.ticket?.cost}
                    ,00
                  </span>
                </>
              </div>
            </Accordion.Header>
            <Accordion.Body>
              <div className={styles.ticketsBody}>
                <div className={styles.ticketsData}>
                  <span>
                    Descrição: {props.ticketsData?.ticket?.description}
                  </span>
                  <span> Nome: {props.userData?.name || ""}</span>
                  <span> CPF: {props.userData?.cpf || ""}</span>
                  <span> Endereço: {props.userData?.address || ""}</span>
                  <span> Email: {props.userData?.email || ""}</span>
                  <span>
                    Celular:{" "}
                    {formatPhoneNumber(props.userData?.phone_number) || ""}
                  </span>
                  <span>
                    Data de emissão: {props.ticketsData?.emission_date}
                  </span>
                  <span>
                    Data de expiração: {props.ticketsData?.expiration_date}
                  </span>
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
                    <span>
                      Categoria:{" "}
                      {props?.userData
                        ? props.ticketsData?.ticket?.category
                        : props.ticketsData?.category}
                    </span>
                    <span>
                      Preço: R${" "}
                      {props?.userData
                        ? props.ticketsData?.ticket?.cost
                        : props.ticketsData?.cost}
                      ,00
                    </span>
                  </>
                </div>
              </Accordion.Header>
              <Accordion.Body>
                <div className={styles.ticketsBody}>
                  <div className={styles.ticketsData}>
                    <span>
                      Descrição:{" "}
                      {props.userData
                        ? props.ticketsData?.ticket?.description
                        : props.ticketsData?.description}
                    </span>
                    {props.userData && (
                      <>
                        <span>
                          Data de emissão: {props.ticketsData?.emission_date}
                        </span>
                        <span>
                          Data de expiração:
                          {props.ticketsData?.expiration_date}
                        </span>
                      </>
                    )}
                  </div>
                  {props?.userData ? (
                    <div className={styles.ticketsBtn}>
                      <Button variant="danger" onClick={() => deleteTicket()}>
                        Excluir infração
                      </Button>
                    </div>
                  ) : (
                    <div className={styles.ticketsBtn}>
                      <Button
                        variant="primary"
                        onClick={() => handleShowEdit()}
                      >
                        Editar infração
                      </Button>
                      <Button variant="danger" onClick={handleShowCaution}>
                        Excluir infração
                      </Button>
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
