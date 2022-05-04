package ru.fixies.fixies.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fixies.fixies.model.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query(
            value = "SELECT models.*, categories.*, brands.* " +
                    "FROM models, categories, brands " +
                    "WHERE models.category_id = categories.id AND " +
                    "models.brand_id = brands.id " +
                    "ORDER BY category_id DESC ",
            nativeQuery = true)
    Page<Model> findAllModelsPageable(Pageable pageable);

    // 1st option - category, brand, model selected separately

    @Query(
            "SELECT m FROM Model m"
    )

//    @Query(
//            value = "SELECT * " +
//                    "FROM models ",
////                    "ORDER BY id ",
//            nativeQuery = true)
    List<Model> findAllModelsForOrderPage();

    @Query(
            value = "SELECT * " +
                    "FROM models " +
                    "WHERE brand_id = :selectedBrandId " +
                    "ORDER BY id ",
            nativeQuery = true)
    List<Model> findModelsBySelectedBrandForOrderPage(Long selectedBrandId);


    @Query(
            value = "SELECT models.category_id " +
                    "FROM models " +
                    "ORDER BY id DESC ",
            nativeQuery = true)
    List<Model> findAllCategoriesForOrderPageDistinct();


//    @Query(
//            value = "SELECT models.id, models.brand_id, models.category_id " +
//                    "FROM models " +
//                    "WHERE models.category_id = :selectedCategoryId ",
//
//            nativeQuery = true)

    @Query(
            value = "SELECT DISTINCT b " +
                    "FROM Model b " +
                    "WHERE b.category.id=:selectedCategoryId "

    )
    List<Model> findBrandsBySelectedCategory(Long selectedCategoryId);

    //    @Query(
//            value = "SELECT DISTINCT brands.id " +
//                    "FROM brands " +
//                    "WHERE id = :selectedBrandId ",
//
//            nativeQuery = true)
    @Query(
            value = "SELECT DISTINCT m " +
                    "FROM Model m " +
                    "WHERE m.brand.id=:selectedBrandId AND m.category.id=:selectedCategoryId"

    )
    List<Model> findModelsBySelectedBrand(Long selectedBrandId, Long selectedCategoryId);

}
