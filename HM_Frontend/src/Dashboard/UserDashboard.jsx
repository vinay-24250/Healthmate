import React, { useContext, useState } from "react";
import { AuthContext } from "../Context/AuthContext";
import axios from "axios";

const UserDashboard = () => {
  const { handleLogout, authToken, currentUser } = useContext(AuthContext);

  const [specialization, setSpecialization] = useState("");
  const [message, setMessage] = useState("");
  const [doctorsData, setDoctorsData] = useState([]);

  const searchDoctors = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/doctor/${specialization}`,
        {
          headers: {
            Authorization: `Bearer ${authToken}`,
            "Content-Type": "application/json",
          },
        }
      );

      console.log(response.data)

      if (!response) {
        setMessage(`Error fetching doctors with specialization ${specialization}`);
      }else {
        setMessage("");
        setDoctorsData(response.data);
      }
    } catch (error) {
      console.error(error);
      setMessage("Error fetching doctors. Please try again.");
    }
  };

  return (
    <div className="container mx-auto h-screen px-4 py-6 bg-blue-300">
      
      {/* Hero Section */}
      <div className="bg-gradient-to-r from-blue-500 to-indigo-600 text-white rounded-lg p-8 shadow-lg mb-6">
        <h1 className="text-3xl font-bold mb-2">Welcome to HealthMate</h1>
        <p className="text-lg">
          Your personal health companion. Connect with trusted doctors,
          get expert advice, and manage your health — anytime, anywhere.
        </p>
        <p className="mt-2 text-sm">
          Logged in as <span className="font-semibold">{currentUser.fullname}</span>
        </p>
      </div>

     {/* Search Section */}
<div className="bg-white shadow rounded-lg p-6 mb-6">
  <h2 className="text-xl font-semibold mb-2">Find Your Doctor</h2>
  <p className="text-gray-600 mb-4">
    Search for experienced doctors by their specialization.
    Our database connects you with trusted healthcare professionals.
  </p>

  <div className="flex flex-col sm:flex-row items-start sm:items-center gap-3">
    {/* Specialization Dropdown */}
    <select
      className="bg-gray-50 border border-gray-300 px-5 py-3 rounded-md w-full sm:w-64 focus:ring-1 focus:ring-blue-500 mr-4"
      value={specialization}
      onChange={(e) => setSpecialization(e.target.value)}
    >
      <option className="px-4 mr-3 border-t-2 border-x-2 border-black" value="">Select Specialization</option>
      <option className="px-4 mr-3 border-t-2 border-x-2 border-black" value="Dermatologist">Dermatologist</option>
      <option className="px-4 mr-3 border-t-2 border-x-2 border-black" value="Cancer Specialist">Cancer Specialist</option>
      <option className="px-4 mr-3 border-t-2 border-x-2 border-black" value="Physiotherapist">Physiotherapist</option>
    </select>

    {/* Search Button */}
    <button
      onClick={searchDoctors}
      className="bg-blue-600 text-white px-5 py-2 rounded-md hover:bg-blue-700 shadow"
    >
      Search
    </button>
  </div>

  {message && <div className="mt-3 text-red-600 font-medium">{message}</div>}
</div>


      {/* Doctors Section */}
      {doctorsData.length > 0 && (
        <div>
          <h3 className="text-2xl font-bold mb-4 text-gray-800">Available Doctors</h3>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {doctorsData.map((doctor) => (
              <div
                key={doctor.doc_id}
                className="border rounded-lg p-5 shadow hover:shadow-lg transition-transform transform hover:-translate-y-1 bg-white"
              >
                <h4 className="text-lg font-bold text-gray-800">{doctor.fullName}</h4>
                <p className="text-gray-600"><strong>Email:</strong> {doctor.email}</p>
                <p className="text-gray-600"><strong>Qualification:</strong> {doctor.qualification}</p>
                <p className="text-gray-600"><strong>Specialization:</strong> {doctor.specialization}</p>
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
  );
};

export default UserDashboard;
