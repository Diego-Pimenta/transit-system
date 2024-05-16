import { useState, useEffect } from "react";
import Routes from "./routes/routes";
import styles from "./App.module.css";
import Topbar from "./components/Topbar/topbar";
import { BrowserRouter as Router } from "react-router-dom";

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState();
  const [userData, setUserData] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setIsAuthenticated(true);
    } else {
      setIsAuthenticated(false);
    }
  }, []);

  return (
    <Router>
      <div className={styles.App}>
        <Topbar userData={userData} />
        <Routes isAuthenticated={isAuthenticated} setUserData={setUserData} />
      </div>
    </Router>
  );
}


export default App;
