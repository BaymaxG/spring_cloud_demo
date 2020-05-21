/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.service;

import cn.itcast.product.dto.StudyProcessDto;
import com.baymax.api.dto.ResultMsg;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guNingbo
 * @since 2020/5/19
 */
@RestController
@RequestMapping("/study")
public interface IStudyProcessService {
    /**
     * 查看当前学习进度
     *
     * @return 进度表
     */
    @RequestMapping(value = "/study-process-list/{currentPage}/{pageSize}", method = RequestMethod.GET)
    ResultMsg queryStudyProcess(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize,
                                @RequestParam("status") String status);

    /**
     * 新增或修改学习计划
     *
     * @param studyProcessDto 学习计划入参
     * @return 新增/修改结果
     */
    @RequestMapping(value = "/study-process", method = RequestMethod.POST)
    ResultMsg saveOrUpdateProcess(@RequestBody StudyProcessDto studyProcessDto);
}
