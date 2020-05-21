/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.entity.listener;

import cn.itcast.product.entity.study.StudyProcess;

import javax.persistence.PostLoad;

/**
 * @author guNingbo
 * @since 2020/4/5
 */
public class StudyProcessListener {
    @PostLoad
    public void loadItem(StudyProcess studyProcess) {
        if (studyProcess != null) {
        }

    }
}
