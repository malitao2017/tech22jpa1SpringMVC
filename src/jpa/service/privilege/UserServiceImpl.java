package jpa.service.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import jpa.dao.privilege.UserDao;

//实例化
@Service
public class UserServiceImpl implements UserService{
	//自动装配
	@Autowired
	private UserDao userDao;
	
//	保存操作加事务
	@Transactional(propagation=Propagation.REQUIRED)
	@TriggersRemove(cacheName="userCache",removeAll=true)//保存之前先清除缓存
	@Override
	public void save(Object entity) {
		userDao.save(entity);
	}

	@Cacheable(cacheName="userCache")//缓存数据
	@Override
	public <T> T getById(Class<T> clazz, Object id) {
		return userDao.getById(clazz, id);
	}

}
