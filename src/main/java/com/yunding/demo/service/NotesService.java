package com.yunding.demo.service;

import com.yunding.demo.dto.PublicNotesDto;
import com.yunding.demo.form.DeletedNotesForm;
import com.yunding.demo.form.InsertNotesForm;
import com.yunding.demo.form.UpdateNotesForm;

import java.util.List;

/**
 * 业务层，存放业务逻辑处理，不直接对数据库进行操作，有接口和接口实现类，提供controller层调用的方法。
 * @author guorui
 * @date 2020-07-18 19:42
 */

public interface NotesService {
    /**
     * 获取公开的笔记的信息
     * @return PublicNotesDto
     */
    List<PublicNotesDto> getAllNotes();
    /**
     * 获取所有公开的笔记信息
     * @return PublicNotesDto
     */
    List<PublicNotesDto> getAllPublicNotes();
    /**
     * 插入笔记信息
     * @param insertNotesForm
     */
    void insertNotes(InsertNotesForm insertNotesForm);
    /**
     * 更改笔记序号从1开始自增
     */
    void autoIncrement();
    /**
     * 删除某条笔记
     * @param deletedNotesForm
     */
    void deleteNotes(DeletedNotesForm deletedNotesForm);
    /**
     *更新笔记序号
     * @param deletedNotesForm
     */
    void updateNotesOrder(DeletedNotesForm deletedNotesForm);
    /**
     * 更新笔记内容
     * @param
     */
    void updateNotes(UpdateNotesForm updateNotesForm);
    /**
     * 查询笔记内容，返回指定笔记的全部信息
     * @param content
     * @return PublicNotesDto
     */
    List<PublicNotesDto> selectOneNote6(String content);
    /**
     * 查询笔记内容，返回指定笔记的全部信息
     * @param content
     * @return PublicNotesDto
     */
    List<PublicNotesDto> selectOneNote5(String content);
}
