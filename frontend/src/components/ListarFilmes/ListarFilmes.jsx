import {useEffect, useState} from "react";  /*faça um GET para buscar os filmes*/
import CardFilme from "../CardFilme/CardFilme"
import styles from './ListarFilmes.module.css'


function ListarFilmes() {

    const [filmes, setFilmes] = useState([])


    useEffect(() => {
        fetch('http://localhost:8080/filmes')
            .then((response) => response.json())
            .then((dados) => {
                console.log(dados)
                setFilmes(dados)
            })
    }, [])

    return (
        <div>
           <h2>Filmes cadastrados</h2>

            <div className={styles.lista}>
                {filmes.map((filme) => (
                    <CardFilme key={filme.id} filme={filme} />
                ))}
            </div>
        </div>
    )
}

export default ListarFilmes