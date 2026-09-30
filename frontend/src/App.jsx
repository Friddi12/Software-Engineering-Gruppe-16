import './App.css'
import './style/husflid.css'
import Searchpage from './pages/Searchpage.jsx'

import Header from './components/Header.jsx'
import { Route, Routes } from 'react-router-dom'

function App() {

  return (
    <main>
      <Header />
      <h1>UNDER ARBEID</h1>
      <img src="mainlogolys.jpg" alt="" />
      <Routes>
        <Route path="/" element={<Searchpage />} />
      </Routes>
    </main>  
  )
}

export default App
