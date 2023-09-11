package az.orient.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response {

    @JsonProperty(value = "response")
    private RespUser respUser;
    private RespStatus status;
}
