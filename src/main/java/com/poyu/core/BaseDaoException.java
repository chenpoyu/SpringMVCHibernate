package com.poyu.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.ApplicationContext;

public class BaseDaoException extends Exception {

	private static final long serialVersionUID = 1L;

	private Object[] args = null;

	private String errorCode;

	private String message;

	private List<BaseDaoException> exceptiionList = null;// 儲存多筆exception

	public BaseDaoException() {
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public BaseDaoException(String errorCode, String message) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public BaseDaoException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.errorCode = errorCode;
	}

	public BaseDaoException(String message) {
		super(message);
		this.message = message;
	}

	public BaseDaoException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public BaseDaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * 建構一個BaseDaoException 例如：mesage= error.factoryNotFound => 其i18n為 無{0} 工廠編碼
	 * args = {"805"} 顯示 無 805 工廠編碼
	 * 
	 * @param message
	 *            錯誤訊息
	 * @param args
	 *            傳入錯誤訊息的參數
	 */
	public BaseDaoException(String message, Object[] args) {
		super(message);
		this.args = args;
		this.message = message;
	}

	/**
	 * @param message
	 * @param cause
	 * @param args
	 *            傳入錯誤訊息的參數
	 */
	public BaseDaoException(String message, Throwable cause, Object[] args) {
		super(message, cause);
		this.args = args;
	}

	/**
	 * 增加BaseDaoException 到 list中
	 * 
	 * @param exception
	 * @return boolean
	 */
	public boolean addBaseDaoException(BaseDaoException exception) {
		if (null == exceptiionList) {
			exceptiionList = new ArrayList<BaseDaoException>();
		}
		return exceptiionList.add(exception);
	}

	public String getMessageInfo(final Locale locale, ApplicationContext ctx) {
		try {
			return ctx.getMessage(this.getMessage(), this.getArgs(), locale);
		} catch (Exception e) {
			e.printStackTrace();
			return this.getMessage();
		}
	}

	/**
	 * 取得 BaseDaoExceptionList
	 * 
	 * @return
	 */
	public List<BaseDaoException> getBaseDaoExceptionList() {
		return this.exceptiionList;
	}

	/**
	 * 取得傳入錯誤訊息的參數
	 * 
	 * @return
	 */
	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
