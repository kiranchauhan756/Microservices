package Rest.Services.rest.services.learning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int  errorCode;
    private String errorMessage;
    private long timeStamp;
}
