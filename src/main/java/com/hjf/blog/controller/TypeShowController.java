package com.hjf.blog.controller;

import com.github.pagehelper.PageInfo;
import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Type;
import com.hjf.blog.service.IBlogService;
import com.hjf.blog.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjf
 */
@Controller
public class TypeShowController {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IBlogService blogService;

    //类型页一页展示的博客数
    private final static int blogsSize = 6;

    /**
     * 跳转到types页面
     * @param typeId
     * @param start
     * @param model
     * @return
     */
    @GetMapping("/types/{typeId}/{start}")
    public String types(@PathVariable("typeId") Long typeId, @PathVariable("start") Integer start, Model model){
        List<Type> types = typeService.orderFindAllType();
        if(typeId == -1){
            typeId = types.get(0).getId();
        }
        PageInfo<Blog> pageInfo = blogService.listBlogsByTypeId(typeId, start, blogsSize);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeId",typeId);
        return "types";
    }


}
