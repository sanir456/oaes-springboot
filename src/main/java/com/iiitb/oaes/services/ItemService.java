package com.iiitb.oaes.services;

import com.iiitb.oaes.modal.DAO.ItemDao;
import com.iiitb.oaes.modal.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value = "itemService")
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    public Item getItem(Map<String,String> payload){
        int itemId = Integer.parseInt(payload.get("itemId"));
        Item item = itemDao.findByItemId(itemId);
        return item;
    }
}
