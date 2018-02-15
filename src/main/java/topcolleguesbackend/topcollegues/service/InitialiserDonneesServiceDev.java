package topcolleguesbackend.topcollegues.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import topcolleguesbackend.topcollegues.entite.Collegue;
import topcolleguesbackend.topcollegues.repository.CollegueRepository;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	private CollegueRepository colRepo;

	@Override
	public void initialiser() {
		colRepo.save(new Collegue("Aline", "assets/images/aline.jpg", 100));
		colRepo.save(new Collegue("Hugues", "assets/images/hugues.jpg", 100));
		colRepo.save(new Collegue("Paul", "assets/images/paul.jpg", 20));
		colRepo.save(new Collegue("Justine", "assets/images/justine.jpg", 20));
		colRepo.save(new Collegue("Jaques", "assets/images/jaques.jpg", 10));
	}
}