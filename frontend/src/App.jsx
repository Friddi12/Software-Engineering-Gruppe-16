import './App.css'
import './style/husflid.css'
import Searchpage from './pages/Searchpage.jsx'

import { Route, Routes } from 'react-router-dom' //Importerer react-router for sømløs navigering mellom sider
//Importerer komponenter i bruk på denne siden
import Header from './components/Header.jsx' 
import Searchpage from './pages/searchpage.jsx'
import Homepage from './pages/Homepage.jsx'

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
