package topcolleguesbackend.topcollegues.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Collegue {

	@Id
	@Column(name = "Pseudo", unique = true)
	private String pseudo;

	@Column
	private String imageUrl;

	@Column(nullable = false)
	private Integer score = 0;

	public Collegue() {}
	
	public Collegue(String pseudo, String imageUrl, Integer score) {
		super();
		this.pseudo = pseudo;
		this.imageUrl = imageUrl;
		this.score = score;
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
}
