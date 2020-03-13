package com.changgou.file.controller;

import com.changgou.entity.FastDFSFile;
import com.changgou.util.FastDFSClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 * @author Ambi
 * @version 1.0
 * @date 2020/3/13 14:50
 */
@RestController
@CrossOrigin
public class FileController {

    @PostMapping("/upload")
    public Result<String> uoload(@RequestParam("file")MultipartFile file)throws Exception{
        //封装一个FastDFSFile
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(), //文件名字
                file.getBytes(),            //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名
        System.out.println(file.getOriginalFilename());
        //文件上传
        String[] uploads = FastDFSClient.upload(fastDFSFile);
        //组装文件上传地址
        String fileurl = FastDFSClient.getTrackerUrl()+"/"+uploads[0]+"/"+uploads[1];
        return new Result<String>(true, StatusCode.OK,"文件上传成功",fileurl);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam("groupName") String groupName, @RequestParam("remoteFileName")String remoteFileName) throws Exception {
        FastDFSClient.deleteFile(groupName,remoteFileName);
        return new Result(true,StatusCode.OK,"文件已删除");
    }


}
