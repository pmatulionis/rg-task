package co.uk.realisticgames.task.test.controller;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ErrorControllerTest class.
 *
 * @author Paulius Matulionis
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ErrorControllerTest extends AbstractControllerTest {

    @Test
    public void testNotFound()
            throws Exception {
        mvc
                .perform(get(API_PATH + "/invalid-url"))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Resource not found."));
    }

    @Test
    public void testUnexpectedError()
            throws Exception {
        mvc
                .perform(post(API_PATH + "/car-data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid")
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().is(500))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Unexpected error occurred"));
    }
}
