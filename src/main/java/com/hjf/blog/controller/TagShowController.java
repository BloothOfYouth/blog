package com.hjf.blog.controller;

import com.github.pagehelper.PageInfo;
import com.hjf.blog.dao.BlogDao;
import com.hjf.blog.dao.TagDao;
import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Tag;
import com.hjf.blog.service.IBlogService;
import com.hjf.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

/**
 * @author hjf
 */
@Controller
public class TagShowController {

    @Autowired
    private ITagService tagService;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private IBlogService blogService;

    //标签页一页展示的博客数
    private final static int blogsSize = 6;

    /**
     * 跳转到tags页面
     * @param tagId
     * @param start
     * @param model
     * @return
     */
    @GetMapping("/tags/{tagId}/{start}")
    public String tags(@PathVariable("tagId") Long tagId, @PathVariable("start") Integer start, Model model){
        List<Tag> tags = tagService.orderFindAllTag();
        if(tagId == -1){
            tagId = tags.get(0).getId();
        }
        PageInfo<Blog> pageInfo = blogService.listBlogsByTagId(tagId, start, blogsSize);
        model.addAttribute("tags",tags);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tagId",tagId);
        return "tags";
    }
}
