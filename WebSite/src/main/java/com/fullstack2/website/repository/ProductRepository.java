package com.fullstack2.webSite.repository;


import com.fullstack2.webSite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAllByCategory(String category);

    List<Product> findByCategory(String item);
}
