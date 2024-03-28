package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api")
public class ReceiveCommandController {
    @Value("${python.explainer.path}")
    private String pythonExpalinerPath;

    @Value("${python.script.path")
    private String pythonScriptPath;
    @PostMapping("/process")
    public String processFile(@RequestParam String fileName) {
        System.out.println("file " + fileName + "processed ~ ");
        // 在这里执行分词指令
        // 这里仅做演示，实际应用中需要根据你的需求调用相应的服务或工具进行文件处理
        // 假设你有一个名为 FileProcessor 的服务类用于处理文件
        // 你可以注入 FileProcessor，并调用它的方法来处理文件
        // 示例中直接返回处理结果字符串
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(pythonExpalinerPath, pythonScriptPath);
            processBuilder.directory(new File("path/to/your/python/script"));
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
//        return "文件 " + fileName + " 已处理";
    }
}
