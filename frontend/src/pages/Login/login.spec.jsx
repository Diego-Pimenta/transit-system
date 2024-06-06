import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import Login from ".";
import { BrowserRouter } from "react-router-dom";
import MockAdapter from "axios-mock-adapter";
import axios from "axios";

const mock = new MockAdapter(axios);

beforeEach(() => {
  fetch.resetMocks();
  mock.reset();
});

describe("<Login /> Integration Test", () => {
  it("submits the form and handles successful login", async () => {
    mock.onPost("http://localhost:8081/api/auth/login").reply(200, {
      token: "fake-token",
      user: { id: 1, role: "user" },
    });

    render(
      <BrowserRouter>
        <Login setUserData={jest.fn()} />
      </BrowserRouter>
    );

    fireEvent.change(screen.getByPlaceholderText("000.000.000-00"), {
      target: { value: "12345678900" },
    });

    fireEvent.change(screen.getByPlaceholderText("Password"), {
      target: { value: "password123" },
    });

    fireEvent.click(screen.getByTestId("recaptcha-mock"));

    fireEvent.click(screen.getByText("Entrar"));

    await waitFor(() => {
      expect(mock.history.post.length).toBe(1);
      expect(localStorage.getItem("token")).toBe("Bearer fake-token");
      expect(localStorage.getItem("isAuthenticated")).toBe("true");
      expect(localStorage.getItem("id")).toBe("1");
      expect(localStorage.getItem("role")).toBe("user");
    });

    await waitFor(() => {
      expect(window.location.href).toContain("http://localhost/");
    });
  });

  it("displays an error message on login failure", async () => {
    mock.onPost("http://localhost:8081/api/auth/login").reply(401, {
      message: "Cpf ou senha incorreta!",
    });

    render(
      <BrowserRouter>
        <Login setUserData={jest.fn()} />
      </BrowserRouter>
    );

    fireEvent.change(screen.getByPlaceholderText("000.000.000-00"), {
      target: { value: "12345678900" },
    });

    fireEvent.change(screen.getByPlaceholderText("Password"), {
      target: { value: "wrongpassword" },
    });

    fireEvent.click(screen.getByTestId("recaptcha-mock"));

    fireEvent.click(screen.getByText("Entrar"));

    await waitFor(() => {
      expect(screen.getByText("Cpf ou senha incorreta!")).toBeInTheDocument();
    });

    expect(mock.history.post.length).toBe(1);
  });
});
