/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.product.dao;

import cn.itcast.product.common.FilesConstants;
import cn.itcast.product.entity.study.StudyProcess;
import com.baymax.api.dao.AbstractDao;
import com.baymax.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guNingbo
 * @since 2020/5/19
 */
@Component
public class StudyProcessDao extends AbstractDao<StudyProcess> {

    public List<StudyProcess> getStudyProcessList(int currentPage, int pageSize, String status) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StudyProcess> criteria = builder.createQuery(StudyProcess.class);
        Root<StudyProcess> root = criteria.from(StudyProcess.class);
        criteria.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(status)) {
            predicates.add(builder.equal(root.get(FilesConstants.STATUS), status));
        }
        if (!CollectionUtils.isEmpty(predicates)) {
            criteria.where(predicates.toArray(new Predicate[0]));
        }
        criteria.orderBy(builder.desc(root.get(FilesConstants.LAST_UPDATE_DATE)));
        return getEntityManager().createQuery(criteria).setMaxResults(currentPage).setMaxResults(pageSize).getResultList();
    }
}
