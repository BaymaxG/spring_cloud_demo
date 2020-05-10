package cn.itcast.product.dao;

import cn.itcast.product.common.FilesConstants;
import cn.itcast.product.dto.ProductQueryDto;
import cn.itcast.product.entity.Product;
import com.baymax.api.dao.AbstractDao;
import com.baymax.utils.CollectionUtil;
import com.baymax.utils.StringUtil;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品dao
 */
@Component
public class ProductDao extends AbstractDao<Product> {
    public List<Product> findAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    public List<Product> findProducts(ProductQueryDto queryDto) {
        if (queryDto == null) {
            return new ArrayList<>();
        }
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(queryDto.getProductName())) {
            predicates.add(builder.equal(root.get(FilesConstants.PRODUCT_NAME), queryDto.getProductName()));
        }
        if (!StringUtil.isNullOrEmpty(queryDto.getCaption())) {
            predicates.add(builder.equal(root.get(FilesConstants.CAPTION), queryDto.getCaption()));
        }
        if (!CollectionUtil.isNullOrEmpty(queryDto.getStatus())) {
            CriteriaBuilder.In<Object> statusIn = builder.in(root.get(FilesConstants.STATUS));
            for (String status : queryDto.getStatus()) {
                statusIn.value(status);
            }
            predicates.add(statusIn);
        }
        criteria.where(predicates.toArray(new Predicate[predicates.size()]));
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
