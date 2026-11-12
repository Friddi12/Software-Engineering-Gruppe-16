import { LocalData } from "../mockData/LocalData";


export default function LocalBox(){


    return(

        <section id="local-box">
            <h3>Våre lokale lag</h3>
            <ul>
                {LocalData.map((local, index) => (
                    <li key={index}>{local.name}</li>
                ))}
            </ul>

        </section>
    )
}