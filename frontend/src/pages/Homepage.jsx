
import Carousel from "../components/Carousel"
import Hero from "../components/Hero"
import { useEffect, useState } from 'react'

export default function Homepage(){
  //State som holder styr over kobling til databasen
  const [connectionStatus, setConnectionStatus] = useState("Testing connection...")
  const [kursList, setKursList] = useState([]) //Array til å holde data fra database

  //API-kall sjekker om data blir hentet fra databasen, og henter alle kursene og lagrer de i en array
  useEffect(() => {
    fetch('http://localhost:8080/api/kurs')
      .then(response => {
        if (response.ok) {
          setConnectionStatus("Kobling opprettet med backend")
          return response.json();
        } else {
          setConnectionStatus("Connected, but received an error status: " + response.status)
        }
      })
      .then(data => {
        console.log("Data from backend:", data)
        if (data) {
          setKursList(data);
        }
      })
      .catch(error => {
        console.error("Connection failed:", error)
        setConnectionStatus("Ingen kobling til backend")
      })
  }, [])

    return(
    <>
      <Hero />
      <Carousel kursList={kursList}/>
      <p>INFORMASJON/LINKS TIL LOKALE LAG KOMMER!</p>
      <h1>UNDER ARBEID</h1>
      <img src="/mainlogolys.jpg" alt="" />
      <div>
        <h2>API Connection Test</h2>
        <p>{connectionStatus}</p>
      </div>
    </>

    )
}