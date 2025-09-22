package com.example.insper.repository;

import com.example.insper.entity.Filme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FilmeRepositoryTest {

    @Autowired
    private FilmeRepository repository;

    @Test
    void deveSalvarEBuscar() {
        Filme filme = new Filme();
        filme.setTitulo("Matrix");
        filme.setDescricao("Sci-fi clássico");
        filme.setDuracao(2);
        filme.setDiretor("Wachowski");

        repository.save(filme);

        List<Filme> filmes = repository.findAll();
        assertFalse(filmes.isEmpty());
        assertEquals("Matrix", filmes.get(0).getTitulo());
    }
}
