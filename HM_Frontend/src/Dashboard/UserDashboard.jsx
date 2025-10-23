/* eslint-disable no-unused-vars */
import React, { useContext, useState } from "react";
import { AuthContext } from "../Context/AuthContext";
import axios from "axios";
import { motion } from "framer-motion";

const UserDashboard = () => {
  const { handleLogout, authToken, currentUser } = useContext(AuthContext);
  const [sideBar, setSideBar] = useState(false);
  const [specialization, setSpecialization] = useState("");
  const [message, setMessage] = useState("");
  const [doctorsData, setDoctorsData] = useState([]);

  // Fetch doctors
  const searchDoctors = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/doctor/${specialization}`,{withCredentials : true} );

      if (!response || !response.data) {
        setMessage(`Error fetching doctors with specialization ${specialization}`);
      } else {
        setMessage("");
        setDoctorsData(response.data);
      }
    } catch (error) {
      console.error(error);
      setMessage("Error fetching doctors. Please try again.");
    }
  };

  return (
    <div className="container mx-auto min-h-screen bg-blue-300 flex flex-row relative overflow-hidden">

      <div
        className={`h-full w-[335px] bg-white fixed left-0 shadow-lg z-50
          transition-transform duration-500 ease-in-out
          ${sideBar ? "translate-x-0" : "-translate-x-full"}
        `}
      >
        <button
          className="h-7 w-7 bg-black text-white absolute top-4 right-4 rounded"
          onClick={() => setSideBar(false)}
        >
          ×
        </button>

        <div className="p-6 mt-12">
          <h2 className="text-xl font-semibold mb-3">Sidebar Menu</h2>
          <ul className="space-y-2">
            <li className="hover:text-blue-600 cursor-pointer">Dashboard</li>
            <li className="hover:text-blue-600 cursor-pointer">Profile</li>
            <li className="hover:text-blue-600 cursor-pointer">Settings</li>
          </ul>
        </div>
      </div>

      <div className="flex flex-col items-center w-full mt-10 px-4 sm:px-8">

    
        <button
          onClick={() => setSideBar(true)}
          className="self-start mb-4 bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 shadow"
        >
          ☰ Open Menu
        </button>

        <motion.div
          initial={{ y: -100, opacity: 0 }}
          animate={{ y: 0, opacity: 1 }}
          transition={{ duration: 0.8, ease: "easeOut" }}
          className="bg-gradient-to-r from-blue-500 to-indigo-600 text-white rounded-lg p-8 shadow-lg mb-6 w-full"
        >
          <h1 className="text-3xl font-bold">Hello {currentUser.fullName}</h1>
          <p className="text-lg mt-4">
            Your personal health companion. Connect with trusted doctors,
            get expert advice, and manage your health — anytime, anywhere.
          </p>
        </motion.div>

        {/* Doctor Search Section */}
        <motion.div
          initial={{ y: -80, opacity: 0 }}
          animate={{ y: 20, opacity: 1 }}
          transition={{ duration: 0.9, ease: "easeIn" }}
          className="bg-white shadow rounded-lg p-6 mb-6 w-full max-w-2xl"
        >
          <h2 className="text-xl font-semibold mb-2">Find Your Doctor</h2>
          <p className="text-gray-600 mb-4">
            Search for experienced doctors by their specialization.
          </p>

          <div className="flex flex-col sm:flex-row items-start sm:items-center gap-3">
            <select
              className="bg-gray-50 border border-gray-300 px-5 py-3 rounded-md w-full sm:w-64 focus:ring-1 focus:ring-blue-500 mr-4"
              value={specialization}
              onChange={(e) => setSpecialization(e.target.value)}
            >
              <option value="">Select Specialization</option>
              <option value="Dermatologist">Dermatologist</option>
              <option value="Cancer Specialist">Cancer Specialist</option>
              <option value="Physiotherapist">Physiotherapist</option>
            </select>

            <button
              onClick={searchDoctors}
              className="bg-blue-600 text-white px-5 py-2 rounded-md hover:bg-blue-700 shadow"
            >
              Search
            </button>
          </div>

          {message && <div className="mt-3 text-red-600 font-medium">{message}</div>}
        </motion.div>

        {doctorsData.length > 0 && (
          <div className="w-full max-w-5xl">
            <h3 className="text-2xl font-bold mb-4 text-gray-800">Available Doctors</h3>
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
              {doctorsData.map((doctorData) => (
                <div
                  key={doctorData.doc_id}
                  className="border rounded-lg p-5 shadow hover:shadow-lg transition-transform transform hover:-translate-y-1 bg-white"
                >
                  <h4 className="text-lg font-bold text-gray-800">{doctorData.fullName}</h4>
                  <p className="text-gray-600"><strong>Email:</strong> {doctorData.email}</p>
                  <p className="text-gray-600"><strong>Qualification:</strong> {doctorData.qualification}</p>
                  <p className="text-gray-600"><strong>Specialization:</strong> {doctorData.specialization}</p>
                </div>
              ))}
            </div>
          </div>
        )}

        {/* Logout Button */}
        <div className="mt-8 flex justify-center">
          <button
            onClick={handleLogout}
            className="bg-red-500 text-white px-6 py-2 rounded-md hover:bg-red-600 shadow"
          >
            Logout
          </button>
        </div>
      </div>
    </div>
  );
};

export default UserDashboard;
