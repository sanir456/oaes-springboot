package com.iiitb.oaes.modal.DAO;

import com.iiitb.oaes.modal.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ItemDao extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT * FROM item where course_id = ?1 and category = ?2  ORDER BY RAND() LIMIT ?3", nativeQuery = true)
    public List<Item> randomItem(int courseId,String category,int numberOfQuestion);

    public Item findByItemId(int itemId);
}
