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
@Table(name = "monitor", indexes = @Index(columnList = "manufacturer, price"))
public class Monitor extends BaseEntity {

	@Column(name = "monitor_id", unique = true)
	private String monitorId;
	
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

	@Column(name = "display_diagonal", columnDefinition = "DECIMAL(7,2)", nullable = false)
	private BigDecimal displayDiagonal;

	@Column(name = "max_resolution_display", nullable = false)
	private String maxResolutionDisplay; // Максимальне розширення дисплея

	@Column(name = "matrix_type")
	private String matrixType;

	@Column(name = "display_brightness")
	private String displayBrightness; // яркість дисплея (кд/м²)

	@Column(nullable = false)
	private String interfaces; //(DVI HDMI VGA)

	@Column(name = "attitude_parties", nullable = false)
	private String attitudeParties; // відношення сторін (16:9)

	@Column(nullable = false)
	private String color; 
	
	@Column(nullable = false)
	private boolean webcam;

	@Column(columnDefinition = "TEXT")
	private String additional; // доповнення
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "DECIMAL(8,2)", nullable = false)
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "monitor_user_id")
	private User monitorUser;

}

