
import { LocalData } from "../mockData/LocalData"

import { Link } from 'react-router-dom'

export default function(){

    return(
    <section>
            <h2>Lokale Husflidslag i Østfold</h2>  
                <ul id="local-container">
                    {LocalData.map((local) => (
                        <li key={local.slug} className="local-card">
                            <h3>{local.name}</h3>
                            <Link to={`/Localhub/${local.slug}`}>Besøk</Link>
                        </li>
                    ))}
                </ul>
        
    </section>
    )
}