import { useParams, Link } from 'react-router-dom'
import { useState, useEffect } from 'react'
import { LocalData } from '../mockData/LocalData'
import Carousel from '../components/Carousel'

export default function Localpage(){
    const { slug } = useParams()
    const local = LocalData.find((item) => item.slug === slug)

    const [kursList, setKursList] = useState([])

    // Husker hvilket lag som mangler logo, så rammen kan skjules helt
    const [manglerLogoFor, setManglerLogoFor] = useState(null)

    useEffect(() => {
        fetch('http://localhost:8080/api/kurs')
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
            })
            .then(data => {
                if (data) {
                    setKursList(data);
                }
            })
            .catch(error => {
                console.error("Feil ved henting av kurs:", error)
            });
    }, [local])

    if (!local) {
        return(
            <div className='lokalSide'>
                <Link to="/Localhub" className='lokalTilbake'> ← Alle lokallag </Link>
                <p>Fant ikke lokallaget</p>
            </div>
        )
    }

    const visLogo = manglerLogoFor !== local.slug

    return(
        <div className='lokalSide'>
            <Link to="/Localhub" className='lokalTilbake'> ← Alle lokallag </Link>
            
            <section className='lokalHero'>
                <img src={local.image} alt={local.name} className='lokalHeroBilde' />
                
            
                {visLogo && (
                    <div className='local-logoRamme'>
                        <img 
                            src={`/local/${local.slug}-hsf.png`} 
                            alt={`Logo for ${local.name}`} 
                            className="local-logo" 
                            onError={(e)=>{e.currentTarget.style.display = "none"}}
                        />
                
                    </div>)}
            </section> 

            {/* overskrift*/}
            <section className={visLogo ? "lokalHeader lokalHeaderMedLogo" : "lokalHeader"}>
                <span>LOKALLAG I ØSTFOLD</span>
                <h2>{local.name}</h2>
            </section>
                
            {/*info om lokallag */}
            <article className='lokalInfoKort'>
                <h3>Om lokallaget {local.name}</h3>
                <p>{local.info}</p>
            </article>
                
            {/* kurs*/}
             <Carousel kursList={kursList}/>
        </div>
    )

}