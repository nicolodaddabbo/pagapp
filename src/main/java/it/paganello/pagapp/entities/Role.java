package it.paganello.pagapp.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="ROL_ROLES")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "CREATED")
	private LocalDateTime created;
	@Column(name = "CHANGED")
	private LocalDateTime changed;
	@Column(name="NAME")
	private String name;
	@OneToMany(mappedBy = "role")
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<UserToRole> getUserToRoles() {
		return userToRoles;
	}
	
	public void setUserToRoles(List<UserToRole> userToRoles) {
		this.userToRoles = userToRoles;
	}
}
