package com.yunbanke.daoyun.Service;

import com.yunbanke.daoyun.infrastructure.Persistence.CheckinInfoRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.CheckinRepository;
import com.yunbanke.daoyun.infrastructure.Persistence.UserRepository;
import com.yunbanke.daoyun.Web.VO.CheckinResultVO;
import com.yunbanke.daoyun.Web.VO.RetResponse;
import com.yunbanke.daoyun.infrastructure.entity.Checkin;
import com.yunbanke.daoyun.infrastructure.entity.CheckinInfo;
import com.yunbanke.daoyun.infrastructure.entity.Class;
import com.yunbanke.daoyun.infrastructure.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CheckinService {
    @Autowired
    private CheckinRepository checkinRepository;
    @Autowired
    private CheckinInfoRepository checkinInfoRepository;
    @Autowired
    private ClassService classService;
    @Autowired
    private UserRepository userRepository;


    // 创建签到
    public CheckinInfo addCheckinInfo(String classNum, Date startTime, Date endTime, String code, int isvalid){
        CheckinInfo checkinInfo = new CheckinInfo();
        checkinInfo.setClassnum(classNum);
        checkinInfo.setCode(code);
        checkinInfo.setStarttime(startTime);
        checkinInfo.setEndtime(endTime);
        checkinInfo.setIsvalid(isvalid);
        CheckinInfo saved_checkInInfo = checkinInfoRepository.save(checkinInfo);
        System.out.println(saved_checkInInfo);
        return saved_checkInInfo;
    }


    // 设置签到信息有效值
    public int setCheckinInfoValid(int isvalid, int id){
        List<CheckinInfo> list =  checkinInfoRepository.getCheckinInfoByCheckininfoid(id);
        if(list.size() == 0){
            return 0;
        }
        CheckinInfo checkinInfo = list.get(0);
        checkinInfo.setIsvalid(isvalid);
        checkinInfoRepository.save(checkinInfo);
        return 1;
    }

    // 获取签到信息
    public List<CheckinInfo> getCheckinInfoList(String classNum){
        return checkinInfoRepository.getCheckInInfosByClassnum(classNum);

    }

    // 创建签到记录
    public RetResponse createStuCheckin(Integer checkinInfoId, String classNum){
        Class c = new Class();
        RetResponse classRet = classService.getClassByClassNum(classNum);
        if(classRet.getCode().equals("200")){
            c = (Class)classRet.getData();
        } else {
            return classRet;
        }
        List<User> stuList = c.getStudentList();
        for(int i = 0; i < stuList.size(); i++){
            Checkin checkin = new Checkin();
            checkin.setCheckinis(0);
            checkin.setClassnum(classNum);
            checkin.setUserid(stuList.get(i).getUserid());
            checkin.setCheckininfoid(checkinInfoId);
            checkinRepository.save(checkin);
        }
        return new RetResponse();
    }

    // 学生签到
    public List<Checkin> checkin(String classnum, Integer checkininfoid, Integer userid){
        Checkin checkin = checkinRepository.getCheckinByClassnumAndCheckininfoidAndUserid(classnum, checkininfoid,  userid);
        checkin.setCheckinis(1);
        checkin.setCheckindate(new Date());
        List<Checkin> list =  new ArrayList<Checkin>();
        list.add(checkinRepository.save(checkin));
        return list;
    }

    // 获得班级签到结果
    public List<CheckinResultVO> getCheckinResult(String classNum, Integer checkininfoid){
        List<CheckinResultVO> checkinResultVOS = new ArrayList<>();
        List<Checkin> checkinList = checkinRepository.getCheckinsByClassnumAndCheckininfoid(classNum, checkininfoid);
        for(int i = 0; i < checkinList.size(); i++){
            Checkin checkin = checkinList.get(i);
            CheckinResultVO checkinResultVO = new CheckinResultVO();
            User user = userRepository.getUsersByUserid(checkin.getUserid()).get(0);
            checkinResultVO.setName(user.getUsername());
            checkinResultVO.setChenkinIs(checkin.getCheckinis());
            if(checkin.getCheckinis() == 1){
                checkinResultVO.setCheckinDate(checkin.getCheckindate());
            }
            checkinResultVO.setClassNum(checkin.getClassnum());
            checkinResultVOS.add(checkinResultVO);
        }
        return checkinResultVOS;
    }
}
