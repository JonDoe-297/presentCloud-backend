package com.yunbanke.daoyun.Service;

import com.yunbanke.daoyun.infrastructure.Persistence.CheckinInfoRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.CheckinRepository;
import com.yunbanke.daoyun.infrastructure.entity.CheckinInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckinService {
    @Autowired
    private CheckinRepository checkinRepository;
    @Autowired
    private CheckinInfoRepository checkinInfoRepository;

    // 创建签到
    public int addCheckinInfo(String classNum, Date startTime, Date endTime, String code, int isvalid){
        CheckinInfo checkinInfo = new CheckinInfo();
        checkinInfo.setClassnum(classNum);
        checkinInfo.setCode(code);
        checkinInfo.setStarttime(startTime);
        checkinInfo.setEndtime(endTime);
        checkinInfo.setIsvalid(isvalid);
        CheckinInfo saved_checkInInfo = checkinInfoRepository.save(checkinInfo);
        System.out.println(saved_checkInInfo);
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                saved_checkInInfo.setIsvalid(0);
//                checkinInfoRepository.save(saved_checkInInfo);
//            }
//        };
//        Timer timer = new Timer(true);
//        timer.schedule(task, endTime);
        return 1;
    }

    // 获取签到信息
    public List<CheckinInfo> getCheckinInfo(String classNum){
        return checkinInfoRepository.getCheckInInfosByClassnum(classNum);

    }

    // 停止签到
    public void endCheckin(Date endTime){

    }
}
