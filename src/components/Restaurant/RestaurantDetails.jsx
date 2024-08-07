import { Grid } from '@mui/material'
import React from 'react'

const RestaurantDetails = () => {
  return (
    <div className='px-5 lg:px-20 mt-5'>
      <section>
        <h3 className='text-gray-500 '></h3>
        <div>
            <Grid container spacing={2}>
            <Grid item xs={12}>
                    <img className = "w-full h-[40vh] object-cover" 
                    src="https://assets.cntraveller.in/photos/63d8e5103d7229d4cf308f01/16:9/w_1024%2Cc_limit/Prequel-lead.jpg" 
                    alt="" />
                    
                </Grid>
                <Grid item xs={12} lg={6}>
                    <img className = "w-full h-[40vh] object-cover" 
                    src="https://b.zmtcdn.com/data/pictures/9/113249/0c361b1041d8d0c7e7e139a9db4ab26f.jpg?fit=around|960:500&crop=960:500;*,*" 
                    alt="" />
                    
                </Grid>
                <Grid item xs={12} lg={6}>
                    <img className = "w-full h-[40vh] object-cover" 
                    src="https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg" 
                    alt="" />
                    
                </Grid>
            </Grid>
        </div>
      </section>
    </div>
  )
}

export default RestaurantDetails
