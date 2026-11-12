import { Link } from "react-router-dom"
import Nav from "./Nav"

//objekt som lagrer lenkene til plattformene til norges husflidslag
const SosialeMedier = [
    {
        navn: 'Instagram', url: 'https://www.instagram.com/norgeshusflidslag/'
    },
    {
        navn: 'Facebook', url: 'https://www.facebook.com/NorgesHusflidslag/'
    },
    {
        navn: 'Youtube', url: 'https://www.youtube.com/user/NorgesHusflidslag'
    }
]



export default function Footer(){
    
        return(
            <footer className="footer">
                <div className="footerKolonner">
                    {/*kolonne 1, kontaktinformasjon */}
                    <section>
                        <h2>Kontakt oss</h2>
                        <p>Sentralbord: <br /> 
                        22 00 87 00 </p>
                        <p>
                            Man-tirs: 10-13 <br /> 
                            Ons: stengt <br /> 
                            To-fre: 10-13 <br /> 
                            Stengt: 11.30-12.00 <br /> 
                        </p>
                        <a href="mailto:post@husflid.no">post@husflid.no</a>
                    </section>

                    {/*kolonne 2, sted */}
                    <section>
                        <h2>Besøk oss</h2>

                        <p>
                        Øvre Slottsgate 2b, <br /> 
                        0157 Oslo
                        </p>
                    </section>

                    {/*kolonne 3, snarveier */ }
                    <section>
                        <h2>Snarveier</h2>

                        <ul className="footerSnarveiListe">
                            <li> <Link to="/">Hjem</Link> </li>
                            <li><Link to="/Searchpage">Se Alle Kurs</Link></li>
                            <li><Link to="/Aboutus">Om Oss</Link></li>
                            <li><Link to="/ContactUs">Kontakt Oss</Link></li>
                        </ul>
                    </section>

                    {/*kolonne 4, sosiale medier */}
                    <section>
                        <h2>Følg oss</h2>

                        <ul className="soMeListe">
                            {SosialeMedier.map((m) => (
                                <li key = {m.navn}> 
                                  <a href={m.url} target="_blank" rel="noopener noreferrer">{m.navn}</a>
                                </li>))} 
                        </ul>
                              {/* ovenfor: target:"_blank" åpner lenkene i ny side, rel:"" sørger for sikkerhet og privatliv protection */}
                    </section>
                </div>

                <div className="footerBunn">
                    <a href="#">
                        <img src="NH-logo-hvit.svg" alt="bunnLOGO" />
                    </a>
                </div>
            </footer>
        )

}