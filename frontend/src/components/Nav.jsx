import { Link } from "react-router-dom";

export default function Nav(){

    return(
        <nav>
            <Link to="/Localhub">Finn Et Lag</Link>
            <Link to="/Searchpage">Se Alle Kurs</Link>
            <Link to="/Aboutus">Om Oss</Link>
            <Link to="/ContactUs">Kontakt </Link>
            <Link to="/Adminpage">Test</Link>
        </nav>
    )

}