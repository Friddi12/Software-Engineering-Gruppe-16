import { useState } from 'react';

export default function SignUp({ kursNavn, onClose }) {
    const [navn, setNavn] = useState("");
    const [epost, setEpost] = useState("");
    const [submitted, setSubmitted] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Påmelding mottatt:", { navn, epost, kursNavn });
        setSubmitted(true);
    }

    return (
        <div className="popup-overlay">
            <div className="popup-content">
                <h3>Meld deg på: {kursNavn}</h3>
                
                {submitted ? (
                    <div>
                        <p>Takk for påmeldingen, {navn}!</p>
                        <button onClick={onClose}>Lukk</button>
                    </div>
                ) : (
                    <form onSubmit={handleSubmit}>
                        <label>
                            Fullt navn:
                            <input 
                                type="text" 
                                value={navn} 
                                onChange={(e) => setNavn(e.target.value)} 
                                required 
                                className="popup-input"
                            />
                        </label>
                        <label>
                            E-postadresse:
                            <input 
                                type="email" 
                                value={epost} 
                                onChange={(e) => setEpost(e.target.value)} 
                                required 
                                className="popup-input"
                            />
                        </label>
                        <div className="popup-buttons">
                            <button type="button" onClick={onClose}>Avbryt</button>
                            <button type="submit" className="btn-submit">Send påmelding</button>
                        </div>
                    </form>
                )}
            </div>
        </div>
    )
}