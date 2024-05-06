import { Routes, Route, Navigate } from "react-router-dom";

import Login from "../pages/Login/index";
import Register from "../pages/Register/index";

import PropTypes from "prop-types";
import Home from "../pages/Home";

const RouteApp = ({ isAuthenticated }) => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/register" element={<Register />} />

      <Route
        path="/home"
        element={isAuthenticated ? <Home /> : <Navigate to="/" replace />}
      />
    </Routes>
  );
};

RouteApp.propTypes = {
  isAuthenticated: PropTypes.bool.isRequired,
};

export default RouteApp;
