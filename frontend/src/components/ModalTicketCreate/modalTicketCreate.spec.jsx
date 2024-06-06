import React from "react";
import { render, fireEvent, screen, waitFor } from "@testing-library/react";
import ModalTicketCreate from "./modalTicketCreate";
import axios from "axios";

jest.mock("axios");

describe("ModalTicketCreate", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  it("renders modal correctly", () => {
    render(<ModalTicketCreate show={true} />);

    expect(
      screen.getByText("Aplicar infração a algum usuário")
    ).toBeInTheDocument();
    expect(screen.getByPlaceholderText("CPF")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Data da emissão")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Hora da emissão")).toBeInTheDocument();
  });

  it("fills form fields correctly", () => {
    render(<ModalTicketCreate show={true} />);

    fireEvent.change(screen.getByPlaceholderText("CPF"), {
      target: { value: "12345678900" },
    });
    fireEvent.change(screen.getByPlaceholderText("Data da emissão"), {
      target: { value: "2024-06-06" },
    });
    fireEvent.change(screen.getByPlaceholderText("Hora da emissão"), {
      target: { value: "12:00" },
    });

    expect(screen.getByPlaceholderText("CPF").value).toBe("123.456.789-00");
    expect(screen.getByPlaceholderText("Data da emissão").value).toBe(
      "2024-06-06"
    );
    expect(screen.getByPlaceholderText("Hora da emissão").value).toBe("12:00");
  });

  it("closes modal when clicking close button", () => {
    const setShowMock = jest.fn();
    render(<ModalTicketCreate show={true} setShow={setShowMock} />);

    fireEvent.click(screen.getByText("Fechar"));
    expect(setShowMock).toHaveBeenCalledWith(false);
  });
});
