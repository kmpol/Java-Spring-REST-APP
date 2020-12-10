package pl.karol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karol.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
