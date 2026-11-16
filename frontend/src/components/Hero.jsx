import { Link } from "react-router-dom";


export default function Hero(){

    return(
        <section className="heroSeksjon">
            <div className="heroInnhold">
                <span>Østfold Husflidslag</span>
                <h2>Velkommen til Østfold Husflidslag!</h2>
                <p>
                    Finn kurs, møt andre som er glad i håndverk og opplev skaperglede i ditt eget nærmiljø
                </p>
                <div className="heroKnapper">
                    <Link to="/searchpage" className="heroKnapp heroKnappKurs">Finn Kurs Nærme Deg!</Link>
                    <Link to="/Localhub" className="heroKnapp heroKnappLokal">Finn et Lokallag!</Link>
                </div>
            </div>
            
        </section>

    )

}