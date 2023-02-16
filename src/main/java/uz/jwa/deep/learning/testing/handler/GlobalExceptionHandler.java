package uz.jwa.deep.learning.testing.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author TheJWA (Jonibek)
 * @since 2/16/2023 Thursday
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDTO> handleAll(RuntimeException e, WebRequest webRequest) {
        log.error("Internal Server Error Exception occurred with message  : {}, request : {}", e.getMessage(), webRequest.toString());
        return new ResponseEntity<>
                (new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
