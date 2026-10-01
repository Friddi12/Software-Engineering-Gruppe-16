import './App.css'
import './style/husflid.css'

import { Route, Routes } from 'react-router-dom'

import Header from './components/Header.jsx'
import Searchpage from './pages/searchpage.jsx'
import Hero from './components/Hero.jsx'

function App() {

  function Home(){
    return(
      <>
      <Hero />
      <h1>UNDER ARBEID</h1>
      <img src="mainlogolys.jpg" alt="" />
    </>
    )
  }

  return (
    
    <main>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Searchpage" element={<Searchpage />} />
      </Routes>
    </main>
  )

  
}



export default App
