import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import AuthProvider from "./Context/AuthProvider";
import { BrowserRouter } from "react-router-dom";
import axios from "axios";

axios.defaults.withCredentials = true;


createRoot(document.getElementById("root")).render(
  <StrictMode>
    <BrowserRouter>
    <AuthProvider>
      <App />
    </AuthProvider>
    </BrowserRouter>
  </StrictMode>
);
