import ShowList from "./Components/ShowList";
import Stack from '@mui/material/Stack';
import React, { useEffect, useState } from "react";
import BasicDateRangePicker from "./Components/BasicDateRangePicker";
import MenuAppBar from "./Components/MenuAppBar";
import retrieve_images from "./Components/HelperFunctions";

export default function App() {
  const [images, setImages] = useState([]);

  return (
      <Stack spacing={2} justifyContent="center" alignItems="center">
        <MenuAppBar/>
        <BasicDateRangePicker onSubmitClick={(dateArray)=>retrieve_images(setImages,dateArray)} />
        <ShowList images={images}/>
      </Stack>
     
  );
}