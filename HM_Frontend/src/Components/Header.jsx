import React from 'react'
import Navbar from './Navbar'

const Header = () => {
  return (
    <>
    <div className='flex justify-between'>
        <img className='object-contain h-22 md:h-20 w-44 md:w-auto px-1 md:px-10 bg-blue-100' src="logo.PNG" alt="HealhMate" />
        <Navbar />
    </div>
    </>
  )
}

export default Header
