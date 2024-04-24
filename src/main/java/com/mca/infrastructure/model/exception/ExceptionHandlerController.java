package com.mca.infrastructure.model.exception;


import com.mca.domain.exception.NoSuchElementException;
import com.mca.infrastructure.model.RestErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;



@ControllerAdvice
public class ExceptionHandlerController {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);



    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<RestErrorDTO> forbiddenException(final NoSuchElementException ex,
                                                           final WebRequest request) {
        return this.generateResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(WebClientResponseException.NotFound.class)
    protected ResponseEntity<RestErrorDTO> handleWebClientNotFoundException(WebClientResponseException ex,
                                                                      WebRequest request) {
       return this.generateResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<RestErrorDTO> generateResponseEntity(final RuntimeException ex,
                                                                final WebRequest request,
                                                                final HttpStatus httpStatus) {
        final RestErrorDTO message = new RestErrorDTO();
        message.setStatus(httpStatus.value());
        message.setTitle(ex.getMessage());
        message.setDetail(request.getDescription(false));
        message.setTimestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).format(DATE_TIME_FORMATTER));
        return new ResponseEntity<>(message, httpStatus);
    }


}
