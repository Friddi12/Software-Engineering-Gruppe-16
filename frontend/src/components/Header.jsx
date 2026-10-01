import { Link } from "react-router-dom"

import Nav from "./Nav.jsx"

export default function Header (){

    return(
    <header>
        <h1>
            <Link to="/">
                <img src="mainlogo.png" alt="LOGO" />
            </Link>
        </h1>
        <Nav />
        <button className="searchbutton">
            <img src="searchicon.png" alt="SØK" />
        </button>
    </header>
    )
}
