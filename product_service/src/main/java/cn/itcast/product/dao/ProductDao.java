package cn.itcast.product.dao;

import cn.itcast.product.entity.Product;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

//        List<Predicate> predicates = new ArrayList<>();
//        criteria.where(predicates.toArray(new Predicate[predicates.size()]));
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
