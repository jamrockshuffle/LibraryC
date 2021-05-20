package edu.bid.course.controller.rest;

import edu.bid.course.repository.AuthorRepository;
import edu.bid.course.service.author.AuthorServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AuthorServiceImpl authorService;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void getAuthors() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/author/get/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(9)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Джон Толкін")));
    }

    @Test
    void getById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}