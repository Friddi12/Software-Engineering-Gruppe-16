import { Link } from "react-router-dom";


export default function Hero(){

    return(
        <section id="herosection">
            <h2>Velkommen til Østfoldhusflidslag!</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
            <Link to="/searchpage">Finn Kurs Nærme Deg!</Link>

        </section>

    )

}