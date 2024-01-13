package kielvien.co.id.hotelreserve.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditInfo {
	private String reffId;
	private String timeStamp;
}
