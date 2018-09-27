package rostyslav.popadynets.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rostyslav.popadynets.entity.enums.UserRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", indexes = @Index(columnList = "first_name, last_name"))
@ToString(exclude = {"laptops", "monitors", "pcs", "phones", "tablets", "tvs"})
public class User extends BaseEntity {
	
	@Column(name = "user_id", unique = true)
	private String userId;

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false, unique = true)
	private String password;
	
	@Enumerated(EnumType.ORDINAL) // 0 - адмін 1 - юзер
	private UserRole role;
	
	private String emaleVerificationToken;
	
	@Column(nullable = true, columnDefinition="boolean default false")
	private Boolean emailVarificationStatus;
	
	@JsonIgnore
	@OneToMany(mappedBy = "laptopUser")
	private List<Laptop> laptops;
	
	@JsonIgnore
	@OneToMany(mappedBy = "monitorUser")
	private List<Monitor> monitors;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pcUser")
	private List<PC> pcs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "phoneUser")
	private List<Phone> phones;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tabletUser")
	private List<Tablet> tablets;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tvUser")
	private List<TV> tvs;
	
}
