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
@Table(name = "tv", indexes = @Index(columnList = "manufacturer, price"))
public class TV extends BaseEntity {
	
	@Column(name = "tv_id", unique = true)
	private String tvId;
	
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

	@Column(columnDefinition = "DECIMAL(5,2)", name = "display_diagonal", nullable = false)
	private BigDecimal displayDiagonal;

	@Column(name = "resolution_display", nullable = false)
	private String resolutionDisplay; // розширення дисплея

	@Column(name = "smart_platform")
	private String smartPlatform; // Android 6.0

	@Column(name = "display_brightness")
	private String displayBrightness; // яркість дисплея (кд/м²)

	@Column(nullable = false)
	private String color;

	@Column(nullable = false)
	private boolean hdr;

	@Column(columnDefinition = "TEXT")
	private String additional; // доповнення

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(columnDefinition = "DECIMAL(9,2)", nullable = false)
	private BigDecimal price;

	@Column(columnDefinition = "DECIMAL(6,2)")
	private BigDecimal weight; // вага

	@Column(name = "dimensions_length", columnDefinition = "DECIMAL(6,2)")
	private BigDecimal dimensionsLength; // габарити

	@Column(name = "dimensions_width", columnDefinition = "DECIMAL(6,2)")
	private BigDecimal dimensionsWidth;

	@Column(name = "dimensions_height", columnDefinition = "DECIMAL(7,2)")
	private BigDecimal dimensionsHeight;

	@ManyToOne
	@JoinColumn(name = "tv_user_id")
	private User tvUser;

}
