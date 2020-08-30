package com.yunding.demo.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性值与数据库中的属性值保持一致。 实现set和get方法。
 * @author guorui
 * @date 2020-07-18 16:11
 */

//表明当前类是一个Java Bean
@Component
//表示获取前缀为notes的配置信息
@ConfigurationProperties(prefix = "notes")
//@Data
public class Notes {
    private int noteId;
    private String title;
    private String content;
    private String hasOpen;
    private String category;
    private String creatTime;
    private String source;
    private int favor;
    private int dislike;


    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    private String Introduction;
    public String getSquarePicture() {
        return squarePicture;
    }

    public void setSquarePicture(String squarePicture) {
        this.squarePicture = squarePicture;
    }

    private String squarePicture;


    public Notes(int noteId) {
        this.noteId = noteId;
    }

    public Notes(int noteId, String title, String content, String hasOpen, String category) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.hasOpen = hasOpen;
        this.category = category;
    }

    public Notes() {
    }

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHasOpen() {
        return hasOpen;
    }

    public void setHasOpen(String hasOpen) {
        this.hasOpen = hasOpen;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
