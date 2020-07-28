package com.yunding.demo;

import com.yunding.demo.entity.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guorui
 * @date 2020-07-18 19:03
 */
@RestController
public class NewController {
    @Autowired
    private Notes notes;

    @RequestMapping("/notes2")
    public String showNotes(){
        return notes.getContent();
    }
}
