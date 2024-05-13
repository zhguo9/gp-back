package com.example.demo.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
public class ShowFileListController {

    @GetMapping("/files")
    public List<String> getFileList() {
        List<String> fileList = new ArrayList<>();
        File folder = new File("C:\\Guo\\Git\\gp-back\\src\\main\\resources\\Files"); // 请替换为你要读取的文件夹路径

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    fileList.add(file.getName());
                }
            }
        }
        return fileList;
    }
}
