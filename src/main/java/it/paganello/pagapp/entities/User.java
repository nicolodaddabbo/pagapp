package it.paganello.pagapp.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="USE_USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "CREATED")
	private LocalDateTime created;
	@Column(name = "CHANGED")
	private LocalDateTime changed;
	@Column(name = "PASSWORD")
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<UserToRole> userToRoles;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	public LocalDateTime getChanged() {
		return changed;
	}
	
	public void setChanged(LocalDateTime changed) {
		this.changed = changed;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<UserToRole> getUserToRoles() {
		return userToRoles;
	}
	
	public void setUserToRoles(List<UserToRole> userToRoles) {
		this.userToRoles = userToRoles;
	}
}
