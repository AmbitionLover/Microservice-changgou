package com.changgou.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件信息类
 * @author Ambi
 * @version 1.0
 * @date 2020/3/13 14:53
 */
@Data
public class FastDFSFile implements Serializable {
    //文件名字
    private String name;
    //文件内容
    private byte[] content;
    //文件扩展名
    private String ext;
    //文件MD5摘要值
    private String md5;
    //文件创建作者
    private String author;
    //文件密钥
    private String rbs;

    public FastDFSFile(String name, byte[] content, String ext, String md5, String author) {
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
    }

    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

    public FastDFSFile() {
    }
}
