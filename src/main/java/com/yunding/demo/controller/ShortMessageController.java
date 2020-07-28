package com.yunding.demo.controller;

import com.yunding.demo.core.wrapper.ResultWrapper;
import com.yunding.demo.dto.MessageDto;
import com.yunding.demo.form.NumberForm;
import com.yunding.demo.util.ShortMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author guorui
 * @date 2020-07-24 15:36
 */
@RequestMapping("/message")
@RestController
@CrossOrigin
@Slf4j
public class ShortMessageController {
    @Autowired
    private ShortMessageUtil shortMessageUtil;

    @PutMapping("/")
    public ResultWrapper getSsm(@RequestBody NumberForm number) {
        //先设置为成功状态
        String successState = "OK";
        try {
            //发送信息
            MessageDto messageDto=shortMessageUtil.getSsm(number.getNumber());
            //如果状态为ok为成功
            if (successState.equals(messageDto.getState())) {
                return ResultWrapper.success("发送成功");
            }else {
                return ResultWrapper.failure("发送失败");
            }
        } catch (Exception e) {
            return ResultWrapper.failure(e.toString ());
        }

    }
}
