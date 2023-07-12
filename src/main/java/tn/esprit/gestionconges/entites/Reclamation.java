package tn.esprit.gestionconges.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@ToString
@NoArgsConstructor
public class Reclamation  implements Serializable  {
    public Long getIdRec() {
		return idRec;
	}
	public void setIdRec(Long idRec) {
		this.idRec = idRec;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescriptionReclamation() {
		return descriptionReclamation;
	}
	public void setDescriptionReclamation(String descriptionReclamation) {
		this.descriptionReclamation = descriptionReclamation;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRec ;
    private String username;
    private  String descriptionReclamation ;
    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) private Date createdAt = new Date();
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private Date updatedAt = new Date();
    @ManyToOne
    private User users ;
}
