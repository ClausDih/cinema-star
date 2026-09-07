import styles from './CardFilme.module.css'


function CardFilme({ filme }) {
    return (
        <div className={styles.card}>
            <h3>{filme.titulo}</h3>
            <p>Diretor: {filme.diretor}</p>
            <p>Ano: {filme.anoLancamento}</p>
            <p>Gênero: {filme.genero}</p>
            <p>Nota: {filme.nota}</p>
        </div>
    )
}

export default CardFilme