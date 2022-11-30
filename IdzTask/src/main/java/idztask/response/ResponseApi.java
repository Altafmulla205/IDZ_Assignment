package idztask.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseApi {

	private String message;
	private Boolean error;
	private Object data;
}
