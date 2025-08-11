import React, { useContext, useState } from "react";
import { AuthContext } from "../Context/AuthContext";

const SignUp = ({ switchToSignIn }) => {
  const { handleSignUp, setAuthMode } = useContext(AuthContext);

  const [showPassword, setShowPassword] = useState(false);
  const [fullName, setFullName] = useState("");
  const [role, setRole] = useState("USER");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [specialization, setSpecialization] = useState("")
  const [qualification, setQualification] = useState("")
  const [hospitalName, setHospitalName] = useState("")

  const submitHandler = async (e) => {
    try {
      e.preventDefault();

      if (!role || (role !== "USER" && role !== "DOCTOR")) {
  setMessage("Please select a valid role");
  return;
}

      const success = await handleSignUp(
        { email, password, fullName, role ,specialization ,qualification , hospitalName  },
        setMessage
      );
      

      if (success) {
        setAuthMode("signin");
      }
      setFullName("");
      setEmail("");
      setPassword("");
      setSpecialization("")
      setQualification("")
      setHospitalName("")
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <div className="flex flex-col justify-center place-items-center ">
        <div className="flex place-items-center flex-col py-10 h-[400px] w-[350px] ">
          <h2 className="font-[Poppins] text-white mb-10 text-3xl">Sign Up</h2>
          <form
            onSubmit={submitHandler}
            className="flex flex-col place-items-center gap-6"
          >
            <select
              value={role}
              onChange={(e) => {
                setRole(e.target.value.toUpperCase());
              }}
              className="h-8 p-2 border-2 border-yellow outline-none w-80 bg-emerald-200 placeholder:text-gray-800 placeholder:font-thin placeholder:text-sm"
          
            >
              
              <option value="USER">USER</option>
              <option value="DOCTOR">DOCTOR</option>
            </select>

            <input
              value={fullName}
              onChange={(e) => {
                setFullName(e.target.value);
              }}
              className="h-8 p-2 border-2 border-yellow outline-none w-80 placeholder:text-gray-800 placeholder:font-thin placeholder:text-sm"
             
              type="text"
              placeholder="Enter your Name"
            />
            <input
              value={email}
              onChange={(e) => {
                setEmail(e.target.value);
              }}
              className="h-8 p-2 border-2 border-yellow outline-none w-80 placeholder:text-gray-800 placeholder:font-thin placeholder:text-sm"
            
              type="email"
              placeholder="Enter your Email"
            />

            { role === "DOCTOR" && <input 
            
            value={specialization}
            onChange={(e)=>{
              setSpecialization(e.target.value)
            }}
            className="h-8 p-2 border-2 border-yellow outline-none w-80 placeholder:text-gray-800 placeholder:font-thin placeholder:text-sm"
            type="text"
            placeholder="Enter specialization" />}

             { role === "DOCTOR" && <input 
            
            value={qualification}
            onChange={(e)=>{
              setQualification(e.target.value)
            }}
            className="h-8 p-2 border-2 border-yellow outline-none w-80 placeholder:text-gray-800 placeholder:font-thin placeholder:text-sm"
            type="text"
            placeholder="Enter qualification" />}

            { role === "DOCTOR" && <input 
            
            value={hospitalName}
            onChange={(e)=>{
              setHospitalName(e.target.value)
            }}
            className="h-8 p-2 border-2 border-yellow outline-none w-80 placeholder:text-gray-800 placeholder:font-thin placeholder:text-sm"
            type="text"
            placeholder="Enter Hospital's name" />}


            <div className="h-8 border-2 border-yellow outline-none w-80 bg-white flex justify-start">
              <input
                value={password}
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
                className="outline-none p-2 h-7 w-80 placeholder:text-gray-800 placeholder:font-thin placeholder:text-sm"
                type={showPassword ? "text" : "password"}
                placeholder="Create Password"
                minLength={10}
              />
              <button
                type="button"
                onClick={() => setShowPassword((prev) => !prev)}
                className="relative right-2 font-[Poppins] text-xs"
              >
                {showPassword ? "Hide" : "Show"}
              </button>
            </div>

            <button
              type="submit"
              className="w-20 bg-gray-300 hover:bg-white text-sm py-1 rounded-sm hover:scale-90"
            >
              Sign Up
            </button>
            <div className="flex justify-center text-sm">
              <h2 className="text-slate-50 font-thin font-[Poppins]">
                Already have an account ?
              </h2>{" "}
              <span
                onClick={switchToSignIn}
                className="cursor-pointer text-yellow-200 font-[Poppins] font-thin hover:underline"
              >
                Sign In
              </span>
            </div>
          </form>
          {message && (
            <div className="text-white font-[Poppins] text-sm">{message}</div>
          )}
        </div>
      </div>
    </>
  );
};

export default SignUp;
