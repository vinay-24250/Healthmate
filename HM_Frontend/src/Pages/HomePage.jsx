import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../Context/AuthContext";

const HomePage = () => {

const {setAuthMode , setAuthPanel ,currentUser} = useContext(AuthContext)
const navigate = useNavigate()


    const getStartedButton = async () =>{
         if(currentUser && currentUser.role === "USER"){
          await navigate("/userdash")
         }else if(currentUser && currentUser.role === "DOCTOR"){
          navigate("/doctordash")
         }
         else{
         setAuthMode("signin")
         setAuthPanel(true)
         }
    }

  return (
    <>
      <section className="flex flex-col md:flex-row pt-20 md:pt-0 items-center md:h-[600px] h-[800px] md:justify-start md:place-items-center bg-blue-300">
        <div className="md:max-w-max space-y-6 p-10 md:pt-20">
          <h2 className="text-4xl font-bold text-gray-800">
            Take Control of Your Health
          </h2>
          <p className="text-gray-600">
            HealthMate helps you track prescriptions, set medicine reminders,
            and keep digital medical records — all in one place.
          </p>
          <button
          onClick={getStartedButton} className="bg-yellow-300 text-black px-6 py-2 rounded hover:bg-yellow-400">
            Get Started
          </button>
        </div>
        <div className="md:w-1/3  w-[300px] h-[300px] mt-1 md:mt-0 md:mb-32 flex flex-col">
          <img src="/Online Doctor-rafiki.svg" alt="Health illustration" />
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-white text-center text-sm text-gray-500 py-4">
        &copy; 2025 HealthMate. All rights reserved.
      </footer>
    </>
  );
};

export default HomePage;
