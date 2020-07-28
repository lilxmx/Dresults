package com.yunding.demo.dto;

import lombok.Data;

/**
 * DTO-用于接受响应返回参数信息的类
 * DTO(Data Transfer Object)
 * @author guorui
 * @date 2020-07-18 19:37
 */
@Data
public class PublicNotesDto {
    private int noteId;
    private String title;
    private String content;
    private String hasOpen;
    private String category;
    private String creatTime;
    private String source;
    private int favor;
    private int dislike;

}
