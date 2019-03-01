package com.turing.framework.util;

import java.util.LinkedList;

/**
* @author zhaogang
* @date 2018年4月23日 上午11:32:07
* @desc
*/
public class Singleton {
	
	private static Singleton singleton = null;
	
	private Singleton() {
	}
	public static Singleton getInstance() {
		if (singleton != null) {
			return singleton;
		}else {
			singleton = new Singleton();
			return singleton; 
		}
	}
	/**
	 * 队列,装出库的票据编号
	 */
	private LinkedList<String> pjnos = new LinkedList<String>();
	private LinkedList<String> rkpjnos = new LinkedList<String>();
	private LinkedList<String> zypjnos = new LinkedList<String>();
	private LinkedList<String> dbpjnos = new LinkedList<String>();
	private LinkedList<String> fspjnos = new LinkedList<String>();
	private LinkedList<String> thpjnos = new LinkedList<String>();
	private LinkedList<String> xspjnos = new LinkedList<String>();

	public LinkedList<String> getPjnos() {
		return pjnos;
	}
	public void setPjnos(LinkedList<String> pjnos) {
		this.pjnos = pjnos;
	}
	public LinkedList<String> getRkpjnos() {
		return rkpjnos;
	}
	public void setRkpjnos(LinkedList<String> rkpjnos) {
		this.rkpjnos = rkpjnos;
	}
	public LinkedList<String> getZypjnos() {
		return zypjnos;
	}
	public void setZypjnos(LinkedList<String> zypjnos) {
		this.zypjnos = zypjnos;
	}
	public LinkedList<String> getDbpjnos() {
		return dbpjnos;
	}
	public void setDbpjnos(LinkedList<String> dbpjnos) {
		this.dbpjnos = dbpjnos;
	}
	public LinkedList<String> getFspjnos() {
		return fspjnos;
	}
	public void setFspjnos(LinkedList<String> fspjnos) {
		this.fspjnos = fspjnos;
	}
	public LinkedList<String> getThpjnos() {
		return thpjnos;
	}
	public void setThpjnos(LinkedList<String> thpjnos) {
		this.thpjnos = thpjnos;
	}
	public LinkedList<String> getXspjnos() {
		return xspjnos;
	}
	public void setXspjnos(LinkedList<String> xspjnos) {
		this.xspjnos = xspjnos;
	}
}
