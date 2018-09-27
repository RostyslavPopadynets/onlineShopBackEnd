package rostyslav.popadynets.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@MappedSuperclass
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
}
