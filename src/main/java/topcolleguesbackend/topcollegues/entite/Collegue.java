package topcolleguesbackend.topcollegues.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Collegue {

	@Id
	@Column(name = "Pseudo", unique = true)
	private String pseudo;

	@Column
	private String imageUrl;

	@Column(nullable = false)
	private Integer score = 0;
	
	@OneToMany
	private List<Comment> avis;

	public Collegue() {
		this.avis = new ArrayList<>();
	}
	
	public Collegue(String pseudo, String imageUrl, Integer score) {
		this.pseudo = pseudo;
		this.imageUrl = imageUrl;
		this.score = score;
		this.avis = new ArrayList<>();
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public List<Comment> getAvis() {
		return avis;
	}

	public void setAvis(List<Comment> avis) {
		this.avis = avis;
	}
}
