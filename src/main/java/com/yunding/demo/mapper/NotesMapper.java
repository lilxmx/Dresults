package com.yunding.demo.mapper;

import com.yunding.demo.dto.PublicNotesDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao层
 * 数据库进行持久化操作，它的方法是针对数据库操作的，基本用到的就是增删改查。它只是个接口，只有方法名字，具体实现在mapper.xml中
 *
 * @author guorui
 */
@Repository
public interface NotesMapper {

    /**
     * 获取笔记广场里所有的笔记的信息
     *
     * @return
     */
    List<PublicNotesDto> getAllNotes();

    /**
     * 获取所有公开的笔记信息
     *
     * @return
     */
    List<PublicNotesDto> getAllPublicNotes();

    /**
     * 插入笔记信息
     *
     * @param title
     * @param content
     * @param hasOpen
     * @param category
     * @param source
     * @return
     */
    void insertNotes(String title, String content, String hasOpen, String category, String source);

    /**
     * 使笔记表的序号从1开始自增
     */
    void autoIncrement();

    /**
     * 删除某个笔记信息
     *
     * @param deletedId
     */
    void deleteNotes(int deletedId);

    /**
     * 更新笔记序号
     *
     * @param deletedId
     */
    void updateNotesOrder(int deletedId);
    /**
     * 更新笔记内容
     * @param
     */
    void updateNotes(String title,String content,String hasOpen,String category,String source,int noteId);
    /**
     * 查询笔记内容，返回指定笔记的全部信息
     * @param content
     * @param content_1
     * @return PublicNotesDto
     */
    List<PublicNotesDto> selectOneNote6(String content_1,String content);
    /**
     * 查询笔记内容，返回指定笔记的全部信息
     * @param content
     * @param content_1
     * @return PublicNotesDto
     */
    List<PublicNotesDto> selectOneNote5(String content_1,String content);
}
