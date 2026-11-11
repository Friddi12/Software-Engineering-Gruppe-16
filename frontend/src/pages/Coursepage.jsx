import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"

export default function Coursepage() {
    const { id } = useParams()
    const [kurs, setKurs] = useState(null)
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        fetch('http://localhost:8080/api/kurs')
            .then(response => {
                if (response.ok) {
                    return response.json()
                } else {
                    throw new Error("Kunne ikke hente kursliste")
                }
            })
            .then(data => {
                const foundKurs = data.find(k => k.id.toString() === id)
                setKurs(foundKurs)
                setLoading(false)
            })
            .catch(error => {
                console.error("Feil ved henting:", error)
                setLoading(false)
            })
    }, [id])

    if (loading) {
        return <p>Laster inn kursdetaljer...</p>
    }

    if (!kurs) {
        return <p>Fant ikke det etterspurte kurset.</p>
    }

    return (
        <div className="course-page" style={{ padding: '20px' }}>
            <h2>Kursdetaljer</h2>
            <h1>{kurs.navn}</h1>
            <p><strong>Beskrivelse:</strong> {kurs.beskrivelse}</p>
            <p><strong>Sted:</strong> {kurs.sted}</p>
            <p><strong>Kategori:</strong> {kurs.kategori}</p>
            <p><strong>Start:</strong> {kurs.startTid}</p>
            <p><strong>Slutt:</strong> {kurs.sluttTid}</p>
            <p><strong>Arrangert av:</strong> {kurs.lag}</p>
            <p><strong>Ledige plasser:</strong> {kurs.plasser}</p>
        </div>
    )
}