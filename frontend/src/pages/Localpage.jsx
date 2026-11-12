import { useParams, Link } from 'react-router-dom'
import { useState, useEffect } from 'react'
import { LocalData } from '../mockData/LocalData'
import Carousel from '../components/Carousel'

export default function Localpage(){
    const { slug } = useParams()
    const local = LocalData.find((item) => item.slug === slug)

    const [kursList, setKursList] = useState([])

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
        return <p>Fant ikke lokallaget.</p>
    }

    return(
        <>
            <section id='local-page'>
                <h2>{local.name}</h2>
                <img src={local.image} alt={local.name} />
                <img 
                    src={`/local/${local.slug}-hsf.png`} 
                    alt={`Logo for ${local.name}`} 
                    className="local-logo" 
                    onError={(e)=>{e.currentTarget.style.display = "none"}}
                />
                <p>{local.info}</p>
            </section>
             <Carousel kursList={kursList}/>
        </>
    )

}