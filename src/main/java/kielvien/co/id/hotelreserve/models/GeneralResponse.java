package kielvien.co.id.hotelreserve.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse<T> {
	private String responseCode;
	private String desc;
	private T data;
}
