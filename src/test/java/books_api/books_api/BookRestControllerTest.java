package books_api.books_api;

import books_api.books_api.controller.BookRestController;
import books_api.books_api.dto.BookResponseDTO;
import books_api.books_api.services.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookServiceImpl;

    @Mock
    private BookResponseDTO testResponseObj;

    @Test
    public void retrieveAllBooks() throws Exception{
        Mockito.when(bookServiceImpl.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }




}
