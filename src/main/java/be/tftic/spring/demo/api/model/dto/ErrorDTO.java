package be.tftic.spring.demo.api.model.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDTO {
    private final int statusCode;
    private final HttpStatus status;
    private final String message;

    public ErrorDTO(HttpStatus status, String message) {
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
    }
}
