import { useState } from "react";
import Routes from "./routes/routes";
import "./App.css";

function App() {
  const [isAuthenticated] = useState(false);

  // const login = () => {
  //   setIsAuthenticated(true);
  // };

  // const logout = () => {
  //   setIsAuthenticated(false);
  // };

  return (
    <>
      <div className="App">
        <Routes isAuthenticated={isAuthenticated} />
      </div>
    </>
  );
}

export default App;
