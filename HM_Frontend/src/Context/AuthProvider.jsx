import React, { useEffect } from "react";
import { AuthContext } from "../Context/AuthContext";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const AuthProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(null);
  const [authPanel, setAuthPanel] = useState(false);
  const [authMode, setAuthMode] = useState("");

   axios.defaults.withCredentials = true;


  const navigate = useNavigate();

  const fetchCurrentUser = async () => {
    const userResponse = await axios.get("http://localhost:8080/me" , {withCredentials : true});

    const currentUserData = userResponse.data;
    console.log(currentUserData)
    setCurrentUser({ email: currentUserData.email , role: currentUserData.role , fullName : currentUserData.fullName });

  };

    useEffect(() => {
    fetchCurrentUser();
  }, []);


  const handleSignUp = async ({ email, password ,fullName ,role , specialization , qualification , hospitalName}, setMessage) => {

    console.log({email , password ,fullName , role , specialization , qualification , hospitalName})
    try {
      const response = await axios.post("http://localhost:8080/signup", {
        role,
        email,
        password,
        fullName ,
        specialization ,
        qualification ,
        hospitalName ,

      });

      console.log(response.data)
     return true;
    } catch (error) {
      if (error.response?.status === 409) {
        setMessage("Email already exist");
        console.log(error.response)
        return false;
      } else {
        console.error("Error creating account...", error);
      }
      return false;
    }
  };

  const handleLogin = async ({ email, password } ,setMessage) => {
    try {
      const response = await axios.post("http://localhost:8080/login", {
        email,
        password,
      } ,{ withCredentials: true });

      let token = response.data.token;
      setCurrentUser(response.data)

      if (token && token.startsWith("Bearer ")) {
        token = token.substring(7);
        return response;
      }
    } catch (error) {
      if (error.response?.status === 401) {
        setMessage("You entered wrong credentials")
        setCurrentUser(null);
      } else {
        console.error(error, "login failed");
        return false;
      }
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("authToken");
    setCurrentUser(null);
    navigate("/");
  };

  return (
    <div>
      <AuthContext.Provider
        value={{
          handleSignUp,
          handleLogin,
          currentUser,
          handleLogout,
          setAuthMode,
          setAuthPanel,
          authMode,
          authPanel,
        }}
      >
        {children}
      </AuthContext.Provider>
    </div>
  );
};

export default AuthProvider;
