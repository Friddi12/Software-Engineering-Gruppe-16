
import { LocalData } from "../mockData/LocalData"

import { Link } from 'react-router-dom'

export default function(){

    return(
    <section>
        <article>
            <h2>THIS IS A PAGE OF LOCAL TEAMS</h2>  
                <ul>
                    {LocalData.map((local) => (
                        <li key={local.slug}>
                            <h3>{local.name}</h3>
                            <Link to={`/Localhub/${local.slug}`}>Besøk</Link>
                        </li>
                    ))}
                </ul>
        </article>
        
    </section>
    )
}