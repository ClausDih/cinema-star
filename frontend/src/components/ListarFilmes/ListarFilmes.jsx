import {useEffect, useState} from "react";  /*faça um GET para buscar os filmes*/

function ListaFilmes() {

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

            {filmes.map((filme) => (
                <div key={filme.id}>
                    <h3>{filme.titulo}</h3>
                    <p>Diretor: {filme.diretor}</p>
                    <p>Ano: {filme.anoLancamento}</p>
                    <p>Gênero: {filme.genero}</p>
                    <p>Nota: {filme.nota}</p>
                </div>
            ))}
        </div>
    )
}

export default ListaFilmes