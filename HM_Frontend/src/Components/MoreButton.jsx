import React, { useContext, useRef } from 'react'
import { AuthContext } from '../Context/AuthContext';
import { Link } from 'react-router-dom';

const MoreButton = ({open ,setOpen}) => {

  const {currentUser , setAuthMode ,setAuthPanel} = useContext(AuthContext)
  const moreButtonRef = useRef();


  return (
    <>
    <div
            ref={moreButtonRef}
            className={`md:hidden fixed top-20 right-0 z-50 h-screen w-40 bg-blue-100 transition-transform duration-500 transform ${
              open ? "translate-x-0" : "translate-x-full"
            }`}
          >
            <ul className="flex flex-col space-y-4 p-4 font-[Poppins] text-gray-800 text-sm gap-5 pt-10">
              <li>
                <Link
                  to="/"
                  className="px-2 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
                >
                  🏠 Home
                </Link>
              </li>
              <li>
                <button
                  onClick={() => {
                    setAuthPanel(true);
                    setAuthMode("signin");
                  }}
                  className="md:inline-block text-sm h-9 px-4 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
                >
                  Sign In
                </button>
              </li>
              <li>
                <Link
                  to="/services"
                  className="px-2 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
                >
                  🛠️ Services
                </Link>
              </li>
              <li>
                <Link
                  to="/contact"
                  className="px-2 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
                >
                  📞 Contact
                </Link>
              </li>
              <li>
                <Link
                  to="/faq"
                  className="px-2 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
                >
                  ❓ FAQ
                </Link>
              </li>
              <li>
               {currentUser && <Link
                  to="/userdash"
                  onClick={()=>setOpen(false)}
                  className="px-2 py-2 rounded font-semibold bg-gray-300 text-black hover:bg-green-500 hover:text-white"
                >
                  👤 Dashboard
                </Link>
                } 
              </li>
            </ul>
          </div>
    </>
  )
}

export default MoreButton
