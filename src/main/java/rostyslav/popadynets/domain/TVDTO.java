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
public class TVDTO {

	@JsonIgnore
	private Long id;
	
	private String imageUrl1;
	
	private String imageUrl2;

	private String imageUrl3;
	
	private String imageUrl4;
	
	private String imageUrl5;

	private String TVId;

	private String manufacturer;

	private String model;

	private BigDecimal displayDiagonal;

	private String resolutionDisplay; // розширення дисплея

	private String smartPlatform; // Android 6.0

	private String displayBrightness; // яркість дисплея (кд/м²)

	private String color;

	private boolean hdr;

	private String additional; // доповнення

	private String description;

	private BigDecimal price;

	private BigDecimal weight; // вага

	private BigDecimal dimensionsLength; // габарити

	private BigDecimal dimensionsWidth;

	private BigDecimal dimensionsHeight;

	private User tvUser;
}
