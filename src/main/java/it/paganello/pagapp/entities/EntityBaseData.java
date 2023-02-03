package it.paganello.pagapp.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class EntityBaseData {
	
	private @Id
	@GeneratedValue Long id;
	
	@Column(name = "CREATED")
	private LocalDateTime created;
	
	@Column(name = "CHANGED")
	private LocalDateTime changed;
	
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
}
