package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
