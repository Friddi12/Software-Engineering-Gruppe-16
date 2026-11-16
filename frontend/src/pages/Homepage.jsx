
import Carousel from "../components/Carousel"
import Hero from "../components/Hero"
import { LocalData } from "../mockData/LocalData"
import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

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
    <div className="HPside">
      <Hero />
      <Carousel kursList={kursList}/>

      <section className="hjemSeksjon">
        <div className="hjemSeksjonHeader">
          <span>I ditt nærmiljø</span>
          <h2>Finn ditt lokallag</h2>
        </div>

        <ul className="hjemlagListe">
          {LocalData.map((local)=>(
            <li key={local.slug}>
              <Link to={`/Localhub/${local.slug}`} className="hjemLagKort">
              {local.image && <img src={local.image} alt="" />}
              <span>{local.name}</span> 
              </Link>
            </li>
          ))}
        </ul>

        <p className="HPLenke">
          <Link to="/Localhub">Se alle lokallag →</Link>
        </p>
      </section>
        
        {/* fra om oss*/}
        <section className="HPTall">
          <div className="hjemSeksjonHeader">
            <span>Om Norges Husflidslag</span>
            <h2>Tradisjonelt håndverk siden 1910</h2>
          </div>

          <ul className="HPTallListe"> 
            <li><strong>350+</strong><span>lokallag</span></li>
            <li><strong>20 000+</strong><span>medlemmer</span></li>
            <li><strong>19</strong><span>fylkeslag</span></li>
            <li><strong>34</strong><span>husflidsbutikker</span></li>
          </ul>

          <p className="HPLenke">
            <Link to="/Aboutus">Les mer om oss →</Link>
          </p>
        
      </section>
      <img src="/mainlogolys.jpg" alt="" className="mainlogoLys" />
      
      {/*<div>
        <h2>API Connection Test</h2>
        <p>{connectionStatus}</p>
      </div>*/}
    </div>

    )
}