import { Link } from "react-router-dom";

export default function Nav(){

    return(
        <nav>
            <Link to="/Localhub">Finn Et Lag</Link>
            <Link to="/Searchpage">Se Alle Kurs</Link>
            <Link>Om Oss</Link>
            <Link>Kontakt Oss</Link>
            <Link>Test</Link>
        </nav>
    )

}