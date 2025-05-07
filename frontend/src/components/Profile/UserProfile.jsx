import React from 'react'
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import { Button } from '@mui/material';

function UserProfile() {

  const handleLogout = ()=>{

  }

  return (
    <div className='min-h-[80vh] flex flex-col justify-center items-center'>
      <AccountBoxIcon sx={{fontSize:"9rem"}}/>
      <h1 className='py-5 text-2xl font-semibold'>Pratik Malviya</h1>
      <p>Email : pratikmalviya@gmail.com</p>
      <Button variant='contained' sx={{margin:"2rem 0rem"}} onClick={handleLogout}>Logout</Button>
    </div>
  )
}

export default UserProfile
