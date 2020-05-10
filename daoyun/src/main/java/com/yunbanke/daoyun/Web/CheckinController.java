package com.yunbanke.daoyun.Web;

import com.yunbanke.daoyun.Service.CheckinService;
import com.yunbanke.daoyun.infrastructure.Types.CheckinResult;
import com.yunbanke.daoyun.infrastructure.entity.Checkin;
import com.yunbanke.daoyun.infrastructure.entity.CheckinInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    Logger logger = LoggerFactory.getLogger(CheckinController.class);


    // TODO 签到课程、时间、签到记录、成员签到列表、签到结果
    @PostMapping("/addCheckinInfo")
    public int addCheckinInfo(@RequestParam String classNum, @RequestParam Date startTime, @RequestParam Date endTime, @RequestParam String code){
        int isvalid = 0;
        Date nowtime = new Date();
        List<CheckinInfo> checkinInfos = checkinService.getCheckinInfoList(classNum);
        for(int i = 0; i < checkinInfos.size(); i++){
            if(checkinInfos.get(i).getIsvalid() == 1){
                logger.error("已经有在进行的签到任务。");
                return 9998; // 已经有在进行的签到任务。
            }
        }
        if(startTime == null || endTime == null || startTime.after(endTime) || nowtime.after(endTime)){
            logger.error("设定时间不正确。");
            return 9999;
        }
        CheckinInfo checkinInfo = checkinService.addCheckinInfo(classNum, startTime, endTime, code, 0);
        checkinService.createStuCheckin(checkinInfo.getCheckininfoid(), classNum);
        // 设定签到失效计时
        if(nowtime.before(checkinInfo.getEndtime())){
            logger.info("设定签到（" + checkinInfo.getCheckininfoid() + "）的失效时间为:" + checkinInfo.getEndtime() + "，开始进行。");
            TimerTask taskEnd = new TimerTask() {
                @Override
                public void run() {
                    logger.info("签到（" + checkinInfo.getCheckininfoid() + "）到期，设定签到有效值为 2，签到完成。");
                    checkinService.setCheckinInfoValid(2, checkinInfo.getCheckininfoid());
                }
            };
            Timer timerEnd = new Timer(true);
            timerEnd.schedule(taskEnd, checkinInfo.getEndtime());
            // 设定签到开始时间
            if(nowtime.before(checkinInfo.getStarttime())){

                logger.info("签到（" + checkinInfo.getCheckininfoid() + "）未到起始时间：" + checkinInfo.getStarttime() + "，未进行。");
                TimerTask taskStart = new TimerTask() {
                    @Override
                    public void run() {

                        logger.info("设定签到（" + checkinInfo.getCheckininfoid() + "）的有效值为 1，开始进行。");
                        checkinService.setCheckinInfoValid(1, checkinInfo.getCheckininfoid());
                    }
                };
                Timer timerStart = new Timer(true);
                timerStart.schedule(taskStart, checkinInfo.getStarttime());
            } else {
                logger.info("设定签到（" + checkinInfo.getCheckininfoid() + "）的有效值为 1，开始进行。");
                checkinService.setCheckinInfoValid(1, checkinInfo.getCheckininfoid());
            }
        } else {
            logger.info("设定签到（" + checkinInfo.getCheckininfoid() + "）已过期" + checkinInfo.getEndtime() + "，设定签到有效值为 0。");
        }
        return 1;
    }

    // TODO 签到
    @GetMapping("/checkin")
    public int checkin(@RequestParam String classNum, @RequestParam Integer userId, @RequestParam String code){
        List<CheckinInfo> checkinInfos = checkinService.getCheckinInfoList(classNum);
        for(int i = checkinInfos.size() - 1; i > 0; i--){
            CheckinInfo checkinInfo = checkinInfos.get(i);
            if(checkinInfo.getIsvalid() == 1){
                if(checkinInfo.getCode() == null || checkinInfo.getCode().equals(code)){
                    List<Checkin> list = checkinService.checkin(classNum, checkinInfo.getCheckininfoid(), userId);
                    if(list.size() > 0){
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -2; //code wrong.
                }
            }
        }
        return 0;
    }

    // 获取签到信息列表
    @GetMapping("/getCheckinInfoList")
    public List<CheckinInfo> getCheckinInfoList(@RequestParam String classNum){
        return checkinService.getCheckinInfoList(classNum);
    }


    // 老师获得签到结果
    @GetMapping("/getCheckinResult")
    public List<CheckinResult> getCheckinResult(@RequestParam String classNum, @RequestParam Integer checkinInfoId){
        return checkinService.getCheckinResult(classNum, checkinInfoId);
    }
}
