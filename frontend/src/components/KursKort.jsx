import { Link } from "react-router-dom"

export default function KursKort({kurs}){

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
    
    return(
        <Link to={`/kurs/${kurs.id}`} className="kurskort">
            <article className="kurskort-inner">
                <div className="kurskortPanel">
                    <h3 className="kurskortTitle">{kurs.navn}</h3>
                    <p>Startdato: {correctDate(kurs.startTid)}</p>
                    <p>Sluttdato: {correctDate(kurs.sluttTid)}</p>
                    
                    <div className="kurskortFooter">
                        <p>Kategori: {kurs.kategori}</p>
                    </div>
                </div>
            </article>
        </Link>
    )
}