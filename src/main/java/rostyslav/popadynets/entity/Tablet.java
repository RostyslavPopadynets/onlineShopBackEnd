package rostyslav.popadynets.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tablet", indexes = @Index(columnList = "manufacturer, price"))
public class Tablet extends BaseEntity {

	@Column(name = "tablet_id", unique = true)
	private String tabletId;
	
	@Column(name = "image_url1")
	private String imageUrl1;
	
	@Column(name = "image_url2")
	private String imageUrl2;

	@Column(name = "image_url3")
	private String imageUrl3;
	
	@Column(name = "image_url4")
	private String imageUrl4;
	
	@Column(name = "image_url5")
	private String imageUrl5;
	
	@Column(nullable = false)
	private String manufacturer;

	@Column(nullable = false)
	private String model;

	@Column(columnDefinition = "DECIMAL(4,1)", name = "display_diagonal", nullable = false)
	private BigDecimal displayDiagonal;
	
	@Column(name = "resolution_display", nullable = false)
	private String resolutionDisplay; // Розширення дисплея
	
	@Column(nullable = false)
	private String ram; //

	@Column(nullable = false)
	private String os;

	@Column(name = "drive_capacity", nullable = false)
	private String driveCapacity; // пам'ять

	@Column(nullable = false)
	private String color;
	
	@Column(nullable = false)
	private String processor;
	
	@Column(name = "max_flash_memory", nullable = false)
	private String maxFlashMemory;
	
	@Column(name = "frontal_camera", nullable = false)
	private String frontalCamera; // мегапиксель
	
	@Column(name = "main_camera", nullable = false)
	private String mainCamera; // мегапиксель (Мп)
	
	@Column(name = "core_number", nullable = false)
	private String coreNumber;
	
	@Column(columnDefinition = "DECIMAL(3,1)", nullable = false)
	private BigDecimal frequency; // частота (ГГц)
	
	@Column(name = "battery_capacity", nullable = false)
	private String batteryCapacity; // мА*ч
	
	@Column(columnDefinition = "TEXT")
	private String additional; // доповнення
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private BigDecimal weight; // вага
	
	@Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
	private BigDecimal price;
	
	@Column(name = "dimensions_length", columnDefinition = "DECIMAL(6,2)")
	private BigDecimal dimensionsLength; // габарити
	
	@Column(name = "dimensions_width", columnDefinition = "DECIMAL(6,2)")
	private BigDecimal dimensionsWidth;
	
	@Column(name = "dimensions_height", columnDefinition = "DECIMAL(6,2)")
	private BigDecimal dimensionsHeight;
	
	@ManyToOne
	@JoinColumn(name = "tablet_user_id")
	private User tabletUser;

}



