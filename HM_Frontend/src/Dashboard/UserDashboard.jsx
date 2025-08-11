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

      if (!response) {
        setMessage(`Error fetching doctors with specialization ${specialization}`);
      } else {
        console.log(response.data);
        setDoctorsData(response.data);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="container mx-auto px-4 py-6">
      <div className="flex flex-col items-start gap-4">
        <h1 className="text-2xl font-bold text-gray-800">User Dashboard</h1>
        <h2 className="text-lg font-semibold text-blue-600">
          Welcome, {currentUser.fullName}
        </h2>

        <div className="flex flex-col sm:flex-row items-start sm:items-center gap-2 w-full">
          <select
            className="bg-yellow-100 border border-black px-3 py-2 rounded-md w-full sm:w-64"
            value={specialization}
            onChange={(e) => setSpecialization(e.target.value)}
            type="text"
            placeholder="Enter specialization"
          >
<option value="Dermatologist">Dermatologist</option>
<option value="Cancer Specialist">Cancer Specialist</option>
<option value="Physiotherepist">Physotherepist</option>
          </select>
          <button
            onClick={searchDoctors}
            className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600"
          >
            Search
          </button>
        </div>

        {message && (
          <div className="text-red-600 font-medium">{message}</div>
        )}

        <div className="w-full overflow-x-auto mt-4">
          <table className="w-full table-auto border border-gray-300 text-left">
            <thead className="bg-gray-200">
              <tr>
                <th className="p-2 border border-gray-300">Doctor Name</th>
                <th className="p-2 border border-gray-300">Email</th>
                <th className="p-2 border border-gray-300">Qualification</th>
                <th className="p-2 border border-gray-300">Specialization</th>
              </tr>
            </thead>
            <tbody>
              {doctorsData.map((doctor) => (
                <tr key={doctor.doc_id} className="hover:bg-gray-50">
                  <td className="p-2 border border-gray-300">{doctor.fullName}</td>
                  <td className="p-2 border border-gray-300">{doctor.email}</td>
                  <td className="p-2 border border-gray-300">{doctor.qualification}</td>
                  <td className="p-2 border border-gray-300">{doctor.specialization}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        <button
          onClick={handleLogout}
          className="mt-4 bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600"
        >
          Logout
        </button>
      </div>
    </div>
  );
};

export default UserDashboard;
