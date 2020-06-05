package com.yunbanke.daoyun.infrastructure.entity;

import javax.persistence.*;

@Entity
@Table(name = "filepath")
public class FilePath {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "filepath_id")
    private Integer filepathid;

    @Column(name = "user_id")
    private Integer userid;

    @Column(name = "file_path")
    private String filepath;

    @Override
    public String toString() {
        return "FilePath{" +
                "filepathid=" + filepathid +
                ", userid=" + userid +
                ", filepath='" + filepath + '\'' +
                '}';
    }

    public Integer getFilepathid() {
        return filepathid;
    }

    public void setFilepathid(Integer filepathid) {
        this.filepathid = filepathid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
