package com.yunbanke.daoyun.Web;


import com.yunbanke.daoyun.infrastructure.Persistence.DictionaryRepository;
import com.yunbanke.daoyun.infrastructure.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dictionary")
@RestController
@CrossOrigin
public class DictionaryController {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @PostMapping(path = "/add")
    public void add(String code,String name, String description){
        Dictionary dic = new Dictionary();
        dic.setDicCode(code);
        dic.setDicName(name);
        dic.setDicDiscription(description);
        dictionaryRepository.save(dic);
    }

    @GetMapping(path = "/del")
    public void del(int dic_id){
        dictionaryRepository.deleteById(dic_id);
    }

    @GetMapping(path = "/edit")
    public void edit(int dic_id,String code,String name, String description){
        Dictionary dic = new Dictionary();
        dic.setDicId(dic_id);
        dic.setDicCode(code);
        dic.setDicName(name);
        dic.setDicDiscription(description);

        dictionaryRepository.save(dic);//存在id为更新，而不是新增
    }

    @GetMapping(path = "/show")
    public String showAll(){
        Iterable<Dictionary> itr_dic =dictionaryRepository.findAll();


        return itr_dic.toString();
    }
}

