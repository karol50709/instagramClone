import React, { useEffect } from 'react'
import './App.css'
import { getHelloObject } from './api/example.api'

function App (): any {
  useEffect(() => {
    void getHelloObject().then(value => { console.log(value) })
  }, [])

  return (
        <div className="Apsp">
        </div>
  )
}

export default App
