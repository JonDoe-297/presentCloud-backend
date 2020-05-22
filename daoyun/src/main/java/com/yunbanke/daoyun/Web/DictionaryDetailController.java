package com.yunbanke.daoyun.Web;


import com.yunbanke.daoyun.Service.DictionaryService;
import com.yunbanke.daoyun.Web.VO.RetResponse;
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
    private DictionaryService dictionaryService;

    @PostMapping(path = "/add")
    public RetResponse add(int typeid,String code, String name,int serialNumber){
        int res=dictionaryService.detail_add(typeid,code,name,serialNumber);
        if(res==-1)
            return new RetResponse("2001", "添加字典详情失败，不存在此字典类别。");
        if(res==-2)
            return new RetResponse("2002", "添加字典详情失败，在此字典类别中，code已经存在。");
        return new RetResponse("200", "添加字典详情成功");
    }

    @PostMapping(path = "/del")
    public RetResponse del(int dicd_id){
        int res=dictionaryService.detail_del(dicd_id);
         if(res==-1)
            return new RetResponse("2001", "删除字典详情失败，dicd_id不存在。");
        return new RetResponse("200", "删除字典详情成功");
    }

    @PostMapping(path = "/edit")
    public RetResponse edit(int dicd_id,int typeid,String code,String name, int serialNumber,int state){
        int res=dictionaryService.detail_edit(dicd_id, typeid, code, name,  serialNumber, state);
        if(res==-1)
            return new RetResponse("2001", "编辑字典详情失败，dicd_id不存在。");
        if(res==-2)
            return new RetResponse("2002", "编辑字典详情失败，字典类别id不存在。");
        return new RetResponse("200", "编辑字典详情成功");
    }

    @PostMapping(path = "/findAllByTypeid")
    public RetResponse<List<DictionaryDetail>> findAll(int typeid){

        List<DictionaryDetail> list=dictionaryService.detail_findAll(typeid);
        if(list.size()==0)
            return new RetResponse("2001", "获取字典详情失败,未找到此类别字典");
        return new RetResponse("200", "获取字典详情成功",list);
    }
}

