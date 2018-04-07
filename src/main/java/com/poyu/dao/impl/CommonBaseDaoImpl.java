package com.poyu.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.poyu.core.BaseDaoHibernate;
import com.poyu.dao.ICommonBaseDao;

/**
 * @author poyu
 * @version 1.0
 * @created 2018.04.07
 */
public class CommonBaseDaoImpl<Model> extends BaseDaoHibernate<Model> implements ICommonBaseDao<Model> {

	/**
	 * 刪除集合資料 1.呼叫getHibernateTemplate().deleteAll(Collection entities)
	 * 
	 * @param entities
	 */
	@Override
	public void deleteCollection(List entities) {
		this.getHibernateTemplate().deleteAll(entities);
		this.getHibernateTemplate().flush();
	}

	/**
	 * 儲存集合資料 1.呼叫getHibernateTemplate().saveOrUpdateAll(Collection entities)
	 * 
	 * @param entities
	 */
//	@Override
//	public void saveCollection(List entities) {
//		this.getHibernateTemplate().saveOrUpdateAll(entities);
//		this.getHibernateTemplate().flush();
//	}

	/**
	 * 修改Model 1.呼叫getHibernateTemplate().update(Model entity)
	 * 
	 * @param entity
	 */
	@Override
	public void updateModel(Model entity) {
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * 新增Model 1.呼叫getHibernateTemplate().save(Model entity)
	 * 
	 * @param entity
	 */
	@Override
	public void saveModel(Model entity) {
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * 新增或修改Model 1.呼叫getHibernateTemplate().saveOrUpdate(Model entity)
	 * 
	 * @param entity
	 */
	@Override
	public void saveOrUpdate(Model entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * 刪除Model 1.呼叫getHibernateTemplate().delete(Model entity)
	 * 
	 * @param entity
	 */
	@Override
	public void deleteModel(Model entity) {
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Model getModel(Model entity, Integer id) {
		return (Model) this.getHibernateTemplate().get(entity.getClass(), id);
	}

	@Override
	public int truncateTable(String tableName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" TRUNCATE TABLE ");
		sql.append(tableName);
		return updateBySQLWithQueryObject(sql.toString(), null);
	}

	@Override
	public List<Model> getAllModel(Model entity) {
		List<Model> result = null;

		Class modelClass = entity.getClass();
		DetachedCriteria criteria = DetachedCriteria.forClass(modelClass);
		result = (List<Model>) this.getHibernateTemplate().findByCriteria(criteria);

		return result;
	}
}
