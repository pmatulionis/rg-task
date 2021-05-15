package co.uk.realisticgames.task.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * AbstractControllerTest class.
 *
 * @author Paulius Matulionis
 */
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {

    protected static final String API_PATH = "/api/v1";

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;
}
