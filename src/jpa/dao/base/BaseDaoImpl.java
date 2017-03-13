package jpa.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
/**
 * 通用DAO实现
 * @author Luxh
 */
@Repository
public class BaseDaoImpl implements BaseDao{
	//注入实体管理器
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void save(Object entity) {
		em.persist(entity);
	}

	@Override
	public <T> T getById(Class<T> clazz, Object id) {
		return em.find(clazz, id);
	}

	
}
