package topcolleguesbackend.topcollegues.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import topcolleguesbackend.topcollegues.entite.Collegue;
import topcolleguesbackend.topcollegues.entite.Comment;
import topcolleguesbackend.topcollegues.repository.CollegueRepository;
import topcolleguesbackend.topcollegues.repository.CommentRepository;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	private CollegueRepository colRepo;
	
	@Autowired
	private CommentRepository comRepo;

	@Override
	public void initialiser() {
		List<Comment> coms = new ArrayList<>();
		Comment c = new Comment("azeazeazeaze");
		comRepo.save(c);
		coms.add(c);
		Collegue col = new Collegue("Aline", "assets/images/aline.jpg", 100);
		col.setAvis(coms);
		colRepo.save(col);
		colRepo.save(new Collegue("Hugues", "assets/images/hugues.jpg", 100));
		colRepo.save(new Collegue("Paul", "assets/images/paul.jpg", 20));
		colRepo.save(new Collegue("Justine", "assets/images/justine.jpg", 20));
		colRepo.save(new Collegue("Jaques", "assets/images/jaques.jpg", 10));
	}
}