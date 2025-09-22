package com.example.insper.service;

import com.example.insper.entity.Filme;
import com.example.insper.repository.FilmeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmeServiceTest {

    @Mock
    private FilmeRepository repository;

    @InjectMocks
    private FilmeService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarFilme() {
        Filme filme = new Filme();
        filme.setTitulo("Inception");
        when(repository.save(filme)).thenReturn(filme);

        Filme salvo = service.salvar(filme);

        assertNotNull(salvo);
        assertEquals("Inception", salvo.getTitulo());
        verify(repository, times(1)).save(filme);
    }

    @Test
    void deveListarTodos() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Filme(), new Filme()));

        List<Filme> filmes = service.listarTodos();

        assertEquals(2, filmes.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveExcluir() {
        service.excluir(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}

