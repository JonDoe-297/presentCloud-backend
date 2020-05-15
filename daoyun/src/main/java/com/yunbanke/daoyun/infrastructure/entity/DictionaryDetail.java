package com.yunbanke.daoyun.infrastructure.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DictionaryDetail {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long dicDetailId;

  private long dicTypeId;
  private String dicDetailCode;
  private String dicDetailName;
  private long dicDetailSerialNumber;
  private long dicDetailState;


  public long getDicDetailId() {
    return dicDetailId;
  }

  public void setDicDetailId(long dicDetailId) {
    this.dicDetailId = dicDetailId;
  }


  public long getDicTypeId() {
    return dicTypeId;
  }

  public void setDicTypeId(long dicTypeId) {
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


  public long getDicDetailSerialNumber() {
    return dicDetailSerialNumber;
  }

  public void setDicDetailSerialNumber(long dicDetailSerialNumber) {
    this.dicDetailSerialNumber = dicDetailSerialNumber;
  }


  public long getDicDetailState() {
    return dicDetailState;
  }

  public void setDicDetailState(long dicDetailState) {
    this.dicDetailState = dicDetailState;
  }

}
