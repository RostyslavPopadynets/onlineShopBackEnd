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
public class LaptopDTO {

	@JsonIgnore
	private Long id;

	private String imageUrl1;

	private String imageUrl2;

	private String imageUrl3;

	private String imageUrl4;

	private String imageUrl5;

	private String laptopId;

	private String manufacturer;

	private String model;

	private String processor;

	private String diagonalScreen; // діагональ екрана

	private String ram;

	private String driveCapacity; // пам'ять

	private String color;

	private String graphicAdapter;

	private String battery;

	private String additional; // доповнення

	private String description;

	private BigDecimal weight; // вага

	private BigDecimal price;

	private BigDecimal dimensionsLength; // габарити

	private BigDecimal dimensionsWidth;

	private BigDecimal dimensionsHeight;

	private User laptopUser;

}
