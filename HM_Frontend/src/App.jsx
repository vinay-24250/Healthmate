import "./App.css";
import { Routes, Route } from "react-router-dom";
import Header from "./Components/Header";
import { useContext } from "react";
import UserDashboard from "./Dashboard/UserDashboard";;
import { AuthContext } from "./Context/AuthContext";
import HomePage from "./Pages/HomePage";
import SignUp from "./Auth/SignUp";
import SignIn from "./Auth/SignIn";
import DoctorDashboard from "./Dashboard/DoctorDashboard";

function App() {

const {currentUser} = useContext(AuthContext)

  return (
    <>
    <Header />
      <Routes>
        <Route path="/" element={<HomePage />}></Route>
        <Route path="/signup" element={<SignUp />}></Route>
        <Route path="/signin" element={<SignIn />}></Route>
       {currentUser && <Route path="/userdash" element={<UserDashboard />}></Route>}
        {currentUser && <Route path="/doctordash" element={<DoctorDashboard />}></Route>}
      </Routes>
    </>
  );
}

export default App;
