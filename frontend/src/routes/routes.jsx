import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Login from "../pages/Login/index";
import Register from "../pages/Register/index";

import PropTypes from "prop-types";

const RouteApp = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* <Route
          path="/register"
          element={
            isAuthenticated ? (
              <Navigate to="/" replace />
            ) : (
              <Navigate to="/register" replace />
            )
          }
        /> */}
      </Routes>
    </Router>
  );
};

RouteApp.propTypes = {
  isAuthenticated: PropTypes.bool.isRequired,
};

export default RouteApp;
