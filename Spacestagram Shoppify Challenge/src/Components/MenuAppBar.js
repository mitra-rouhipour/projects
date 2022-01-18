import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';

export default function MenuAppBar() {

  return (
    <Box sx={{ flexGrow: 1 }} width="100%">
      <AppBar position="static" sx={{flexGrow: 1}}>
        <Toolbar>
          <Typography variant="h6" component="div">
            Spacestagram
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
