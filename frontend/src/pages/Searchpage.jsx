import { useState } from "react"
import KursKort from "../components/KursKort.jsx"
import kurs  from "../mockData/Courses.jsx"


export default function Searchpage() {
    const [query, setQuery] = useState("") //det bruker har skrevet 

    // skal filtrere kurs basert på søkespørsmål
    const filtrering = kurs.filter((k) => 
        k.title
    )

    return(
        <main>
            <section className="searchgruppe">
                <h2>Finn kurs</h2>
                {/*lar skjermleser få tilgang til søkefelt  */}
                <label htmlFor="kurssøk" className="visually-hidden"> Søk etter kurs eller lag</label>
                <input 
                id="kurssøk"
                classname="søkInput"
                type="search"
                placeholder="Søk etter kurs.." 
                value = {query}
                onChange={(e)=>setQuery(e.target.value)}
                />
                <button type="submit">Søk</button>
            </section>
        
            <section className="searchresultat">
            <div className="kursliste">
                {filtrering.map((k) => (
                <KursKort key={k.id} kurs={k} /> ))}
                {filtrering.length === 0 && <p>Ingen kurs funnet.</p>}
            </div>
            </section>
        </main>)

}