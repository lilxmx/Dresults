package com.yunding.demo.form;

import lombok.Data;

/**
 * 表单-用于提交信息的类
 * @author guorui
 * @date 2020-07-19 16:25
 */
@Data
public class InsertNotesForm {
    private String title;
    private String content;
    private String hasOpen;
    private String category;
    private String source;
}
