package ru.fixies.fixies.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fixies.fixies.model.Category;


import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query(
            value = "SELECT * " +
                    "FROM categories " +
                    "ORDER BY id DESC ",
            countQuery = "SELECT count(*) FROM categories ",
            nativeQuery = true)
    Page<Category> findAllCategories(Pageable pageable);

    // 1st option - category, brand, model selected separately


    //    @Query(
//            value = "SELECT * " +
//                    "FROM categories " +
//                    "ORDER BY id DESC ",
//            nativeQuery = true)
    @Query(
            value = "SELECT DISTINCT c " +
                    "FROM Category c"
    )
    List<Category> findAllCategoriesForOrderPage();

}
