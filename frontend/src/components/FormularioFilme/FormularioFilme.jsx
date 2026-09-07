import {useState} from "react";
import styles from './FormularioFilme.module.css'


function FormularioFilme() {
    const [titulo, setTitulo] = useState('')
    const [diretor, setDiretor] = useState('')
    const [anoLancamento, setAnoLancamento] = useState('')
    const [genero, setGenero] = useState('')
    const [nota, setNota] = useState('')

    function cadastrarFilme(event) {
        event.preventDefault()   /*não p osso esquecer que isso serve pra não envia e não recarrega a pág*/

        fetch('http://localhost:8080/filmes', {
            method: 'POST',
            headers: {
                'Content-Type':'application/json'
            },
            body: JSON.stringify({
                titulo: titulo,
                diretor: diretor,
                anoLancamento: anoLancamento,
                genero: genero,
                nota: nota
            })
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Erro ao cadastrar filme')
                }

                return response.json()
            })
    }

    return (
        <div className={styles.formulario}>
            <h2>Cadastrar filme</h2>

            <form onSubmit={cadastrarFilme}>
                <label type="text">Título</label>
                <input id="titulo" type="text" placeholder="Digite o Título" value={titulo}
                       onChange={(event) => setTitulo(event.target.value)} />
                <label type="text">Diretor</label>
                <input id="diretor" type="text" placeholder="Digite o Diretor" value={diretor}
                       onChange={(event) => setDiretor(event.target.value)} />
                <label type="number">Ano de Lançamento</label>
                <input id="anoLancamento" type="number" placeholder="Ano de lançamento" value={anoLancamento}
                        onChange={(event) => setAnoLancamento(Number(event.target.value))}/>
                <label type="text">Gênero</label>
                <input id="genero" type="text" placeholder="Digite o Gênero" value={genero}
                        onChange={(event) => setGenero(event.target.value)}/>
                <label type="number">Nota</label>
                <input id="nota" type="number" placeholder="Nota" value={nota}
                        onChange={(event) => setNota(Number(event.target.value))}/>

                <button type="submit">Cadastrar filme</button>
            </form>
        </div>
    )
}



export default FormularioFilme