package ru.fixies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixies.model.Categories;

@Repository
public interface CategoriesRepository  extends JpaRepository<Categories, Long> {


}
