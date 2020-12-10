package pl.karol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karol.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
