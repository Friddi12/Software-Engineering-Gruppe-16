import KursKort from "./KursKort.jsx"
import kurs from "../mockData/Courses.jsx"

import { useEffect, useState } from "react"




export default function Carousel({ kursList = [] }) {
    if (!kursList) {
        return (
            <div id="caro-wraper">
                <h3>Nyeste Kurs</h3>
                <p>Laster inn kurs...</p>
            </div>
        );
    }

    return (
        <div id="caro-wraper">
            <h3>Nyeste Kurs</h3>
            <section className="carousel">
                <ul className="carousel-track marquee">
                    {kursList.map((item) => (
                        <KursKort key={item.id} kurs={item} />
                    ))}
                    {kursList.map((item) => (
                        <KursKort key={`dup-1-${item.id}`} kurs={item} />
                    ))}
                </ul>
            </section>
        </div>
    )
}