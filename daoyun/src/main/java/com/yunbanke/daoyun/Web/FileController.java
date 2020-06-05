package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.Service.FileService;
import com.yunbanke.daoyun.Web.VO.RetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileService;

    @Value("${file.basePath}")
    String basePath;
    Logger logger = LoggerFactory.getLogger(FileController.class);
    @RequestMapping("/upload")
    public RetResponse uploadFile(@RequestParam("file")MultipartFile file, @RequestParam Integer userId){
       return fileService.uploadFile(file, userId);
    }


    @RequestMapping("/getFilesByUserId")
    public RetResponse getFilesByUserId(@RequestParam Integer userId){
        return fileService.getFilesByUserId(userId);
    }

    @RequestMapping("/downloadByFilepathId")
    public String getFileByFilepathId(HttpServletResponse response, @RequestParam Integer FilepathId){
        RetResponse retResponse = fileService.getFileByFilepathId(response, FilepathId);
        String filePath = (String)retResponse.getData();
        File file = new File(filePath);
        String fileName = filePath.substring(filePath.lastIndexOf("/")).substring(1);
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
            response.addHeader("Content-Length", "" +file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "文件下载出错";
        }
    }

    @RequestMapping("/deleteFileByFilepathId")
    public RetResponse deleteFileByFilepathId(@RequestParam Integer filePathId){
        return fileService.deleteFileByFilepathId(filePathId);
    }

    @RequestMapping("/test")
    public void test(){
        logger.info("basePath: " + basePath + '\n');
    }
}
