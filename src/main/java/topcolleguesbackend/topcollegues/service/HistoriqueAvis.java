package topcolleguesbackend.topcollegues.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import topcolleguesbackend.topcollegues.entite.Avis;

@Service
public class HistoriqueAvis {
	
	private List<Avis> historique = new ArrayList<>();
	
	public void newAvis(Avis newAvis) {
		historique.add(newAvis);
	}
	
	public List<Avis> getHistorique(Integer since) {
		Avis sinceAvis = historique.stream().filter(v -> v.getId().intValue() == since.intValue()).findFirst().orElse(null);
		if(sinceAvis == null) {
			return new ArrayList<>();
		}
		return historique.subList(historique.indexOf(sinceAvis),historique.size());
	}
	
	public List<Avis> getDefaultHistorique() {
		if(historique.isEmpty()) {
			return new ArrayList<>();
		}else if(historique.size()<3) {
			return historique.subList(0, historique.size());
		}else {
			return historique.subList(historique.size()-3, historique.size());
		}
	}
	
	public Avis delete(Integer avisId) {
		Avis delAvis = historique.stream().filter(v -> v.getId().intValue() == avisId.intValue()).findFirst().orElse(null);
		historique.remove(delAvis);
		return delAvis;
	}

}