import React, { useContext } from 'react';
import { AuthContext } from '../Context/AuthContext';

const DoctorDashboard = () => {
  const { handleLogout } = useContext(AuthContext);

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      {/* Header */}
      <header className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold text-gray-800">Doctor Dashboard</h1>
        <button
          onClick={handleLogout}
          className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg shadow"
        >
          Logout
        </button>
      </header>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Profile Section */}
        <div className="bg-white rounded-xl shadow p-6">
          <div className="flex flex-col items-center">
            <img
              src="https://via.placeholder.com/100"
              alt="Doctor Profile"
              className="w-24 h-24 rounded-full border-4 border-blue-500"
            />
            <h2 className="mt-4 text-xl font-semibold">Dr. John Doe</h2>
            <p className="text-gray-500">Cardiologist</p>
            <p className="mt-2 text-sm text-gray-600">
              Passionate about patient care and heart health. 10+ years experience.
            </p>
          </div>
        </div>

        {/* Appointments Section */}
        <div className="lg:col-span-2 bg-white rounded-xl shadow p-6">
          <h3 className="text-lg font-semibold mb-4">Upcoming Appointments</h3>
          <ul className="space-y-3">
            <li className="p-3 bg-gray-50 rounded-lg shadow-sm">
              <strong>Patient:</strong> Jane Smith | <strong>Date:</strong> 15 Aug 2025
            </li>
            <li className="p-3 bg-gray-50 rounded-lg shadow-sm">
              <strong>Patient:</strong> Mike Johnson | <strong>Date:</strong> 16 Aug 2025
            </li>
          </ul>
        </div>
      </div>

      {/* Additional Features */}
      <div className="mt-6 bg-white rounded-xl shadow p-6">
        <h3 className="text-lg font-semibold mb-4">Patient Search</h3>
        <input
          type="text"
          placeholder="Search patient by name..."
          className="w-full border rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
      </div>
    </div>
  );
};

export default DoctorDashboard;
