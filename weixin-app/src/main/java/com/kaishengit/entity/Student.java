package com.kaishengit.entity;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/10 0010
 */
public class Student {

    private Integer id;
    private String userName;
    private String address;
    private Integer schoolId;

    private School school;
    private List<Tag> tagList;

    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }


    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", schoolId=" + schoolId +
                ", school=" + school +
                ", tagList=" + tagList +
                '}';
    }

}
