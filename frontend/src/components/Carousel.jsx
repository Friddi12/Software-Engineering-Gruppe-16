import KursKort from "./KursKort.jsx"
import kurs from "../mockData/Courses.jsx"

import { useEffect, useState, useRef } from "react"




export default function Carousel({ kursList = [] }) {
    const trackRef = useRef(null)

    if (!kursList) {
        return (
            <div id="caro-wraper">
                <h3>Nyeste Kurs</h3>
                <p>Laster inn kurs...</p>
            </div>
        );
    }

    // Skroll funksjon på karusellen

    const scrollCarousel = (direction) => {
        if (trackRef.current) {
            const card = trackRef.current.querySelector('.kurskort')
            if (card) {
                const cardWidth = card.offsetWidth
                const gap = 24 // 1.5rem = 24px
                const scrollAmount = (cardWidth + gap) * 2 // Scroll by 2 cards

                trackRef.current.scrollBy({
                    left: direction === "left" ? -scrollAmount : scrollAmount,
                    behavior: "smooth"
                })
            }
        }
    }

    return (
        <div id="caro-wraper">
            <h3>Nyeste Kurs</h3>
            <div className="carousel-container">
                <button className="carousel-btn prev-btn" onClick={() => scrollCarousel("left")}>
                    &#10094;
                </button>
                
                <section className="carousel">
                    <ul className="carousel-track" ref={trackRef}>
                        {kursList.map((item) => (
                            <KursKort key={item.id} kurs={item} />
                        ))}
                    </ul>
                </section>

                <button className="carousel-btn next-btn" onClick={() => scrollCarousel("right")}>
                    &#10095;
                </button>
            </div>
        </div>
    )
}