package com.hjf.blog.controller.admin;

import com.hjf.blog.entity.User;
import com.hjf.blog.service.IUserService;
import com.hjf.blog.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author hjf
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 跳转到用户登录页面
     * @return
     */
    @GetMapping
    public String toLoginPage(){
        return "admin/login";
    }

    /**
     * 跳转到index页面
     * @return
     */
    @GetMapping("/index")
    public String indexPage(){
        return "admin/index";
    }

    /**
     * 登录处理
     * @param username
     * @param password
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes){
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            redirectAttributes.addFlashAttribute("message","账号或密码为空");
            return "redirect:/admin";
        }

        //获取当前角色
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据的令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Utils.code(password));//使用md5加密

        try {
            subject.login(token);
            logger.info("登陆成功");
            User user = (User) subject.getPrincipal();//获取当前登录成功user对象
            user.setPassword(null);//保护账户，不让密码泄露
            session.setAttribute("user",user);
            return "redirect:/admin/index";
        } catch (UnknownAccountException uae) {
            //重定向页面用redirectAttributes.addFlashAttribute("message","用户名或密码错误"); 发送数据
            redirectAttributes.addFlashAttribute("message","用户名错误");
            return "redirect:/admin";
        } catch (IncorrectCredentialsException ice){
            redirectAttributes.addFlashAttribute("message","密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销处理
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }


}
