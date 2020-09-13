package com.hjf.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Tag;
import com.hjf.blog.entity.Type;
import com.hjf.blog.entity.User;
import com.hjf.blog.service.IBlogService;
import com.hjf.blog.service.ITagService;
import com.hjf.blog.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hjf
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ITypeService typeService;

    @Autowired
    private ITagService tagService;

    final private static int pageSize = 6;//一页展示多少

    /**
     * 跳转到blogs页面
     * @return
     */
    @GetMapping("/blogs")
    public String blogsPage(){
        return "admin/blogs";
    }

    /**
     * 给blogs和blogs-input页面的分类选项
     * @return
     */
    @GetMapping("/blogs-types")
    @ResponseBody
    public List<Type> getAllTypes(){
        List<Type> types = typeService.findAllType();
        return types;
    }

    /**
     * 给blogs-input页面的标签选项
     * @return
     */
    @GetMapping("/blogs-tags")
    @ResponseBody
    public List<Tag> getAllTags(){
        List<Tag> tags = tagService.findAllTag();
        return tags;
    }


    /**
     * 分页，条件查询Blog
     * @param pageNum
     * @param title
     * @param typeId
     * @param recommend
     * @return
     */
    @PostMapping("/blogs")
    @ResponseBody
    public PageInfo<Blog> listBlogs(
            @RequestParam Integer pageNum,@RequestParam String title,
            @RequestParam Long typeId,@RequestParam Boolean recommend){
        PageInfo<Blog> pageInfo = blogService.listBlog(pageNum, pageSize, title, typeId, recommend);

        return pageInfo;
    }

    /**
     * 跳转到blos-input页面
     * @return
     */
    @GetMapping("/blogs/input")
    public String blogs_input(){
        return "admin/blogs-input";
    }

    /**
     * 添加Blog
     * @param blog
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/blogs/input")
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes redirectAttributes){
        if(blog.getAppreciation() == null){
            blog.setAppreciation(false);
        }
        if(blog.getShareStatement() == null){
            blog.setShareStatement(false);
        }
        if(blog.getCommentabled() == null){
            blog.setCommentabled(false);
        }
        if(blog.getRecommend() == null){
            blog.setRecommend(false);
        }
        blog.setType(typeService.findType(blog.getType().getId()));
        User user = (User) session.getAttribute("user");
        blog.setUser(user);
        blog.setTags(tagService.findSomeTag(blog.getTagIds()));
        boolean isAdd = blogService.addBlog(blog);
        if(isAdd){
            redirectAttributes.addFlashAttribute("success","添加成功，["+blog.getTitle()+"]");
        }else{
            redirectAttributes.addFlashAttribute("error","添加失败，["+blog.getTitle()+"]");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 跳转到更新页面
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/blogs/update/{id}")
    public String updateBlogPage(@PathVariable("id") Long id, HttpServletRequest request){
        Blog blog = blogService.findBlog(id);
        request.setAttribute("blog",blog);
        return "admin/blogs-update";
    }

    /**
     * 更新Blog
     * @param blog
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/blogs/update")
    public String updateBlog(Blog blog, RedirectAttributes redirectAttributes){
        if(blog.getAppreciation() == null){
            blog.setAppreciation(false);
        }
        if(blog.getShareStatement() == null){
            blog.setShareStatement(false);
        }
        if(blog.getCommentabled() == null){
            blog.setCommentabled(false);
        }
        if(blog.getRecommend() == null){
            blog.setRecommend(false);
        }
        blog.setType(typeService.findType(blog.getType().getId()));
        blog.setTags(tagService.findSomeTag(blog.getTagIds()));
        boolean isUpdate = blogService.updateBlog(blog);
        if(isUpdate){
            redirectAttributes.addFlashAttribute("success","修改成功，["+blog.getTitle()+"]");
        }else{
            redirectAttributes.addFlashAttribute("error","修改失败，["+blog.getTitle()+"]");
        }
        return "redirect:/admin/blogs";
    }


    /**
     * 删除Blog
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/blogs/delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }
}
