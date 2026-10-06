import { Link } from "react-router-dom"

export default function KursKort({kurs}){
    return(
        <article className="kurskort">
            <div className="kurskortPanel">
                <h3 className="kurskortTitle">{kurs.title}</h3>
                <p>Dato: {kurs.date}</p>
                    {/* inkludere tid her etter hvert*/}
                
                
                <p className="kurskortLag">Arrangert av: {kurs.lag}</p>
                
                <div className="kurskortInfo">
                    <p className="kurskortLabel">Informasjon:</p>
                    <p>{kurs.information}</p>  
                </div>
                
                <div className="kurskortFooter">
                    <p>Pris: {kurs.price} kr</p>
                    <p>Ledige plasser: {kurs.plasser}</p>
                </div>
            </div>
        </article>
    )
}