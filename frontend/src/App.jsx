import styles from './App.module.css'
import FormularioFilme from './components/FormularioFilme/FormularioFilme'
import ListarFilmes from './components/ListarFilmes/ListarFilmes'

function App() {
    return (
        <div className={styles.app}>
            <div className={styles.titulo}>
                <h1>CinemaStar</h1>
                <p>Meu catálogo de filmes</p>

                <FormularioFilme />

                <ListarFilmes/>
            </div>
        </div>
    )
}

export default App