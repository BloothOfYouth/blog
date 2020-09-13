package com.hjf.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hjf
 */
@Controller
public class FileController {

    /**上传地址*/
    @Value("${file.upload.path}")
    private String filePath;

    @PostMapping("/file/upload")
    @ResponseBody
    public Map<String,Object> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        // 获取上传文件名
        String filename = file.getOriginalFilename();
        // 定义上传文件保存路径
        String path = filePath+"rotPhoto/";
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            byte[] bytes = file.getBytes();
            Path paths = Paths.get(path + File.separator + filename);
            Files.write(paths, bytes);
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url","/image/rotPhoto/"+filename);
        } catch (IOException e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        return resultMap;
    }
}
