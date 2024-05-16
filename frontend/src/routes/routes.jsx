import { Routes, Route, Navigate } from "react-router-dom";

import Login from "../pages/Login/index";
import Register from "../pages/Register/index";

import Home from "../pages/Home";

const RouteApp = ({ isAuthenticated, setUserData }) => {
  return (
    <Routes>
      <Route path="/" element={<Login setUserData={setUserData} />} />
      <Route path="/register" element={<Register />} />

      <Route
        path="/home"
        element={isAuthenticated ? <Home /> : <Navigate to="/" replace />}
      />
    </Routes>
  );
};



export default RouteApp;
