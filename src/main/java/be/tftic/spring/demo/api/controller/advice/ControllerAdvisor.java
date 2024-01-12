package be.tftic.spring.demo.api.controller.advice;

import be.tftic.spring.demo.api.model.dto.ErrorDTO;
import be.tftic.spring.demo.api.model.dto.ValidationErrorDTO;
import be.tftic.spring.demo.bll.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorDTO(
                                HttpStatus.NOT_FOUND,
                                ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handle(MethodArgumentNotValidException ex){
        return ResponseEntity.status( HttpStatus.UNPROCESSABLE_ENTITY )
                .body(
                        new ValidationErrorDTO(
                                HttpStatus.UNPROCESSABLE_ENTITY,
                                "invalid form",
                                ex.getBindingResult()

                        )
                );
    }

}
