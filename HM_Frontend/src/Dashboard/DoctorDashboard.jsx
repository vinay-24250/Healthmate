import React, { useContext } from 'react'
import { AuthContext } from '../Context/AuthContext'

const DoctorDashboard = () => {
const {handleLogout} = useContext(AuthContext)

  return (
    <>
    <div>
      Doctor Dashboard
    </div>

     <button
      onClick={handleLogout} className="bg-red-500 text-white">Logout
      </button>

      </>
  )
}

export default DoctorDashboard
