import { Link } from "react-router-dom"

export default function KursKort({kurs}){
    return(
        <article className="kurs-kort">
            <h3>{kurs.title}</h3>
            <p>Arrangert av: {kurs.lag}</p>
            <p>Dato: {kurs.date}</p>
            <p>Informasjon: {kurs.information}</p>
            <p>Pris: {kurs.price} kr</p>
            <p>Ledige plasser: {kurs.plasser}</p>
        </article>
    )
}