
export default function ContactUs(){
    
    const hovedkontakt = {
        navn: "Kari Nordmann",
        rolle: "Leder i Østfold Husflidslag",
        email: "kari@example.com",
        telefon: "900 00 000"
    }

    const kontakter=[
    {
        navn: "Ola Hansen",
        rolle: "Nestleder",
        email: "ola@example.no",
        telefon: "901 00 000"
    },
    {
        navn: "Anne Johansen",
        rolle: "Kursansvarlig",
        email: "anne@example.no",
        telefon: "902 00 000",
    },
    {
        navn: "Liv Andersen",
        rolle: "Medlemsansvarlig",
        email: "liv@example.no",
        telefon: "903 00 000"
    },
    {
        navn: "Per Olsen",
        rolle: "Kursansvarlig",
        email: "per@example.no",
        telefon: "904 00 000"
    }]
    /*her ble ai brukt for å fylle dummy data-en */ 

    return(
        <div className="kontaktSide">
            <section className="kontaktheader">
                <span>VI HØRER GJERNE FRA DEG</span>
                <h2>Kontakt oss</h2>
                <p className="underTitteltekst">Har du spørsmål om husflid, kurs, medlemsskap eller våre aktiviteter? 
                Vi hjelper deg gjerne!</p>
            </section>
            
            {/* hovedkontakt*/}
            <section className="hovedkontaktSeksjon">
                <h2>Hovedkontakt for Østfold Husflidslag</h2>
                <article className="hovedkontakt-kort">
                    <p className="hovedkontakt-rolle">
                        {hovedkontakt.rolle}</p>
                    <h3>{hovedkontakt.navn}</h3>
                    <p>Ta kontakt på mail eller telefon:</p>
                    <div className="kontaktinfo">
                        <a href={`mailto:${hovedkontakt.email}`}>{hovedkontakt.email}</a>
                        <a href={`tel:${hovedkontakt.telefon}`}>{hovedkontakt.telefon}</a>
                    </div>
                </article>
            </section>

            {/* andre kontakter*/}
            <section className="andrekontakter">
                <h2>Andre kontaktpersoner</h2>

                <p className="andrekontakter-beskrivelse">
                    Her finner du kontaktinformasjon til andre i styret i Østfold Husflidslag.
                </p>
                <section className="kontaktGrid">
                    {kontakter.map((kontakt)=>(
                        <article className="kontaktkort" key={kontakt.email}>
                            <h3>{kontakt.navn}</h3>
                            <p className="kontaktrolle">
                                {kontakt.rolle}
                            </p>
                            <div className="kontaktinfo">
                                <a href={`mailto:${kontakt.email}`}>
                                    {kontakt.email}
                                </a>
                                <a href={`tel:${kontakt.telefon}`}>{kontakt.telefon}</a>
                            </div>
                        </article>
                    ))}
                </section>
            </section>

        </div>
    )
}