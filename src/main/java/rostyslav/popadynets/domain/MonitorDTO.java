package rostyslav.popadynets.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rostyslav.popadynets.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonitorDTO {

	@JsonIgnore
	private Long id;

	private String imageUrl1;

	private String imageUrl2;

	private String imageUrl3;

	private String imageUrl4;

	private String imageUrl5;

	private String monitorId;

	private String manufacturer;

	private String model;

	private BigDecimal displayDiagonal;

	private String maxResolutionDisplay; // Максимальне розширення дисплея

	private String matrixType;

	private String displayBrightness; // яркість дисплея (кд/м²)

	private String interfaces; // (DVI HDMI VGA)

	private String attitudeParties; // відношення сторін (16:9)

	private String color;

	private boolean webcam;

	private String additional; // доповнення

	private String description;

	private BigDecimal price;

	private User monitorUser;
}
