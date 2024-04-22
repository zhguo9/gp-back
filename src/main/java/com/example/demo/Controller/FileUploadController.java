package com.example.demo.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api")
public class FileUploadController {

    private static final String UPLOAD_DIR = "C:\\Users\\silence\\Desktop\\Files"; // 修改为你想要保存文件的路径

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "请选择文件";
        }
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 文件保存路径
            String filePath = UPLOAD_DIR + File.separator + fileName;
            // 保存文件
            file.transferTo(new File(filePath));
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }
}
