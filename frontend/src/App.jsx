import {useState} from 'react';
import styles from './App.module.css'
import FormularioFilme from './components/FormularioFilme/FormularioFilme'
import ListarFilmes from './components/ListarFilmes/ListarFilmes'

function App() {
    const [mostrarFormulario, setMostrarFormulario] = useState(false)

    return (
        <div className={styles.app}>

            <header className={styles.header}>
                <h1>CinemaStar</h1>

                <nav>
                    <a href="#">Início</a>
                    <a href="#">Filmes</a>
                </nav>
            </header>

            <main>
                <section className={styles.hero}>
                    <h2>O CINEMA COMEÇA AQUI</h2>

                    <button onClick={() => setMostrarFormulario(!mostrarFormulario)}>
                        {mostrarFormulario ? 'Fechar cadastro' : 'Cadastrar filme'}
                    </button>
                </section>

                {mostrarFormulario && <FormularioFilme />}

                <ListarFilmes/>
            </main>
        </div>
    )
}

export default App