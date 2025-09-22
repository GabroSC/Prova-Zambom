package com.example.insper.controller;

import com.example.insper.entity.Filme;
import com.example.insper.service.FilmeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmeController.class)
class FilmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeService service;

    @Test
    void deveCadastrarFilme() throws Exception {
        Filme filme = new Filme();
        filme.setTitulo("Tenet");
        Mockito.when(service.salvar(Mockito.any(Filme.class))).thenReturn(filme);

        mockMvc.perform(post("/filmes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Tenet\",\"descricao\":\"Teste\",\"duracao\":2,\"diretor\":\"Nolan\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Tenet"));
    }

    @Test
    void deveListarFilmes() throws Exception {
        Filme filme = new Filme();
        filme.setTitulo("Interstellar");
        Mockito.when(service.listarTodos()).thenReturn(Collections.singletonList(filme));

        mockMvc.perform(get("/filmes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Interstellar"));
    }

    @Test
    void deveExcluirFilme() throws Exception {
        doNothing().when(service).excluir(1L);

        mockMvc.perform(delete("/filmes/1"))
                .andExpect(status().isNoContent());
    }
}
