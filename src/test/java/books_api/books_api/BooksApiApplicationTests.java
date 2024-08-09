package books_api.books_api;

import books_api.books_api.controller.BookRestController;
import books_api.books_api.services.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Collections;

@WebMvcTest(BookRestController.class)
class BooksApiApplicationTests {

	@Autowired
	private MockMvc mockTester;

	@MockBean
	private BookServiceImpl service;

	@Test
	void testRetrieveAllBooks() throws Exception {
		Mockito.when(service.findAll()).thenReturn(Collections.emptyList());

		mockTester.perform(get("/books"))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));

	}

}
