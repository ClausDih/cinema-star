import styles from './App.module.css'
import FormularioFilme from './components/FormularioFilme/FormularioFilme'

function App() {
    return (
        <div className={styles.app}>
            <div className={styles.titulo}>
                <h1>CinemaStar</h1>
                <p>Meu catálogo de filmes</p>

                <FormularioFilme />
            </div>
        </div>
    )
}

export default App