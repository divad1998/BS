package com.budgit.exceptionHandler;

import com.budgit.exception.BlankFieldException;
import com.budgit.exception.EmptyFieldException;
import com.budgit.exception.FieldValueOutOfRangeException;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import reactor.core.publisher.Mono;

import javax.management.monitor.MonitorNotification;

/**
 *
 * Handles all exceptions thrown within the Controllers.
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    /**
     *
     * Handles {@link EmptyFieldException} thrown within the Controllers. //ToDo: Controllers or a particular Controller?
     * @param e The thrown exception.
     * @return {@link ResponseEntity} encapsulating {@link org.springframework.http.HttpStatus}  and exception message.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<String> handleEmptyFieldException(EmptyFieldException e) {
        logger.error(e.getMessage());
        return ResponseEntity
                            .badRequest()
                            .body(e.getMessage());
    }

    /**
     *
     * Handles BlankFieldException thrown within the Controllers. //ToDo: will this need change?
     * @param e The thrown exception.
     * @return ResponseEntity encapsulating Http.BAD_REQUEST and exception message
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(BlankFieldException.class)
    public ResponseEntity<String> handleBlankFieldException(BlankFieldException e) {
        logger.error(e.getMessage());
        return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
    }

    /**
     *
     * Handles HttpMessageNotReadableException thrown within the Controllers. //ToDo: will this need change?
     * @param e The thrown exception.
     * @return ResponseEntity encapsulating Http.BAD_REQUEST and exception message.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error(e.getMessage());
        return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
    }

    /**
     *
     * Handles FieldValueOutOfRangeException thrown within the Controllers. //ToDo: will this need change?
     * @param e The Thrown Exception.
     * @return ResponseEntity encapsulating Http.BAD_REQUEST and exception message.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(FieldValueOutOfRangeException.class)
    public ResponseEntity<String> handleFieldValueOutOfRangeException(FieldValueOutOfRangeException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}