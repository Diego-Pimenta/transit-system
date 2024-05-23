import { Routes, Route, Navigate } from "react-router-dom";

import Login from "../pages/Login/index";
import Register from "../pages/Register/index";

import Home from "../pages/Home";
import Users from "../pages/Users";
import UserDetails from "../pages/UserDatails";

const RouteApp = ({ isAuthenticated, setUserData, userData }) => {
  return (
    <Routes>
      <Route path="/" element={<Login setUserData={setUserData} />} />
      <Route path="/register" element={<Register />} />

      <Route
        path="/home"
        element={
          isAuthenticated ? (
            <Home userData={userData} />
          ) : (
            <Navigate to="/" replace />
          )
        }
      />

      <Route
        path="/users"
        element={
          isAuthenticated ? (
            <Users userData={userData} />
          ) : (
            <Navigate to="/" replace />
          )
        }
      />

      <Route
        path="/users/:id"
        element={
          isAuthenticated ? (
            <UserDetails userData={userData} />
          ) : (
            <Navigate to="/" replace />
          )
        }
      />
    </Routes>
  );
};

export default RouteApp;
