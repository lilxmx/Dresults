package com.yunding.demo.form;

import lombok.Data;

/**
 * @author guorui
 * @date 2020-07-20 11:36
 */
@Data
public class UpdateNotesForm {
    private String title;
    private String content;
    private String hasOpen;
    private String category;
    private String source;
    private int noteId;
}
