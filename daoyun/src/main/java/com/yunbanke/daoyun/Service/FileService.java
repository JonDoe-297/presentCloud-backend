package com.yunbanke.daoyun.Service;

import com.yunbanke.daoyun.Web.FileController;
import com.yunbanke.daoyun.Web.VO.RetResponse;
import com.yunbanke.daoyun.infrastructure.Persistence.FilepathRepository;
import com.yunbanke.daoyun.infrastructure.entity.FilePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FilepathRepository filepathRepository;

    @Value("${file.basePath}")
    String basePath;

    Logger logger = LoggerFactory.getLogger(FileController.class);

    public RetResponse uploadFile(MultipartFile file, Integer userId){
        try {
            if (file.isEmpty()) {
                logger.error("the file is empty");
                return new RetResponse("20004", "文件为空");
            }
            String fileName = file.getOriginalFilename();
            String suffixname = fileName.substring(fileName.lastIndexOf("."));
            logger.info("user: " + userId + " uploaded file: " + fileName + " and suffixname: " + suffixname);
            String filePath = basePath + '/' + userId + '/' + fileName;
            logger.info("filePath: " + filePath);
            File dest = new File(filePath);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            file.transferTo(dest);
            FilePath filePathObject = new FilePath();
            filePathObject.setFilepath(filePath);
            filePathObject.setUserid(userId);
            filepathRepository.save(filePathObject);
            return new RetResponse("200", "upload success.");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RetResponse("50005", "upload failed.");
    }

    public RetResponse getFilesByUserId(Integer userId){
        List<FilePath> filePaths = new ArrayList<>();
        filePaths = filepathRepository.getFilePathsByUserid(userId);
        if(filePaths.isEmpty()){
            return new RetResponse("20007", "未找到该用户文件记录。");
        } else {
            return new RetResponse("200", "success.", filePaths);
        }
    }

    public RetResponse getFileByFilepathId(HttpServletResponse response, Integer filePathId){
        List<FilePath> filePaths = filepathRepository.getFilePathByFilepathid(filePathId);
        if(filePaths.isEmpty()){
            return new RetResponse("20008", "no such file.");
        } else {

            String filePath = filePaths.get(0).getFilepath();
            return new RetResponse("200", "success.", filePath);
        }
    }

    public RetResponse deleteFileByFilepathId(Integer filePathId){
        List<FilePath> filePaths = filepathRepository.getFilePathByFilepathid(filePathId);
        if(filePaths.isEmpty()){
            return new RetResponse("20007", "no such file.");
        } else {
            FilePath filePath = filePaths.get(0);
            String path = filePath.getFilepath();
            String fileName = path.substring(path.lastIndexOf("/")).substring(1);
            File file = new File(path);
            file.delete();
            filepathRepository.delete(filePath);
            return new RetResponse("200", "delete success.", fileName);
        }
    }
}
