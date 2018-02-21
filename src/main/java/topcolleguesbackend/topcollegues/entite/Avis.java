package topcolleguesbackend.topcollegues.entite;

public class Avis {
	
	private static int compteur = 0;
	
	private Integer id;
	private Collegue collegue;
	private String vote;
	
	/**
	 * @param collegue
	 * @param avis
	 */
	public Avis(Collegue collegue, String vote) {
		super();
		this.id = ++compteur;
		this.collegue = collegue;
		this.vote = vote;
	}

	/**
	 * Getter for the collegue
	 * @return the collegue
	 */
	public Collegue getCollegue() {
		return collegue;
	}

	/**
	 * Getter for the avis
	 * @return the avis
	 */
	public String getAvis() {
		return vote;
	}

	/**
	 * Getter for the id
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

}
