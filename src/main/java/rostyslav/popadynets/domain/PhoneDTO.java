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
public class PhoneDTO {

	@JsonIgnore
	private Long id;

	private String imageUrl1;

	private String imageUrl2;

	private String imageUrl3;

	private String imageUrl4;

	private String imageUrl5;

	private String phoneId;

	private String manufacturer;

	private String model;

	private BigDecimal displayDiagonal;

	private String resolutionDisplay; // Розширення дисплея

	private String ram; //

	private String os;

	private String driveCapacity; // пам'ять

	private String color;

	private String numberSim;

	private String maxFlashMemory;

	private String frontalCamera; // мегапиксель

	private String mainCamera; // мегапиксель (Мп)

	private boolean flash;

	private boolean autofocus;

	private String coreNumber;

	private BigDecimal frequency; // частота (ГГц)

	private String batteryCapacity; // мА*ч

	private String additional; // доповнення

	private String description;

	private BigDecimal weight; // вага

	private BigDecimal price;

	private BigDecimal dimensionsLength; // габарити

	private BigDecimal dimensionsWidth;

	private BigDecimal dimensionsHeight;

	private User phoneUser;

}
