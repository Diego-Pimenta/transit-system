import { Routes, Route, Navigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Login from "../pages/Login/index";
import Register from "../pages/Register/index";

import Home from "../pages/Home";
import Users from "../pages/Users";
import UserDetails from "../pages/UserDatails";
import Contact from "../pages/Contact";

const RouteApp = ({ setUserData, userData }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(
    localStorage.getItem("isAuthenticated") === "true"
  );
  useEffect(() => {
    const authStatus = localStorage.getItem("isAuthenticated");
    if (authStatus === "true") {
      setIsAuthenticated(authStatus);
    }
  }, []);

  return (
    <Routes>
      {/*       
    //   <Route
    //     path="/"
    //     element={
    //       isAuthenticated ? (
    //         <Home userData={userData} />
    //       ) : (
    //         <Login setUserData={setUserData} />
    //       )
    //     }
    //   /> */}
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

      <Route
        path="/contact"
        element={
          isAuthenticated ? (
            <Contact userData={userData} />
          ) : (
            <Navigate to="/" replace />
          )
        }
      />

      <Route
        path="*"
        element={
          isAuthenticated ? (
            <Home userData={userData} />
          ) : (
            <Navigate to="/" replace />
          )
        }
      />
    </Routes>
  );
};

export default RouteApp;
