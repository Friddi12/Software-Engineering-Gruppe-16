
import Carousel from "../components/Carousel"
import Hero from "../components/Hero"
import { useEffect, useState } from 'react'

export default function Homepage(){
  const [connectionStatus, setConnectionStatus] = useState("Testing connection...");

  useEffect(() => {
    fetch('http://localhost:8080/api/kurs/search')
      .then(response => {
        if (response.ok) {
          setConnectionStatus("Connected successfully to Javalin backend!");
          return response.json();
        } else {
          setConnectionStatus("Connected, but received an error status: " + response.status);
        }
      })
      .then(data => console.log("Data from backend:", data))
      .catch(error => {
        console.error("Connection failed:", error);
        setConnectionStatus("Failed to connect to backend. Is Java running?");
      });
  }, []);

    return(
    <>
      <Hero />
      <Carousel />
      <p>INFORMASJON/LINKS TIL LOKALE LAG KOMMER!</p>
      <h1>UNDER ARBEID</h1>
      <img src="mainlogolys.jpg" alt="" />
      <div>
        <h2>API Connection Test</h2>
        <p>{connectionStatus}</p>
      </div>
    </>

    )
}