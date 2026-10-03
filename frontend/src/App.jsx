import './App.css'
import './style/husflid.css'
import Searchpage from './pages/Searchpage.jsx'

import { Route, Routes } from 'react-router-dom'

import Header from './components/Header.jsx'
import Hero from './components/Hero.jsx'

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
      </Routes> 
    </main>
  )

  
}

export default App
