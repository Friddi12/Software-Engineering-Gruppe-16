import { Link } from "react-router-dom"

import Nav from "./Nav.jsx"


export default function Header (){

    return(
    <header>
        <h1>
            <Link to="/">
                <img src="/mainlogo.png" alt="LOGO" />
            </Link>
        </h1>
        <Nav />
        <Link to="/Searchpage" className="searchbutton">
            <img src="/searchicon.png" alt="Søk" />
        </Link>
    </header>
    )
}
