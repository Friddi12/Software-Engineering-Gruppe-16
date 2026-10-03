


export default function Carousel(){

    return(

        <section className="carousel">
    <ul className="carousel-track marquee">
        {/* Repeating cards twice to create a seamless infinite loop */}
        <li className="carousel-card placeholder-1">Card 1</li>
        <li className="carousel-card placeholder-2">Card 2</li>
        <li className="carousel-card placeholder-3">Card 3</li>
        <li className="carousel-card placeholder-4">Card 4</li>
        <li className="carousel-card placeholder-1">Card 5</li>
        <li className="carousel-card placeholder-2">Card 6</li>
        <li className="carousel-card placeholder-3">Card 7</li>
        <li className="carousel-card placeholder-4">Card 8</li>
        <li className="carousel-card placeholder-1">Card 1</li>
        <li className="carousel-card placeholder-2">Card 2</li>
    </ul>
        </section>

    )
}