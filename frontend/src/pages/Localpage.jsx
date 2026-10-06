import { useParams, Link } from 'react-router-dom';
import { LocalData } from '../mockData/LocalData';

export default function Localpage(){
    const { slug } = useParams()
    const local = LocalData.find((item) => item.slug === slug)

    return(
        <section id='local-page'>
            <h2>{local.name}</h2>
            <img src={local.image} alt={local.name} />
            <p>{local.info}</p>
        </section>
    )

}