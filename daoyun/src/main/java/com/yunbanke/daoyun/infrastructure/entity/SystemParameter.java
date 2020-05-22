package com.yunbanke.daoyun.infrastructure.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemParameter {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer sysId;
  private String sysName;
  private String sysParameter;


  public Integer getSysId() {
    return sysId;
  }

  public void setSysId(Integer sysId) {
    this.sysId = sysId;
  }


  public String getSysName() {
    return sysName;
  }

  public void setSysName(String sysName) {
    this.sysName = sysName;
  }


  public String getSysParameter() {
    return sysParameter;
  }

  public void setSysParameter(String sysParameter) {
    this.sysParameter = sysParameter;
  }

}
