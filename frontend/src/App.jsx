import { useState } from "react";
import Routes from "./routes/routes";
import styles from "./App.module.css";
import Topbar from "./components/Topbar/topbar";
import { BrowserRouter as Router } from "react-router-dom";

function App() {
  const [isAuthenticated] = useState(true);

  // const login = () => {
  //   setIsAuthenticated(true);
  // };

  // const logout = () => {
  //   setIsAuthenticated(false);
  // };

  return (
    <Router>
      <div className={styles.App}>
        <Topbar />
        <Routes isAuthenticated={isAuthenticated} />
      </div>
    </Router>
  );
}

export default App;
