package com.iiitb.oaes.controller;

import com.iiitb.oaes.modal.Item;
import com.iiitb.oaes.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ItemContoller {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    public ResponseEntity<?> getItem(@RequestBody Map<String,String> payload)
    {
        Item item = itemService.getItem(payload);
        return ResponseEntity.ok(item);
    }

}
