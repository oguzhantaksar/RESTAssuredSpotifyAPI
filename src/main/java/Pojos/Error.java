
package Pojos;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "error"
})
@Generated("jsonschema2pojo")
public class Error {

    @JsonProperty("error")
    private ErrorInner error;

    @JsonProperty("error")
    public ErrorInner getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(ErrorInner error) {
        this.error = error;
    }

}
