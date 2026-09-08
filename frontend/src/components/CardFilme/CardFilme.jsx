import styles from './CardFilme.module.css'


function CardFilme({ filme, aoExcluir }) {

    function excluirFilme() {
        fetch(`http://localhost:8080/filmes/${filme.id}`, {
            method: 'DELETE'
        })
            .then(() => {
                aoExcluir()
            })
    }

    return (
        <div className={styles.card}>
            <h3>{filme.titulo}</h3>
            <p>Diretor: {filme.diretor}</p>
            <p>Ano: {filme.anoLancamento}</p>
            <p>Gênero: {filme.genero}</p>
            <p>Nota: {filme.nota}</p>

            <button onClick={excluirFilme}>Excluir</button>
        </div>
    )
}

export default CardFilme