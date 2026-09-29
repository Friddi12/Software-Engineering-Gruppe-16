import { Link } from "react-router-dom"

export default function Header (){

    return(
    <header>
        <h1>
            <Link to="/">
                <img src="mainlogolys.jpg" alt="LOGO" />
            </Link>
        </h1>

    </header>
    )
}
