package pl.karol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karol.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
