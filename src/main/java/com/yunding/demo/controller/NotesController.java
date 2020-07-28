package com.yunding.demo.controller;

import com.yunding.demo.core.wrapper.ResultWrapper;
import com.yunding.demo.dto.PublicNotesDto;
import com.yunding.demo.entity.Notes;
import com.yunding.demo.form.DeletedNotesForm;
import com.yunding.demo.form.InsertNotesForm;
import com.yunding.demo.form.SelectOneNotes6;
import com.yunding.demo.form.UpdateNotesForm;
import com.yunding.demo.service.NotesService;
import com.yunding.demo.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器层，导入service层，调用service方法，controller通过接收前端传过来的参数进行业务操作
 * @author guorui
 * @date 2020-07-18 17:32
 */
@CrossOrigin
@Api(value = "NotesController", tags = {"笔记API"})
@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;
    // 创建线程安全的Map
    static Map<String, Notes> note = Collections.synchronizedMap(new HashMap<String, Notes>());

    /**
     * 根据ID查询用户
     * @return
     */
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息",produces = "application/json")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/other", method = RequestMethod.GET)
    public ResponseEntity<CommonResult> selectAllNotes (){
        CommonResult commonResult = new CommonResult();
        try {
            Notes notes ;
            commonResult.setResult("notes");
            commonResult.setStatus("ok");
        } catch (Exception e) {
            commonResult.setResult(e.getClass().getName() + ":" + e.getMessage());
            commonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(commonResult);
    }
    /**
     * 获取所有笔记的信息
     * @return
     */
    @ApiOperation(value="获取所有公开笔记", notes="获取所有公开笔记",produces = "application/json")
    @GetMapping("/allNotes")
    public ResultWrapper getAllNotes(){
        return ResultWrapper.successWithData(notesService.getAllNotes());
    }
    /**
     * 获取所有公开的笔记信息
     * @return
     */
    @ApiOperation(value="获取所有公开笔记", notes="获取所有公开笔记",produces = "application/json")
    @GetMapping("/allPublicNotes")
    public ResultWrapper getAllPublicNotes(){
        return ResultWrapper.successWithData(notesService.getAllPublicNotes());
    }
    /**
     * 插入笔记信息
     *
     */
    @ApiOperation(value="插入一条笔记", notes="插入一条笔记",produces = "application/json")
    @PostMapping("/insertNotes")
    public ResultWrapper insertNotes(@RequestBody InsertNotesForm insertNotesForm){
        try {
            notesService.autoIncrement();
            notesService.insertNotes(insertNotesForm);
            return ResultWrapper.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultWrapper.failure();
        }
    }
    /**
     * 删除笔记信息
     */
    @ApiOperation(value="删除一条笔记", notes="删除一条笔记",produces = "application/json")
    @PostMapping("/deleteNotes")
    public ResultWrapper deleteNotes(@RequestBody DeletedNotesForm deletedNotesForm){
        try {
            notesService.deleteNotes(deletedNotesForm);
            notesService.updateNotesOrder(deletedNotesForm);
            return ResultWrapper.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultWrapper.failure();
        }
    }
    /**
     * 更改笔记信息
     */
    @ApiOperation(value="更改一条笔记", notes="更改一条笔记",produces = "application/json")
    @PostMapping("/updateNotes")
    public ResultWrapper updateNotes(@RequestBody UpdateNotesForm updateNotesForm){
        try {
            notesService.autoIncrement();
            notesService.updateNotes(updateNotesForm);
            return ResultWrapper.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultWrapper.failure();
        }
    }
    /**
     * 查询笔记内容，返回指定笔记的全部信息
     * @param selectOneNotes
     * @return ResultWrapper
     */
    @ApiOperation(value="更改一条笔记", notes="更改一条笔记",produces = "application/json")
    @PostMapping("/selectOneNotes")
    public ResultWrapper updateNotes(@RequestBody SelectOneNotes6 selectOneNotes){
        try {
            String content = selectOneNotes.getContent();
            List<PublicNotesDto> notebook2 = notesService.selectOneNote6(content);
            List<PublicNotesDto> notebook1 = notesService.selectOneNote5(content);
            int c = 0;
            if (notebook2 != null && notebook2.size()>0 ) {
                for (int i = 0; i < notebook1.size(); i++) {
                    for (int j = 0; j < notebook2.size(); j++) {
                        int a = notebook1.get(i).getNoteId();
                        int b = notebook2.get(j).getNoteId();
                        if (a == b) {
                            notebook1.remove(i);
                            notebook1.add(c,notebook2.get(j));
                            notebook2.remove(j);
                            c++;
                        }
                    }
                }
                notebook1.addAll(notebook2);
            }
            return ResultWrapper.successWithData(notebook1);
        }catch (Exception e){
            e.printStackTrace();
            return ResultWrapper.failure();
        }
    }
}
