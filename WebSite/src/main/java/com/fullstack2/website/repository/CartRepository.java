package com.fullstack2.webSite.repository;

import com.fullstack2.webSite.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByMember(Long member);
}
