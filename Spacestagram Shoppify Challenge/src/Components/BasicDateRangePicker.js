import * as React from 'react';
import TextField from '@mui/material/TextField';
import DateRangePicker from '@mui/lab/DateRangePicker';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import { Stack } from '@mui/material';

export default function BasicDateRangePicker({onSubmitClick}) {
  const [value, setValue] = React.useState([null, null]);

  return (
    <Stack direction="row" spacing={2}>
        <LocalizationProvider dateAdapter={AdapterDateFns}>
        <DateRangePicker
            startText="Start"
            endText="End"
            value={value}
            onChange={(newValue) => {
            setValue(newValue);
            }}
            renderInput={(startProps, endProps) => (
            <React.Fragment>
                <TextField {...startProps} />
                <Box sx={{ mx: 2 }}> to </Box>
                <TextField {...endProps} />
            </React.Fragment>
            )}
        />
        </LocalizationProvider>
        <Button variant="contained" color="success" onClick={()=>onSubmitClick(value)}>
            Submit
        </Button>
    </Stack>
    
  );
}
