package jpa.service.base;

public interface BaseService {
	/**
     * 保存实体
     */
	void save(Object entity);
	 /**
     * 根据主键获取对象
     */
	<T> T getById(Class<T> clazz,Object id);
}
