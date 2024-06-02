import { useState, useEffect } from "react";
import Routes from "./routes/routes";
import styles from "./App.module.css";
import Topbar from "./components/Topbar/topbar";
import { BrowserRouter as Router } from "react-router-dom";

function App() {
  const [userData, setUserData] = useState(null);

  return (
    <Router>
      <div className={styles.App}>
        <Topbar userData={userData} setUserData={setUserData} />
        <Routes setUserData={setUserData} userData={userData} />
      </div>
    </Router>
  );
}

export default App;
