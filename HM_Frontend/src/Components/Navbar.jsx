import { useContext, useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import { Menu } from "lucide-react";
import { AuthContext } from "../Context/AuthContext";
import SignUp from "../Auth/SignUp";
import SignIn from "../Auth/SignIn";
import MoreButton from "./MoreButton";

const Navbar = () => {
  const {authMode ,authPanel ,setAuthMode ,setAuthPanel ,currentUser} = useContext(AuthContext)
  const [open, setOpen] = useState(false)

  const authPanelRef = useRef();
  const moreButtonRef = useRef();

   useEffect(() => {
      const handleClickOutside = (e) => {
        if (authPanelRef.current && !authPanelRef.current.contains(e.target)) {
          setAuthPanel(false);
        }
        if (moreButtonRef.current && !moreButtonRef.current.contains(e.target)) {
          setOpen(false);
        }
      };
  
      document.addEventListener("mousedown", handleClickOutside);
  
      return () => {
        document.removeEventListener("mousedown", handleClickOutside);
      };
    }, [setAuthPanel]);

  return (
    <>
      <div className="flex h-20 md:h-20 md:w-screen sm:w-full w-80 justify-start place-items-center bg-gray-600">
        <div className="w-52 h-12 md:w-full md:h-12 flex justify-end md:justify-end gap-1 md:pr-10 text-[10px] font-sans place-items-center md:gap-10">
          <Link
            to="/"
            className="hidden md:inline-block md:text-sm md:h-9 px-4 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
          >
            Home
          </Link>

          {currentUser && <Link
            to= {currentUser.role === "USER" ? "/userdash" : "/doctordash"}
            className="hidden md:inline-block md:text-sm md:h-9 px-4 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
          >
            Dashboard
          </Link>
}
          <button
            onClick={() => {
              setAuthPanel(true);
              setAuthMode("signin");
            }}
            className="hidden md:inline-block text-sm h-9 px-4 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
          >
            Sign In
          </button>

          <button
            onClick={() => {
              setAuthPanel(true);
              setAuthMode("signup");
            }}
            className="md:text-sm px-4 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
          >
            Sign Up
          </button>

          <button
          ref={moreButtonRef}
            onClick={() => setOpen(true)}
            className="md:hidden px-4 py-2 text-white focus-within:text-slate-300"
          >
            <Menu className="h-7 w-8"></Menu>
          </button>

          <MoreButton open={open} setOpen= {setOpen}/>

          <div
            ref={authPanelRef}
            className={`fixed top-20 right-0 z-50 h-screen pt-20 w-full md:w-[400px] bg-black transition-transform duration-500 transform ${
              authPanel ? "translate-x-0" : "translate-x-full"
            }`}
          >
            {authMode === "signup" ? (
              <SignUp switchToSignIn={() => setAuthMode("signin")} />
            ) : (
              <SignIn switchToSignUp={() => setAuthMode("signup")} />
            )}
          </div>

          
        </div>
      </div>
    </>
  );
};

export default Navbar;
