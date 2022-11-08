package pl.kozdrun.openfdaapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class FdaApiResponseException extends ApplicationException {

    private String fdaCallUrl;

    public FdaApiResponseException(String fdaCallUrl, String message, HttpStatus status) {
        super(message, status);
        this.fdaCallUrl = fdaCallUrl;
    }
}