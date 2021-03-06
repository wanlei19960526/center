package com.xn.hk.common.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.xn.hk.common.dao.BaseDao;
import com.xn.hk.common.service.BaseService;
import com.xn.hk.common.utils.log.LogHelper;
import com.xn.hk.common.utils.page.BasePage;
import com.xn.hk.system.dao.AdminLogDao;

/**
 * 
 * @Title: BaseServiceImpl
 * @Package: com.xn.ad.common.service.impl
 * @Description: 业务接口实现基础类,所有业务接口实现类的父类
 * @Author: wanlei
 * @Date: 2017-11-28 下午03:50:38
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	@Autowired
	private AdminLogDao adminLogDao;

	/**
	 * 抽象方法(用于指定具体调用某个dao接口)
	 * 
	 * @return dao接口基础类,所有dao接口的父类
	 */
	public abstract BaseDao<T> getDao();

	/**
	 * 添加实体
	 * 
	 * @param session
	 *            session
	 * @param logName
	 *            日志操作名称
	 * @param logType
	 *            日志操作类型
	 * @param t
	 *            实体对象
	 * @return 返回影响条数
	 */
	public int insert(HttpSession session, String logName, Integer logType, T t) {
		boolean logResult = true;
		int result = getDao().insert(t);
		if (result == 0) {
			logResult = false;
		}
		LogHelper.getInstance().saveLog(adminLogDao, session, logName, logResult, logType, t);
		return 2;
	}

	/**
	 * 批量添加实体
	 * 
	 * @param session
	 *            session
	 * @param logName
	 *            日志操作名称
	 * @param logType
	 *            日志操作类型
	 * @param List<T>
	 *            实体对象列表
	 * @return 返回影响条数
	 */
	public int batchInsert(HttpSession session, String logName, Integer logType, List<T> list) {
		boolean logResult = true;
		if (list == null || list.size() == 0) {
			return -1;
		}
		// 循环调用插入方法，次数为list的size
		for (T t : list) {
			int result = getDao().insert(t);
			if (result == 0) {
				logResult = false;
			}
			LogHelper.getInstance().saveLog(adminLogDao, session, logName, logResult, logType, t);
		}
		return list.size() * 2;
	};

	/**
	 * 更新实体
	 * 
	 * @param session
	 *            session
	 * @param logName
	 *            日志操作名称
	 * @param logType
	 *            日志操作类型
	 * @param t
	 *            实体对象
	 * @return 返回影响条数
	 */
	public int update(HttpSession session, String logName, Integer logType, T t) {
		boolean logResult = true;
		int result = getDao().update(t);
		if (result == 0) {
			logResult = false;
		}
		LogHelper.getInstance().saveLog(adminLogDao, session, logName, logResult, logType, t);
		return 2;
	}

	/**
	 * 删除实体
	 * 
	 * @param session
	 *            session
	 * @param logName
	 *            日志操作名称
	 * @param logType
	 *            日志操作类型
	 * @param id
	 *            实体ID
	 * @return 返回影响条数
	 */
	public int delete(HttpSession session, String logName, Integer logType, Object id) {
		boolean logResult = true;
		T t = findById(id);
		int result = getDao().delete(id);
		if (result == 0) {
			logResult = false;
		}
		LogHelper.getInstance().saveLog(adminLogDao, session, logName, logResult, logType, t);
		return 2;
	};

	/**
	 * 批量删除实体
	 * 
	 * @param session
	 *            session
	 * @param logName
	 *            日志操作名称
	 * @param logType
	 *            日志操作类型
	 * @param ids
	 *            实体ID数组
	 * @return 返回影响条数
	 */
	public int batchDelete(HttpSession session, String logName, Integer logType, Object[] ids) {
		boolean logResult = true;
		if (ids == null || ids.length < 1) {
			return -1;
		}
		// 循环调用删除方法，次数为数组长度
		for (Object id : ids) {
			T t = findById(id);
			int result = getDao().delete(id);
			if (result == 0) {
				logResult = false;
			}
			LogHelper.getInstance().saveLog(adminLogDao, session, logName, logResult, logType, t);
		}
		return ids.length * 2;
	}

	/**
	 * 查询分页对象列表
	 * 
	 * @param page
	 *            分页对象
	 * @return 分页对象列表
	 */
	public List<T> pageList(BasePage<T> page) {
		int count = getDao().pageCount(page);
		// 将分页总记录数塞到分页对象中去
		page.setCount(count);
		return getDao().pageList(page);
	}

	/**
	 * 使用分页插件查询分页对象列表
	 * 
	 * @param page
	 *            分页对象
	 * @return 分页对象列表
	 */
	public List<T> pageAll(BasePage<T> page) {
		return getDao().pageAll(page);
	}

	/**
	 * 查询所有
	 * 
	 * @return 对象列表
	 */
	public List<T> findAll() {
		return getDao().findAll();
	};

	/**
	 * 根据实体ID查找该实体
	 * 
	 * @param id
	 *            实体ID
	 * @return 实体对象
	 */
	public T findById(Object id) {
		return getDao().findById(id);
	}

	/**
	 * 根据实体名查找该实体
	 * 
	 * @param name
	 *            实体名
	 * @return 实体对象
	 */
	public T findByName(Object name) {
		return getDao().findByName(name);
	}

	/**
	 * 根据自定义条件查找该实体
	 * 
	 * @param cond
	 *            自定义条件
	 * @return 实体对象
	 */
	public T findByCond(Object cond) {
		return getDao().findByCond(cond);
	};

	/**
	 * 根据自定义条件查找实体列表
	 * 
	 * @param cond
	 *            自定义条件
	 * @return 实体列表
	 */
	public List<T> findListByCond(Object cond) {
		return getDao().findListByCond(cond);
	};

	/**
	 * 根据自定义条件查找结果总数
	 * 
	 * @param cond
	 *            自定义条件
	 * @return 结果总数
	 */
	public int findCountByCond(Object cond) {
		return getDao().findCountByCond(cond);
	};
}
