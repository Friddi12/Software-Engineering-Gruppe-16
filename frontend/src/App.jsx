import './App.css'
import './style/husflid.css'


import { Route, Routes } from 'react-router-dom'

import Header from './components/Header.jsx'
import Hero from './components/Hero.jsx'
import Searchpage from './pages/searchpage.jsx'
import Homepage from './pages/Homepage.jsx'
import Localhub from './pages/Localhub.jsx'

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
      </Routes> 
    </main>
  )

  
}

export default App
