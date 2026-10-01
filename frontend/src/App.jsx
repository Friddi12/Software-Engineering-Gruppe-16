import './App.css'
import './style/husflid.css'
import Searchpage from './pages/Searchpage.jsx'

import { Route, Routes } from 'react-router-dom'

import Header from './components/Header.jsx'
<<<<<<< HEAD
import { Route, Routes } from 'react-router-dom'
=======
import Searchpage from './pages/searchpage.jsx'
import Hero from './components/Hero.jsx'
>>>>>>> React

function App() {

  function Home(){
    return(
      <>
      <Hero />
      <h1>UNDER ARBEID</h1>
      <img src="mainlogolys.jpg" alt="" />
<<<<<<< HEAD
      <Routes>
        <Route path="/" element={<Searchpage />} />
      </Routes>
    </main>  
=======
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
>>>>>>> React
  )

  
}



export default App
