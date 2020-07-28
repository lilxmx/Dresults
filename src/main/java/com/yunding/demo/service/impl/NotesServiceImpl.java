package com.yunding.demo.service.impl;

import com.yunding.demo.dto.PublicNotesDto;
import com.yunding.demo.form.DeletedNotesForm;
import com.yunding.demo.form.InsertNotesForm;
import com.yunding.demo.form.UpdateNotesForm;
import com.yunding.demo.mapper.NotesMapper;
import com.yunding.demo.service.NotesService;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Tie;
import java.util.List;

/**
 * @author guorui
 * @date 2020-07-18 19:45
 */
@Service
public class NotesServiceImpl implements NotesService {
    /**
     * 获取公开的笔记的信息
     */
    @Autowired
    private NotesMapper notesMapper;


    /**
     * 获取公开的笔记的信息
     */
    @Override
    public List<PublicNotesDto> getAllNotes() {

        return notesMapper.getAllNotes();
    }
    /**
     * 获取所有公开的笔记信息
     */
    @Override
    public List<PublicNotesDto> getAllPublicNotes() {

        return notesMapper.getAllPublicNotes();
    }
    /**
     * 获取所有公开的笔记信息
     */
    @Override
    public void insertNotes(InsertNotesForm insertNotesForm) {
        String title = insertNotesForm.getTitle();
        String content = insertNotesForm.getContent();
        String hasOpen = insertNotesForm.getHasOpen();
        String category = insertNotesForm.getCategory();
        String source = insertNotesForm.getSource();
        notesMapper.insertNotes(title,content,hasOpen,category,source);
    }

    /**
     * 更改笔记序号从1开始自增
     */
    @Override
    public void autoIncrement() {
        notesMapper.autoIncrement();
    }
    /**
     * 更改笔记序号从1开始自增
     */
    @Override
    public void deleteNotes(DeletedNotesForm deletedNotesForm) {
        int deleteId = deletedNotesForm.getDeletedId();
        notesMapper.deleteNotes(deleteId);
    }
    /**
     * 更改笔记序号从1开始自增
     */
    @Override
    public void updateNotesOrder(DeletedNotesForm deletedNotesForm) {
        int deleteId = deletedNotesForm.getDeletedId();
        notesMapper.updateNotesOrder(deleteId);
    }
    /**
     * 更新笔记内容
     * @param
     */
    @Override
    public void updateNotes(UpdateNotesForm updateNotesForm){
        String title = updateNotesForm.getTitle();
        String content = updateNotesForm.getContent();
        String hasOpen = updateNotesForm.getHasOpen();
        String category = updateNotesForm.getCategory();
        String source = updateNotesForm.getSource();
        int deleteId = updateNotesForm.getNoteId();
        notesMapper.updateNotes(title,content,hasOpen,category,source,deleteId);
    }
    /**
     * 查询笔记内容，返回指定笔记的全部信息
     * @param content
     * @return PublicNotesDto
     */
    @Override
    public List<PublicNotesDto> selectOneNote6(String content){
        StringBuilder content_1 = new StringBuilder('%' + content + '%');
        return notesMapper.selectOneNote6(String.valueOf(content_1),content);
    }
    /**
     * 查询笔记内容，返回指定笔记的全部信息
     * @param content
     * @return PublicNotesDto
     */
    @Override
    public List<PublicNotesDto> selectOneNote5(String content){
        StringBuilder content_1 = new StringBuilder('%' + content + '%');
        return notesMapper.selectOneNote5(String.valueOf(content_1),content);
    }

}
