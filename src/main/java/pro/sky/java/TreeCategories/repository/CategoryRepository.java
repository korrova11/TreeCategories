package pro.sky.java.TreeCategories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.TreeCategories.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByChatAndName(Long chat, String name);
}
