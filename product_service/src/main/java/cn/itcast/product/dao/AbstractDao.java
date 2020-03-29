package cn.itcast.product.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * 公共抽象dao
 *
 * @param <T> 实体
 */
public abstract class AbstractDao<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void insertObject(T obj) {
        EntityManager manager = getEntityManager();
        manager.persist(obj);
        manager.flush();
    }

    public T saveOrUpdate(T object) {
        EntityManager manager = getEntityManager();
        T obj = manager.merge(object);
        manager.flush();
        return obj;
    }

    public void remove(T object) {
        EntityManager manager = getEntityManager();
        manager.remove(object);
        manager.flush();
    }

    public T queryById(Long id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return getEntityManager().find(tClass, id);
    }

    EntityManager getEntityManager() {
        try {
            DefaultTransactionStatus defaultTransactionStatus = (DefaultTransactionStatus) TransactionAspectSupport.currentTransactionStatus();
            JdbcTransactionObjectSupport transaction = (JdbcTransactionObjectSupport) defaultTransactionStatus.getTransaction();
            Field field = transaction.getClass().getDeclaredField("entityManager");
            field.setAccessible(true);
            EntityManager manager = (EntityManager) field.get(transaction);
            manager.setFlushMode(FlushModeType.COMMIT);
            return manager;
        } catch (Exception e) {
            LOGGER.error("getEntityManager failed: No transaction aspect-managed TransactionStatus in scope!");
        }
        entityManager.setFlushMode(FlushModeType.COMMIT);
        return entityManager;
    }
}
