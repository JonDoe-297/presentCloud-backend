package com.yunbanke.daoyun.Web;


import com.yunbanke.daoyun.Service.DictionaryService;
import com.yunbanke.daoyun.Web.VO.RetResponse;
import com.yunbanke.daoyun.infrastructure.Persistence.DictionaryRepository;
import com.yunbanke.daoyun.infrastructure.entity.CheckinInfo;
import com.yunbanke.daoyun.infrastructure.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RequestMapping("/dictionary")
@RestController
@CrossOrigin
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping(path = "/add")
    public RetResponse add(String code,String name, String description){
        int res=dictionaryService.add(code,name,description);

        if(res==-1)
            return new RetResponse("2001", "添加字典类别失败，code已经存在。");
        return new RetResponse("200", "添加字典类别成功");
    }

    @PostMapping(path = "/del")
    public RetResponse del(int dic_id){
        int res=dictionaryService.del(dic_id);
        if(res==-1)
            return new RetResponse("2001", "删除字典类别失败，dic_id不存在。");
        return new RetResponse("200", "删除字典类别成功");
    }

    @PostMapping(path = "/edit")
    public RetResponse edit(int dic_id,String code,String name, String description){
        int res=dictionaryService.edit(dic_id, code, name,  description);

        if(res==-1)
            return new RetResponse("2001", "修改字典类别失败，dic_id不存在。");

        return new RetResponse("200", "修改字典类别成功");
    }

    @PostMapping(path = "/findAll")
    public RetResponse<List<Dictionary>> findAll(){
        List<Dictionary> list =dictionaryService.findAll();
        return new RetResponse("200", "获取成功",list);
    }

    @PostMapping(path = "/findById")
    public RetResponse<Dictionary> find(int dic_id){
        Dictionary dic =dictionaryService.find(dic_id);
        return new RetResponse("200", "获取成功",dic);
    }
}

