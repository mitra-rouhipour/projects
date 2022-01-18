function createImage(img, expl,date,title){
    return {img, expl,date, title};
  }
function createUrl(start_date, end_date){
    return `https://api.nasa.gov/planetary/apod?api_key=sgtUgElMsVYiSvwYLJqkDrLB9BYSiVlHPPGLEaya&start_date=${start_date}&end_date=${end_date}`;
  }
export default  function retrieveImages(set_images,date_array){
    let currentDate = new Date().toISOString().slice(0, 10);
    let startDateInput = date_array[0].toISOString().substring(0,10); 
    let endDateInput = date_array[1].toISOString().substring(0,10);
    let numEnd = endDateInput.substring(0,4) + endDateInput.substring(5,7) + endDateInput.substring(8);
    let numCur = currentDate.substring(0,4) + currentDate.substring(5,7) + currentDate.substring(8);
    if(parseInt(numEnd, 10) <= parseInt(numCur) -1){
      fetch(createUrl(startDateInput, endDateInput))
                .then(res => {
                  if(res["status"] === 200){
                    return res.json()
                  }
                  else{
                    alert("There was some problems with the Jason file");
                  }
                })
                .then(
                    (result) => {
                        let temp= [];
                        result.map((field)=> {
                            temp.push(createImage(field["url"],field["explanation"],field["date"],field["title"]));
                        });
                        set_images(temp);
                    }
                )
                .catch(error=>{
                  alert("Error: while fetching the data.");
                }); 
      }
      else{
        alert("Error: Can not enter date for tomorrow!!! Please try again.");
      }
  }