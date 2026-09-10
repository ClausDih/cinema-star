package school.sptech.cinema_star;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;


import java.sql.PreparedStatement;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/filmes")
public class CinemaStarController {

    private final JdbcTemplate jdbcTemplate;

    public CinemaStarController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public ResponseEntity<List<CinemaStar>> listarFilmes() {

        String sql = "SELECT * FROM cinema";
        List<CinemaStar> filmes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CinemaStar.class));

        return ResponseEntity.status(200).body(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CinemaStar> buscarFilmePorId(@PathVariable Integer id) {

        String sql = "SELECT * FROM cinema WHERE id = ?";
        List<CinemaStar> filmes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CinemaStar.class), id);

        if(filmes.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        CinemaStar filme = filmes.get(0);

        return ResponseEntity.status(200).body(filme);
    }


    @PostMapping
    public ResponseEntity<CinemaStar> cadastrarFilme(@RequestBody CinemaStar novoFilme) {

        if(novoFilme.getTitulo() == null || novoFilme.getTitulo().isBlank() ||
                novoFilme.getDiretor() == null || novoFilme.getDiretor().isBlank() ||
                novoFilme.getGenero() == null || novoFilme.getGenero().isBlank() ||
                novoFilme.getAnoLancamento() == null || novoFilme.getAnoLancamento() <1888 ||
                novoFilme.getNota() == null || novoFilme.getNota() <0 || novoFilme.getNota() > 10) {
            return ResponseEntity.status(400).build();
        }
        String sqlAdicionar = "INSERT INTO cinema (titulo, diretor, ano_lancamento, genero, nota) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sqlAdicionar, new String[]{"id"});
            preparedStatement.setString(1, novoFilme.getTitulo());
            preparedStatement.setString(2, novoFilme.getDiretor());
            preparedStatement.setInt(3, novoFilme.getAnoLancamento());
            preparedStatement.setString(4, novoFilme.getGenero());
            preparedStatement.setInt(5, novoFilme.getNota());
            return preparedStatement;
        }, keyHolder);

        novoFilme.setId(keyHolder.getKey().intValue());

        return ResponseEntity.status(201).body(novoFilme);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CinemaStar> atualizarFilme(@PathVariable Integer id, @RequestBody CinemaStar filmeAtualizado) {

        String sql = "SELECT * FROM cinema WHERE id = ?";
        List<CinemaStar> filmes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CinemaStar.class), id);

        if (filmes.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        if (filmeAtualizado.getTitulo() == null || filmeAtualizado.getTitulo().isBlank() ||
                filmeAtualizado.getDiretor() == null || filmeAtualizado.getDiretor().isBlank() ||
                filmeAtualizado.getGenero() == null || filmeAtualizado.getGenero().isBlank() ||
                filmeAtualizado.getAnoLancamento() == null || filmeAtualizado.getAnoLancamento() < 1888 ||
                filmeAtualizado.getNota() == null || filmeAtualizado.getNota() < 0 || filmeAtualizado.getNota() > 10){

            return ResponseEntity.status(400).build();
        }

        String sqlAtualizar = "UPDATE cinema SET titulo = ?, diretor = ?, ano_lancamento = ?, genero = ?, nota = ? WHERE id = ?";
        jdbcTemplate.update(sqlAtualizar,
                filmeAtualizado.getTitulo(),
                filmeAtualizado.getDiretor(),
                filmeAtualizado.getAnoLancamento(),
                filmeAtualizado.getGenero(),
                filmeAtualizado.getNota(),
                id
        );

        String sqlBuscar = "SELECT * FROM cinema WHERE id = ?";
        CinemaStar filme = jdbcTemplate.queryForObject(sqlBuscar, new BeanPropertyRowMapper<>(CinemaStar.class), id);

        return ResponseEntity.status(200).body(filme);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFilme(@PathVariable Integer  id) {

        String sql = "SELECT * FROM cinema WHERE id = ?";
        List<CinemaStar> filmes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CinemaStar.class), id);

        if (filmes.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        String sqlRemover = "DELETE FROM cinema WHERE id = ?";
        jdbcTemplate.update(sqlRemover, id);

        return ResponseEntity.status(204).build();

    }

}
