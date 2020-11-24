package com.launchdatesandshop.repositories;

import com.launchdatesandshop.entities.CartItem;
import com.launchdatesandshop.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


}
