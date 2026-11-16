import './App.css'
import './style/husflid.css'


import { Route, Routes } from 'react-router-dom'

import Header from './components/Header.jsx'
import Searchpage from './pages/Searchpage.jsx'
import Homepage from './pages/Homepage.jsx'
import Localhub from './pages/Localhub.jsx'
import Localpage from './pages/Localpage.jsx'
import Aboutus from './pages/Aboutus.jsx'
import Coursepage from './pages/Coursepage.jsx'
import Footer from './components/Footer.jsx'
import ContactUs from './pages/ContactUs.jsx'
import Adminpage from './pages/Adminpage.jsx'

function App() {

  function Home(){
    return(
      <Homepage />
    )
  }

  return (
    
    <main>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} /> 
        <Route path="/Searchpage" element={<Searchpage />} />
        <Route path="/Localhub" element={<Localhub />} />
        <Route path="/Localhub/:slug" element={<Localpage />} />
        <Route path='/Aboutus' element={<Aboutus />} />
        <Route path='/kurs/:id' element={<Coursepage />} />
        <Route path='/ContactUs' element={<ContactUs />}/>
        <Route path='/Adminpage' element={<Adminpage />} />
      </Routes> 
      <Footer />
    </main>
  )

  //burde ha homepage istedenfor home?
}

export default App
