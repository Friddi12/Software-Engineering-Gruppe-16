import { useState } from "react"
import KursKort from "../components/KursKort.jsx"
import kurs  from "../mockData/Courses.jsx"


export default function Searchpage() {
    const [query, setQuery] = useState("")

    const filtrering = kurs.filter((k) => 
        k.title
    )

    return(
        <main>
            <section className="searchgruppe">
            <input type="search"
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