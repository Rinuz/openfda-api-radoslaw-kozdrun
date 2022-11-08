package pl.kozdrun.openfdaapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RestExceptionDto {

    private String title;
    private String message;
    private int code;
    private LocalDateTime timestamp;
}