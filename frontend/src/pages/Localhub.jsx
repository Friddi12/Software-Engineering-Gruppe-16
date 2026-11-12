
import { LocalData } from "../mockData/LocalData"

import { Link } from 'react-router-dom'

export default function(){
    

    return(
    <section className="lokalhub-side">
            <div className="lokalhub-header">
                <h2>Lokale Husflidslag i Østfold</h2>  
            </div>

            <ul id="local-container">
                {LocalData.map((local) => (
                    <li key={local.slug} className="local-card">
                        {local.image && (
                            <img src={local.image} alt={local.name} className="local-card-bilde" />
                        )}
                        <div className="local-card-tekst">
                            <h3>{local.name}</h3>
                            <Link to={`/Localhub/${local.slug}`} className="lokallagLenke">Besøk</Link>
                        </div>
                    </li>
                ))}
            </ul>
        
    </section>
    )
}