import { Link } from "react-router-dom";

export default function Nav(){

    return(
        <nav>
            <Link to="/Localhub" className="navLenker">Finn Et Lag</Link>
            <Link to="/Searchpage" className="navLenker">Se Alle Kurs</Link>
            <Link to="/Aboutus" className="navLenker">Om Oss</Link>
            <Link to="/ContactUs" className="navLenker">Kontakt Oss</Link>
            <Link>Test</Link>
        </nav>
    )

}