package com.hjf.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.hjf.blog.entity.Type;
import com.hjf.blog.service.ITypeService;
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
public class TypeController {

    @Autowired
    private ITypeService typeService;

    final private static int pageSize = 8;//一页展示多少

    /**
     * 跳转到types页面
     * @return
     */
    @GetMapping("/types")
    public String typesPage(){
        return "admin/types";
    }

    /**
     * 分页查询展示
     * @param pageNum
     * @return
     */
    @GetMapping("/types/{pageNum}")
    @ResponseBody
    public PageInfo<Type> listTypes(@PathVariable("pageNum") int pageNum){
        PageInfo<Type> pageInfo = typeService.listType(pageNum, pageSize);
        return pageInfo;
    }

    /**
     * 跳转到types-input页面
     * @return
     */
    @GetMapping("/types/input")
    public String types_inputPage(){
        return "admin/types-input";
    }

    /**
     * 添加type
     * @param type
     * @param redirectAttributes
     * @param request
     * @return
     */
    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes redirectAttributes, HttpServletRequest request){
        boolean isAdd = typeService.addType(type);
        if(isAdd == false){
            request.setAttribute("error","添加失败，已存在["+type.getTypeName()+"]");
            return "admin/types-input";
        }else{
            redirectAttributes.addFlashAttribute("success","添加成功，["+type.getTypeName()+"]");
            return "redirect:/admin/types";
        }
    }

    /**
     * 跳转到更新页面
     * @return
     */
    @GetMapping("/types/update/{id}")
    public String updateTypePage(@PathVariable("id") Long id, HttpServletRequest request){
        Type type = typeService.findType(id);
        request.setAttribute("type",type);
        return "admin/types-update";
    }

    /**
     * 更新type
     * @param type
     * @param redirectAttributes
     * @param request
     * @return
     */
    @PostMapping("/types/update")
    public String updateType(Type type, RedirectAttributes redirectAttributes, HttpServletRequest request){
        boolean isUpdate = typeService.updateType(type);
        if(isUpdate == false){
            request.setAttribute("error","修改失败，已存在["+type.getTypeName()+"]");
            return "admin/types-update";
        }else{
            redirectAttributes.addFlashAttribute("success","修改成功，["+type.getTypeName()+"]");
            return "redirect:/admin/types";
        }
    }

    /**
     * 删除type
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/types/delete/{id}")
    public String deleteType(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("success","删除成功");
        return "redirect:/admin/types";
    }
}
