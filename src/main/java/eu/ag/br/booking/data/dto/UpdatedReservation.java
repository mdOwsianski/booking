package eu.ag.br.booking.data.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatedReservation {

	private Long tableId;
	private Long reservationId;
	private Integer updatedRows;
}
