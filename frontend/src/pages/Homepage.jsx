
import Carousel from "../components/Carousel"
import Hero from "../components/Hero"
import { useEffect, useState } from 'react'

export default function Homepage(){
  const [connectionStatus, setConnectionStatus] = useState("Testing connection...");
  const [kursList, setKursList] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/kurs')
      .then(response => {
        if (response.ok) {
          setConnectionStatus("Kobling opprettet med backend");
          return response.json();
        } else {
          setConnectionStatus("Connected, but received an error status: " + response.status);
        }
      })
      .then(data => {
        console.log("Data from backend:", data);
        if (data) {
          setKursList(data); 
        }
      })
      .catch(error => {
        console.error("Connection failed:", error);
        setConnectionStatus("Ingen kobling til backend");
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

      <div className="kurs-section">
        <h2>Alle Kurs</h2>
        {kursList.length === 0 ? (
          <p>Ingen kurs funnet i databasen.</p>
        ) : (
          <div className="kurs-grid">
            {kursList.map((kurs) => (
              <div key={kurs.id} className="kurs-card" style={{ border: '1px solid #ccc', margin: '10px', padding: '15px', borderRadius: '8px' }}>
                <h3>{kurs.navn}</h3>
                <p><strong>Beskrivelse:</strong> {kurs.beskrivelse}</p>
                <p><strong>Sted:</strong> {kurs.sted}</p>
                <p><strong>Kategori:</strong> {kurs.kategori}</p>
                <p><strong>Start:</strong> {kurs.startTid}</p>
                <p><strong>Slutt:</strong> {kurs.sluttTid}</p>
              </div>
            ))}
          </div>
        )}
      </div>
    </>

    )
}