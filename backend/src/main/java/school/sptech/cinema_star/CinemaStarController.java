package school.sptech.cinema_star;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.sql.PreparedStatement;
import java.util.List;

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



}
