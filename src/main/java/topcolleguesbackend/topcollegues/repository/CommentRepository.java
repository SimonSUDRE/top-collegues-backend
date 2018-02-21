package topcolleguesbackend.topcollegues.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import topcolleguesbackend.topcollegues.entite.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
