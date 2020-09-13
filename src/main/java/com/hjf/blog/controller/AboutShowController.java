package com.hjf.blog.controller;

import com.hjf.blog.entity.Tag;
import com.hjf.blog.entity.Type;
import com.hjf.blog.entity.User;
import com.hjf.blog.service.ITagService;
import com.hjf.blog.service.ITypeService;
import com.hjf.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author hjf
 */
@Controller
public class AboutShowController {

    @Autowired
    private ITypeService typeService;

    /**
     * 跳转到about页面
     * @param model
     * @return
     */
    @GetMapping("/about")
    public String about(Model model){
        List<Type> types = typeService.findSixType();
        model.addAttribute("types",types);
        return "about";
    }
}
