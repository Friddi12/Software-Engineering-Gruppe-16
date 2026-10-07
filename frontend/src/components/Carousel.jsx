import KursKort from "./KursKort.jsx"
import kurs from "../mockData/Courses.jsx"


export default function Carousel(){

    return(
        <div id="caro-wraper">
            <h3>Nyeste Kurs</h3>
            <section className="carousel">
                <ul className="carousel-track marquee">
                    {kurs.map((item) => (
                        <KursKort key={item.id} kurs={item} />
                    ))}
                    {kurs.map((item) => (
                        <KursKort key={`dup-${item.id}`} kurs={item} />
                    ))}
                    {kurs.map((item) => (
                        <KursKort key={`dup-${item.id}`} kurs={item} />
                    ))}
                    {kurs.map((item) => (
                        <KursKort key={`dup-${item.id}`} kurs={item} />
                    ))}
                    {kurs.map((item) => (
                        <KursKort key={`dup-${item.id}`} kurs={item} />
                    ))}
                </ul>
            </section>
        </div>

    )
}