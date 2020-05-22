package com.yunbanke.daoyun.Service;

import com.yunbanke.daoyun.Web.VO.CheckinResultVO;
import com.yunbanke.daoyun.Web.VO.RetResponse;
import com.yunbanke.daoyun.infrastructure.Persistence.*;
import com.yunbanke.daoyun.infrastructure.entity.*;
import com.yunbanke.daoyun.infrastructure.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private DictionaryDetailRepository dictionaryDetailRepository;

    //字典大类别增删改查
    // 新增字典大类别
    public int add(String code,String name, String description){
        if(dictionaryRepository.findDictionaryByDicCode(code)!=null)
            return -1;//code 字典类别 code存在
        Dictionary dic = new Dictionary();
        dic.setDicCode(code);
        dic.setDicName(name);
        dic.setDicDiscription(description);
        dictionaryRepository.save(dic);
        return 1;
    }

    //删除字典大类别 彻底删除
    public int del(int dic_id){
        if(dictionaryRepository.findDictionaryByDicId(dic_id)==null)
            return -1;//id不存在
        dictionaryRepository.deleteById(dic_id);
        dictionaryDetailRepository.deleteByDicTypeId(dic_id);//级联删除
        return 1;
    }

    //编辑字典大类别
    public int edit(int dic_id,String code,String name, String description){
        if(dictionaryRepository.findDictionaryByDicId(dic_id)==null)
            return -1;//id不存在
        Dictionary dic = new Dictionary();
        dic.setDicId(dic_id);
        dic.setDicCode(code);
        dic.setDicName(name);
        dic.setDicDiscription(description);

        dictionaryRepository.save(dic);//存在id为更新，而不是新增
        return 1;
    }

    //查询所有大类别
    public List<Dictionary> findAll(){
        List<Dictionary> list =dictionaryRepository.findAll();
        return list;
    }

    //按dic_id 查询大类别
    public Dictionary find(int dic_id){
        Dictionary dic =dictionaryRepository.findDictionaryByDicId(dic_id);
        return dic;
    }

    //////////////////////////////////////////////////////////
    //字典详情增删改查
    //增加
    public int detail_add(int typeid,String code, String name,int serialNumber){
        if(dictionaryRepository.findDictionaryByDicId(typeid)==null)
            return -1;//不存在此字典类别
        if(dictionaryDetailRepository.findByDicTypeIdAndDicDetailCode(typeid,code)!=null)
            return -2;//此类别下 code已经被使用
        DictionaryDetail dicd = new DictionaryDetail();
        dicd.setDicTypeId(typeid);
        dicd.setDicDetailCode(code);
        dicd.setDicDetailName(name);
        dicd.setDicDetailSerialNumber(serialNumber);
        dicd.setDicDetailState(1);
        dictionaryDetailRepository.save(dicd);
        return 1;
    }

    //删除 彻底删除
    public int detail_del(int dicd_id){
        if(dictionaryDetailRepository.findById(dicd_id)==null)
            return -1;//不存在
        dictionaryDetailRepository.deleteById(dicd_id);
        return 1;
    }

    //修改
    public int detail_edit(int dicd_id,int typeid,String code,String name, int serialNumber,int state){
        if (dictionaryDetailRepository.findByDicTypeId(dicd_id)==null)
            return -1;//不存在此字典详情
        if (dictionaryRepository.findDictionaryByDicId(typeid)==null)
            return -2;//不存在此字典类别
        DictionaryDetail dic = new DictionaryDetail();
        dic.setDicDetailId(dicd_id);
        dic.setDicTypeId(typeid);
        dic.setDicDetailCode(code);
        dic.setDicDetailName(name);
        dic.setDicDetailSerialNumber(serialNumber);
        dic.setDicDetailState(state);
        dictionaryDetailRepository.save(dic);//存在id为更新，而不是新增
        return 1;
    }

    //查询 按字典大类别
    public List<DictionaryDetail> detail_findAll(int typeid){
        List<DictionaryDetail> dicd =dictionaryDetailRepository.findByDicTypeId(typeid);
        return dicd;
    }
 }
