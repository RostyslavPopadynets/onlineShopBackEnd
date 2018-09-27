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
@Table(name = "pc", indexes = @Index(columnList = "manufacturer, price"))
public class PC extends BaseEntity {

	@Column(name = "pc_id", unique = true)
	private String pcId;
	
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

	@Column(nullable = false)
	private String processor;

	@Column(nullable = false)
	private String ram;

	@Column(nullable = false)
	private String os;

	@Column(name = "drive_capacity", nullable = false)
	private String driveCapacity; // пам'ять

	@Column(nullable = false)
	private String color;
	
	private String cooling; // охолодження

	@Column(name = "graphic_adapter", nullable = false)
	private String graphicAdapter;

	@Column(columnDefinition = "TEXT")
	private String additional; // доповнення
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "DECIMAL(7,2)")
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
	@JoinColumn(name = "pc_user_id")
	private User pcUser;

}

