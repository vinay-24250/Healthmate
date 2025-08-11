import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../Context/AuthContext";


const SignIn = ({ switchToSignUp }) => {
  const { handleLogin, setAuthPanel ,currentUser } = useContext(AuthContext)
  const [showPassword, setShowPassword] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const LoginHandler = async (e) => {
    e.preventDefault();

    if(currentUser != null){
      setMessage("Already logged in...")
      setAuthPanel(true)
      return false;
    }
      const loginData = await handleLogin({ email, password } ,setMessage);

     if(loginData && loginData.data && loginData.data.role){
      setAuthPanel(false)
     }

    const role = loginData.data.role;

    if (role === "DOCTOR") {
      navigate("/doctordash");
    }
    else if(role === "USER") {
      navigate("/userdash")
    }else{
      setMessage("Invalid credentials...");
      return false;
    }
    

    

    setEmail("");
    setPassword("");

    setTimeout(() => {
      setMessage("");
    }, 2000);
  };

  return (
    <>
      <div className="flex flex-col justify-center place-items-center bg-black">
        <div className="flex place-items-center flex-col py-10 h-screen w-[350px">
          <h2 className="font-[Poppins] text-white mb-10 text-3xl">Sign In</h2>
          <form
            onSubmit={LoginHandler}
            className="flex flex-col place-items-center gap-6"
          >
            <input
              value={email}
              onChange={(e) => {
                setEmail(e.target.value);
              }}
              className="h-8 p-2 border-2 border-yellow outline-none w-80 placeholder:text-gray-800 placeholder:font-thin placeholder:text-sm"
              type="email"
              placeholder="Enter your Email"
            />

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
              Sign In
            </button>
            <div className="flex justify-center text-sm">
              <h2 className="text-slate-50 font-thin font-[Poppins]">
                Don't have an account ?
              </h2>{" "}
              <span
                onClick={switchToSignUp}
                className="cursor-pointer text-yellow-200 font-[Poppins] font-thin hover:underline"
              >
                Sign Up
              </span>
            </div>
          </form>
          {message && <div className="text-white">{message}</div>}
        </div>
      </div>
    </>
  );
};

export default SignIn;
