import { Link } from "react-router-dom"

export default function KursKort({kurs}){
    return(
        <Link to={`/kurs/${kurs.id}`} className="kurskortLink">
        <article className="kurskort">
            <div className="kurskortPanel">
                <h3 className="kurskortTitle">{kurs.navn}</h3>
                <p>Startdato: {kurs.startTid}</p>
                <p>Sluttdato: {kurs.sluttTid}</p>
                    {/* inkludere tid her etter hvert*/}
                
                
                <p className="kurskortLag">Arrangert av: {kurs.lag}</p>
                
                <div className="kurskortInfo">
                    <p className="kurskortLabel">Informasjon:</p>
                    <p>{kurs.beskrivelse}</p>  
                </div>
                
                <div className="kurskortFooter">
                    <p>Kategori: {kurs.kategori}</p>
                    <p>Ledige plasser: {kurs.plasser}</p>
                </div>
            </div>
        </article>
        </Link>
    )
}