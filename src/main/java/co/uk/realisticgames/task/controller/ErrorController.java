package co.uk.realisticgames.task.controller;

import co.uk.realisticgames.task.model.api.ResponseItem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ErrorController class.
 *
 * @author Paulius Matulionis
 */
@ControllerAdvice
public class ErrorController {

    /**
     * Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    /**
     * Handles not found exception thrown by spring.
     *
     * @param ex not found exception
     * @return response entity with the necessary error details for the response
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseItem> handle(NoHandlerFoundException ex) {
        logger.warn("No handler found: {}", ex.getMessage());

        ResponseItem error = new ResponseItem();
        error.setMessage("Resource not found.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles unexpected exceptions thrown form anywhere in the system.
     *
     * @param ex exception
     * @return response entity with the necessary error details for the response
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseItem> handle(Throwable ex) {
        logger.error("Unexpected error occurred.", ex);

        ResponseItem error = new ResponseItem();
        error.setMessage("Unexpected error occurred");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
