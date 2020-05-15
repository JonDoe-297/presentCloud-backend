package com.yunbanke.daoyun.Web;


import com.yunbanke.daoyun.infrastructure.Persistence.DictionaryDetailRepository;
import com.yunbanke.daoyun.infrastructure.entity.DictionaryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dictionaryDetail")
@RestController
@CrossOrigin
public class DictionaryDetailController {

    @Autowired
    private DictionaryDetailRepository dictionaryDetailRepository;

    @PostMapping(path = "/add")
    public void add(int typeid,String code, String name,int serialNumber){
        DictionaryDetail dicd = new DictionaryDetail();
        dicd.setDicTypeId(typeid);
        dicd.setDicDetailCode(code);
        dicd.setDicDetailName(name);
        dicd.setDicDetailSerialNumber(serialNumber);
        dictionaryDetailRepository.save(dicd);
    }

    @GetMapping(path = "/del")
    public void del(int dicd_id){
        dictionaryDetailRepository.deleteById(dicd_id);
    }

    @GetMapping(path = "/edit")
    public void edit(int dicd_id,int typeid,String code,String name, int serialNumber,int state){
        DictionaryDetail dic = new DictionaryDetail();
        dic.setDicDetailId(dicd_id);
        dic.setDicTypeId(typeid);///????typid获取
        dic.setDicDetailCode(code);
        dic.setDicDetailName(name);
        dic.setDicDetailSerialNumber(serialNumber);
        dic.setDicDetailState(state);
        dictionaryDetailRepository.save(dic);//存在id为更新，而不是新增
    }

    @GetMapping(path = "/show")
    public String showAll(int typeid){
        List<DictionaryDetail> itr_dicd =dictionaryDetailRepository.findByDicTypeId(typeid);
        ////按typeid寻找
        return itr_dicd.get(0).toString();////?????分页
    }
}

