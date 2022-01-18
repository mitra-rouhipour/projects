import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import React, { useEffect, useState } from "react";
import PictureComp from './PictureComp';

export default function ShowList({images}) {
    
  return (
    <ImageList sx={{ width: 1200, height: 1450 }} cols={3}>
      {images && images.map((item) => (
        <ImageListItem key={item.img}>
          <PictureComp imgAddress={item.img} expl={item.expl} title={item.title}  date={item.date}/>
        </ImageListItem>
      ))}
    </ImageList>
  );
}
