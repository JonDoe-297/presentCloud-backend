package com.yunbanke.daoyun.infrastructure.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DictionaryDetail {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Integer dicDetailId;

  private Integer dicTypeId;
  private String dicDetailCode;
  private String dicDetailName;
  private Integer dicDetailSerialNumber;
  private Integer dicDetailState;


  public Integer getDicDetailId() {
    return dicDetailId;
  }

  public void setDicDetailId(Integer dicDetailId) {
    this.dicDetailId = dicDetailId;
  }


  public Integer getDicTypeId() {
    return dicTypeId;
  }

  public void setDicTypeId(Integer dicTypeId) {
    this.dicTypeId = dicTypeId;
  }


  public String getDicDetailCode() {
    return dicDetailCode;
  }

  public void setDicDetailCode(String dicDetailCode) {
    this.dicDetailCode = dicDetailCode;
  }


  public String getDicDetailName() {
    return dicDetailName;
  }

  public void setDicDetailName(String dicDetailName) {
    this.dicDetailName = dicDetailName;
  }


  public Integer getDicDetailSerialNumber() {
    return dicDetailSerialNumber;
  }

  public void setDicDetailSerialNumber(Integer dicDetailSerialNumber) {
    this.dicDetailSerialNumber = dicDetailSerialNumber;
  }


  public Integer getDicDetailState() {
    return dicDetailState;
  }

  public void setDicDetailState(Integer dicDetailState) {
    this.dicDetailState = dicDetailState;
  }

}
