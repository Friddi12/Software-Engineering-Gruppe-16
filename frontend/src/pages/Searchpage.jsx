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
        <div>
            <section className="searchgruppe">
                <h2>Finn kurs</h2>
                <form className="søkeForm" onSubmit={(e) => e.preventDefault()}>
                    <label htmlFor="kurssøk" className="visually-hidden"> Søk etter kurs </label>
                    <input 
                    id="kurssøk"
                    className="søkInput"
                    type="search"
                    placeholder="Søk etter kurs.." 
                    value = {query}
                    onChange={(e)=>setQuery(e.target.value)}
                    />
                    <button className="søkeknapp" type="submit">Søk</button>
                </form>
            </section>
        
            <section className="searchresultat">
            <div className="kursliste">
                {filtrering.map((k) => (
                <KursKort key={k.id} kurs={k} /> ))}
                {filtrering.length === 0 && <p>Ingen kurs funnet.</p>}
            </div>
            </section>
        </div>)

}