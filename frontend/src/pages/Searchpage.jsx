import { useState, useEffect } from "react"
import KursKort from "../components/KursKort.jsx"

export default function Searchpage() {
    const [query, setQuery] = useState("") //det bruker har skrevet 
    const [loading, setLoading] = useState(true)
    const [kursList, setKursList] = useState([]) //Data fra backend

    //API-kall som henter alle kurs
    useEffect(() => {
        fetch('http://localhost:8080/api/kurs')
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error("Kunne ikke hente kurs");
            })
            .then(data => {
                if (data) {
                    setKursList(data);
                }
                setLoading(false);
            })
            .catch(error => {
                console.error("Error fetching courses:", error);
                setLoading(false);
            });
    }, []);

    //Filtrere kursene: hvis søk er tomt, returnerer alle kurs.
    const filtrering = kursList.filter((k) => {
        const searchText = query.toLowerCase();
        const navnMatch = k.navn?.toLowerCase().includes(searchText);
        const beskrivelseMatch = k.beskrivelse?.toLowerCase().includes(searchText);
        return navnMatch || beskrivelseMatch;
    });

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