package com.baorenai.bole.controller;

import com.baorenai.bole.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project boleWeb
 * @auther baorenai
 * @create 2017/11/6星期一
 */
@RequestMapping("/mail")
@RestController
public class MailController {
    @Autowired
    MailService mailService;

    @RequestMapping(value = "/test")
    public void testMail()
    {
        String title = "test";
        String content = "<html><body>hello world</body></html>";
        mailService.sendSimpleMail("baorenai@u51.com",title,content);

    }
}
