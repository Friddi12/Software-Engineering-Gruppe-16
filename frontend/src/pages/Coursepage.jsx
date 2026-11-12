import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import SignUp from "../components/SignUp"

export default function Coursepage() {
    const { id } = useParams()
    const [kurs, setKurs] = useState(null)
    const [loading, setLoading] = useState(true)
    const [showSignUp, setShowSignUp] = useState(false)

function correctDate(dateStr) {
        if (!dateStr) return "Ingen dato funnet"

        // Dato kommer inn fra backend som en array
        if (Array.isArray(dateStr)) {
            const [year, month, day, hour = 0, minute = 0] = dateStr
            
            const formattedDay = String(day).padStart(2, '0')
            const formattedMonth = String(month).padStart(2, '0')
            const formattedHour = String(hour).padStart(2, '0')
            const formattedMinute = String(minute).padStart(2, '0')

            return `${formattedDay}.${formattedMonth}.${year} kl. ${formattedHour}:${formattedMinute}`
        }

    }

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
            <p><strong>Start:</strong> {correctDate(kurs.startTid)}</p>
            <p><strong>Slutt:</strong> {correctDate(kurs.sluttTid)}</p>
            {/* <p><strong>Arrangert av:</strong> {kurs.lag}</p>
            <p><strong>Ledige plasser:</strong> {kurs.plasser}</p> */}
            <button onClick={() => setShowSignUp(true)}>BLI MED</button>
            {showSignUp && (
                <SignUp 
                    kursNavn={kurs.navn} 
                    onClose={() => setShowSignUp(false)} 
                />
            )}
        </div>
    )
}