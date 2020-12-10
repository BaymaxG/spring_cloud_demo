/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.service.impl;

import cn.itcast.product.common.StudyProcessStatusEnum;
import cn.itcast.product.dao.StudyProcessDao;
import cn.itcast.product.dto.StudyProcessDto;
import cn.itcast.product.entity.study.StudyProcess;
import cn.itcast.product.service.IStudyProcessService;
import com.baymax.api.dto.ResultMsg;
import com.baymax.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author guNingbo
 * @since 2020/5/19
 */
@Service
public class StudyProcessService implements IStudyProcessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudyProcessService.class);

    @Autowired
    private StudyProcessDao studyProcessDao;

    @Override
    public ResultMsg queryStudyProcess(int currentPage, int pageSize, String status) {
        List<StudyProcess> studyProcessList = studyProcessDao.getStudyProcessList(currentPage == 0 ? 1 : currentPage,
                pageSize == 0 ? 15 : pageSize, status);
        return ResultMsg.buildSuccess(studyProcessList);
    }

    @Override
    @Transactional
    public ResultMsg saveOrUpdateProcess(StudyProcessDto studyProcessDto) {
        StudyProcess studyProcess;
        if (StringUtil.isNullOrEmpty(studyProcessDto.getId())) {
            // 新增
            studyProcess = new StudyProcess();
            if (StringUtil.isNullOrEmpty(studyProcessDto.getContent()) && studyProcessDto.getCurrentPage() == 0) {
                return ResultMsg.buildFailed("请输入参考内容");
            }
            BeanUtils.copyProperties(studyProcessDto, studyProcess);
            // 计划时间默认往后推一天
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);
            studyProcess.setCompletedDate(calendar.getTime());
            studyProcess.setStatus(StudyProcessStatusEnum.START.getCode());
        } else {
            // 修改
            studyProcess = studyProcessDao.queryById(studyProcessDto.getId());
            if (studyProcess == null) {
                return ResultMsg.buildFailed("未查询到学习计划");
            }
            if (StringUtil.isNullOrEmpty(studyProcessDto.getStatus())) {
                studyProcess.setStatus(StudyProcessStatusEnum.STUDYING.getCode());
            }
            if (StudyProcessStatusEnum.COMPLETED.getCode().equals(studyProcessDto.getStatus())) {
                studyProcess.setStatus(studyProcessDto.getStatus());
                studyProcess.setCompletedDate(new Date());
            }
        }
        studyProcessDao.saveOrUpdate(studyProcess);
        return ResultMsg.buildSuccess();
    }
}
