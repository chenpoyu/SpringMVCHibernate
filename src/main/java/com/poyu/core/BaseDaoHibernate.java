package com.poyu.core;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * @author poyu
 * @version 1.0
 * @created 2018/04/07
 */
public class BaseDaoHibernate<Model extends Object> extends HibernateDaoSupport{
	
	/**
	 * 不用ehcache
	 * @param sessionFactory
	 */
	@Resource(name="sessionFactory") //為父類HibernateDaoSupport注入sessionFactory的值
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	/**
	 * 根據HQL條件與查詢條件取得資料
	 * @param hql
	 * @param argsMap
	 * @return
	 */
	protected List<Model> findByHQLWithQueryObject(final String hql, final Map argsMap){
		return this.findByHQLWithQueryObject(hql, argsMap, null, null);
	}
	
	/**
	 * 根據HQL條件與查詢條件headIndex, maxCount取得資料，前端分頁顯示用
	 * 備註：headIndex, maxCount只負責記錄分頁資訊，參數值都由argsMap取得
	 * @param hql
	 * @param argsMap
	 * @param headIndex
	 * @param maxCount
	 * @return
	 */
	protected List<Model> findByHQLWithQueryObject(final String hql, final Map argsMap, final String headIndex, final String maxCount){
		return (List<Model>) getHibernateTemplate().execute(
				new HibernateCallback<List<Model>>() {
					@SuppressWarnings("unchecked")
					public List<Model> doInHibernate(Session session) {
						Query query = session.createQuery(hql);
						
						if(MapUtils.isNotEmpty(argsMap)){
							query.setProperties(argsMap);
						}/*end of if*/
						
						if(null != headIndex){
							query.setFirstResult(Integer.parseInt(headIndex));
							query.setMaxResults(Integer.parseInt(maxCount));
						}/*end of if*/
						
						return query.list();
					}
			});
	}
	
	/**
	 * 根據HQL條件與argsMap參數計算分頁資訊。
	 * 該HQL為SELECT COUNT(*)開頭，因此只包含Where之條件
	 * 
	 * @param hql
	 * @param argsMap
	 * @return
	 */
	protected long countByHQLWithQueryObject(final String hql, final Map argsMap){
		@SuppressWarnings("unchecked")
		List<Model> list =  (List<Model>) this.getHibernateTemplate().execute(new HibernateCallback<List<Model>>(){
			public List<Model> doInHibernate(Session session){
				Query query = session.createQuery("  SELECT COUNT(*)" + hql);				
				if(MapUtils.isNotEmpty(argsMap)){
					query.setProperties(argsMap);
				}/*end of if*/
				
				return query.list();
			}
		});
		return Integer.valueOf(list.get(0).toString());
	}
	
	/**
	 * 根據HQL條件與argsMap參數執行update語法, 亦可用於delete
	 * 
	 * @param hql
	 * @param argsMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected int updateByHQLWithQueryObject(final String hql, final Map argsMap){
		return (Integer)this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query = session.createQuery(hql);
				
				if(MapUtils.isNotEmpty(argsMap)){
					query.setProperties(argsMap);
				}/*end of if*/
				
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * 根據HQL條件及參數來查詢資料
	 * @param sql
	 * @param argsMap
	 * @return
	 */
	protected List findBySQLWithQueryObject(final String sql, final Map argsMap){
		return this.findBySQLWithQueryObject(sql, argsMap, null, null);
	}
	
	/**
	 * 根據HQL條件與查詢條件headIndex, maxCount取得資料，前端分頁顯示用
	 * 備註：headIndex, maxCount只負責記錄分頁資訊，參數值都由argsMap取得
	 * @param hql
	 * @param argsMap
	 * @param headIndex
	 * @param maxCount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List findBySQLWithQueryObject(final String sql, final Map argsMap, final String headIndex, final String maxCount){
		return (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query = session.createSQLQuery(sql); ;
				
				if(MapUtils.isNotEmpty(argsMap)){
					query.setProperties(argsMap);
				}/*end of if*/
				
				if(null != headIndex){
					query.setFirstResult(Integer.parseInt(headIndex));
					query.setMaxResults(Integer.parseInt(maxCount));
				}/*end of if*/
				
				return query.list();
			}
		});
	}
	
	/**
	 * 根據SQL條件與argsMap參數執行update語法, 亦可用於delete
	 * 
	 * @param sql
	 * @param argsMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected int updateBySQLWithQueryObject(final String sql, final Map argsMap){
		return (Integer)this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query = session.createSQLQuery(sql);
				
				if(MapUtils.isNotEmpty(argsMap)){
					query.setProperties(argsMap);
				}/*end of if*/
				
				return query.executeUpdate();
			}
		});
	}
}