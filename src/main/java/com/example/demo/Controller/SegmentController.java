package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api")
public class SegmentController {

    @Value("${python.explainer.path}")
    private String pythonExpalinerPath;

    @Value("${python.script.path")
    private String pythonScriptPath;
    @PostMapping("/processFile")
    public String processFile(@RequestParam("filePath") String filePath) {

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(pythonExpalinerPath, pythonScriptPath);
            processBuilder.directory(new File("C:\\Users\\silence\\Desktop"));
            Process process = processBuilder.start();

            // 从Python脚本的输出流读取数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 等待Python脚本执行结束
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println(output.toString());
                return output.toString(); // 返回Python处理后的字符串
            } else {
                System.out.println("wrooooooooooong!");
                throw new RuntimeException("Python脚本执行失败");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("执行Python脚本时出现错误");
        }
    }
}
