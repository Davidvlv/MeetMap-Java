package au.usyd.elec5619.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.AutoPopulatingList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "UserProfile")
public class UserProfile implements Serializable {

	public UserProfile() {
	}

	public UserProfile(User user, String description, Integer age) {
		this.user = user;
		this.username = user.getUsername();
		this.description = description;
		this.age = age;
		this.interests = new ArrayList<Interest>();
	}

	@JsonManagedReference
	@JsonIgnore
	private User user;

	private String username;

	@JsonIgnore
	private ProfileImage profileImage;

	private List<Interest> interests;
		
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@JoinColumn(name = "ProfileImageId", unique = true)
	@OneToOne(cascade = { CascadeType.ALL })
	public ProfileImage getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(ProfileImage profileImage) {
		this.profileImage = profileImage;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "User", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Integer age;

	private String description;

	@Column(name = "Age", nullable = true)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "Description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@PrimaryKeyJoinColumn
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
}
