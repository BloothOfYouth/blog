package com.hjf.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hjf.blog.entity.Tag;
import com.hjf.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hjf
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private ITagService tagService;

    final private static int pageSize = 8;//一页展示多少

    /**
     * 跳转到tags页面
     * @return
     */
    @GetMapping("/tags")
    public String tagsPage(){
        return "admin/tags";
    }

    /**
     * 分页查询展示
     * @param pageNum
     * @return
     */
    @GetMapping("/tags/{pageNum}")
    @ResponseBody
    public PageInfo<Tag> listTags(@PathVariable("pageNum") int pageNum){
        PageInfo<Tag> pageInfo = tagService.listTag(pageNum, pageSize);
        return pageInfo;
    }

    /**
     * 跳转到tags-input页面
     * @return
     */
    @GetMapping("/tags/input")
    public String tags_inputPage(){
        return "admin/tags-input";
    }

    /**
     * 添加tag
     * @param tag
     * @param redirectAttributes
     * @param request
     * @return
     */
    @PostMapping("/tags")
    public String addTag(Tag tag, RedirectAttributes redirectAttributes, HttpServletRequest request){
        boolean isAdd = tagService.addTag(tag);
        if(isAdd == false){
            request.setAttribute("error","添加失败，已存在["+tag.getTagName()+"]");
            return "admin/tags-input";
        }else{
            redirectAttributes.addFlashAttribute("success","添加成功，["+tag.getTagName()+"]");
            return "redirect:/admin/tags";
        }
    }

    /**
     * 跳转到更新页面
     * @return
     */
    @GetMapping("/tags/update/{id}")
    public String updateTagPage(@PathVariable("id") Long id, HttpServletRequest request){
        Tag tag = tagService.findTag(id);
        request.setAttribute("tag",tag);
        return "admin/tags-update";
    }

    /**
     * 更新tag
     * @param tag
     * @param redirectAttributes
     * @param request
     * @return
     */
    @PostMapping("/tags/update")
    public String updateTag(Tag tag, RedirectAttributes redirectAttributes, HttpServletRequest request){
        boolean isUpdate = tagService.updateTag(tag);
        if(isUpdate == false){
            request.setAttribute("error","修改失败，已存在["+tag.getTagName()+"]");
            return "admin/tags-update";
        }else{
            redirectAttributes.addFlashAttribute("success","修改成功，["+tag.getTagName()+"]");
            return "redirect:/admin/tags";
        }
    }

    /**
     * 删除tag
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/tags/delete/{id}")
    public String deleteTag(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("success","删除成功");
        return "redirect:/admin/tags";
    }
}
