package be.tftic.spring.demo.api.controller.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(
        basePackages = { "be.tftic.spring.demo.api.controller.demo" }
//        basePackageClasses = { TaskController.class, DemoController.class }
)
public class SpecificControllerAdvisor {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(Exception ex){
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                .body(ex.getMessage());
    }

}
