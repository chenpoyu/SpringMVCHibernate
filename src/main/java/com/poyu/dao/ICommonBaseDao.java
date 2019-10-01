package com.poyu.dao;

import java.util.List;

/**
 * Description:	共用 Dao之Interface
 * @author poyu
 * @version 1.0
 * @created 2018.04.07
 */
public interface ICommonBaseDao<Model extends Object> {

	/**
	 * 刪除集合資料
	 * 1.呼叫getHibernateTemplate().deleteAll(Collection entities)
	 * 
	 * @param entities
	 */
	@SuppressWarnings("rawtypes")
	public void deleteCollection(List entities);

	/**
	 * 儲存集合資料
	 * 1.呼叫getHibernateTemplate().saveOrUpdateAll(Collection entities)
	 * 
	 * @param entities
	 */
//	public void saveCollection(List entities);

	/**
	 * 修改Model
	 * 1.呼叫getHibernateTemplate().update(Model entity)
	 * 
	 * @param entity
	 */
	public void updateModel(Model  entity);

	/**
	 * 新增Model
	 * 1.呼叫getHibernateTemplate().save(Model entity)
	 * 
	 * @param entity
	 */
	public void saveModel(Model entity);

	/**
	 * 新增或修改Model
	 * 1.呼叫getHibernateTemplate().saveOrUpdate(Model entity)
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(Model entity);
	
	/**
	 * 刪除Model
	 * 1.呼叫getHibernateTemplate().delete(Model entity)
	 * 
	 * @param entity
	 */
	public void deleteModel(Model entity);
	
	public Model getModel(Model entity, Integer id);
	
	public int truncateTable(String tableName);
	
	/**
	 * 取得 Table 內所有 Model
	 * @param entity
	 * @return Model List
	 */
	public List<Model> getAllModel(Model entity);

}