package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.Service.CheckinService;
import com.yunbanke.daoyun.infrastructure.entity.CheckinInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/checkin")
@CrossOrigin
@RestController
public class CheckinController {
    @Autowired
    private CheckinService checkinService;

    @GetMapping("/getTime")
    public void getTime(){
        System.out.println(new Date());
    }


    // TODO 签到课程、时间、签到记录、成员签到列表、签到结果
    @PostMapping("/addCheckinInfo")
    public int addCheckinInfo(@RequestParam String classNum, @RequestParam Date startTime, @RequestParam Date endTime, @RequestParam String code){
        int isvalid = 0;
        Date nowtime = new Date();
        List<CheckinInfo> checkinInfos = checkinService.getCheckinInfo(classNum);
        for(int i = 0; i < checkinInfos.size(); i++){
            if(checkinInfos.get(i).getIsvalid() == 1){
                return 9998; // 已经有在进行的签到任务。
            }
        }
        if(startTime == null || endTime == null){
            return 9999;
        }
        if(nowtime.getTime() > startTime.getTime() && nowtime.getTime() < endTime.getTime()){
            isvalid = 1;
        }
        return checkinService.addCheckinInfo(classNum, startTime, endTime, code, isvalid);
    }

    // TODO 签到
//    @GetMapping("/getCheckinInfo")
//    public CheckinInfo checkin(@RequestParam String classNum){
//        List<CheckinInfo> checkinInfos = checkinService.getCheckinInfo(classNum);
//        if(checkinInfos.size() == 1){
//            return checkinInfos.get(0);
//        }
//        else {
//
//        }
//    }

}
