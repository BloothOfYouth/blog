package com.hjf.blog.controller;

import com.hjf.blog.entity.Blog;
import com.hjf.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author hjf
 */
@Controller
public class ArchiveShowController {


    @Autowired
    private IBlogService blogService;

    /**
     * 跳转到archives页面
     * @param model
     * @return
     */
    @GetMapping("/archives")
    public String archives(Model model){
        Map<String, List<Blog>> map = blogService.archiverBlog();
        Integer blogsCount = blogService.getTotalBlogsCount();
        model.addAttribute("map",map);
        model.addAttribute("blogsCount",blogsCount);
        return "archives";
    }
}
