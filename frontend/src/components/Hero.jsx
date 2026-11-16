import { Link } from "react-router-dom";


export default function Hero(){

    return(
        <section id="herosection">
            <h2>Østfold Husflidslag</h2>
            <Link to="/searchpage" className="finnKursknapp">Finn Kurs Nærme Deg!</Link>
        </section>

    )

}