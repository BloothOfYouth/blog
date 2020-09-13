package com.hjf.blog.controller;

import com.github.pagehelper.PageInfo;
import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Tag;
import com.hjf.blog.entity.Type;
import com.hjf.blog.service.IBlogService;
import com.hjf.blog.service.ITagService;
import com.hjf.blog.service.ITypeService;
import com.hjf.blog.util.MarkdownUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjf
 */
@Controller
public class indexController {

    @Autowired
    private IBlogService blogService;

    //展示给前端首页的博客一页展示数
    private final static int blogsSize = 5;

    //展示给前端首页最新推荐Blogs的Top
    private final static int blogsTopSize = 5;

    @Autowired
    private ITypeService typeService;

    //展示给前端首页最多blog type的Top
    private final static int typesTopSize = 6;

    @Autowired
    private ITagService tagService;

    //展示给前端首页最多blog tag的Top
    private final static int tagsTopSize = 7;

    /**
     * 跳转到前端首页
     * @return
     */
    @GetMapping({"/","/index.html","index"})
    public String index(){
        return "index";
    }

    /**
     * 找到前端index页面所需要的全部数据
     * @return
     */
    @GetMapping("/indexData/{start}")
    @ResponseBody
    public Map findIndexData(@PathVariable("start") int start){
        PageInfo<Blog> blogs = blogService.listBlogs(start, blogsSize);
        PageInfo<Blog> topBlogs = blogService.listBlogsTop(blogsTopSize);
        Integer totalBlogsCount = blogService.getTotalBlogsCount();
        PageInfo<Type> topTypes = typeService.findAllTypesTop(typesTopSize);
        PageInfo<Tag> topTags = tagService.findAllTagsTop(tagsTopSize);
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("blogs",blogs);
        dataMap.put("topBlogs",topBlogs);
        dataMap.put("totalBlogsCount",totalBlogsCount);
        dataMap.put("topTypes",topTypes);
        dataMap.put("topTags",topTags);
        return dataMap;
    }

    /**
     * 首页上一页或下一页刷新博客数据
     * @param start
     * @return
     */
    @GetMapping("/changePage/{start}")
    @ResponseBody
    public Map changeIndexPage(@PathVariable("start") int start){
        PageInfo<Blog> blogs = blogService.listBlogs(start, blogsSize);
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("blogs",blogs);
        return dataMap;
    }

    /**
     * 根据title或description查询博客(Blog)
     * @param query
     * @param start
     * @param request
     * @return
     */
    @GetMapping("/search/{start}/{query}")
    public String search(@PathVariable("query") String query,@PathVariable("start") int start, HttpServletRequest request){
        Map map = blogService.listQueryBlogs(query, start, blogsSize);
        request.setAttribute("map",map);
        request.setAttribute("query",query);
        return "search";
    }

    /**
     * 进入博客详情页面
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id,HttpServletRequest request) throws NotFoundException {
        Blog blog = blogService.findBlogAndUpdateViews(id);
        if(blog == null){
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        //将博客内容的markdown格式转化为html格式
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        request.setAttribute("blog",b);
        return "blog";
    }

    @GetMapping("/footer/blogs")
    public String blogs(Model model){
        List<Blog> blogs = blogService.findthreeBlogs();
        model.addAttribute("blogs",blogs);
        return "_fragments :: newblogList";
    }

    @GetMapping("/admin/footer/blogs")
    public String adminBlogs(Model model){
        List<Blog> blogs = blogService.findthreeBlogs();
        model.addAttribute("blogs",blogs);
        return "admin/_fragments :: newAdminBlogList";
    }
}
