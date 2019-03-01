package com.turing.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turing.manage.entity.Classs;
import com.turing.manage.mapper.ClasssMapper;
import com.turing.manage.page.ClassPage;
import com.turing.manage.service.IClassService;

@Service
public class ClassService implements IClassService{
	/**
	 * 班级mapper
	 */
	@Autowired
	private ClasssMapper mapper;
	/**
	 * 根据id查询
	 */
	@Override
	public Classs queryById(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}
	/**
	 * 添加
	 */
	@Override
	@Transactional
	public int save(Classs c) {
		c.setClassId(System.currentTimeMillis()+"");
		c.setClassState("N");
		c.setCreateDate(new Date());
		return mapper.insertSelective(c);
	}
	/**
	 * 修改
	 */	
	@Override
	@Transactional
	public int edit(Classs c) {
		
		return mapper.updateByPrimaryKeySelective(c);
	}
	/**
	 * 删除
	 */
	@Override
	@Transactional
	public String delete(String ids) {
		String names="";
		String[] strs=ids.split(",");
		for (int i = 0; i < strs.length; i++) {
			String id = strs[i];
			//判断主键能不能删除
			int ge=mapper.deleteYanzheng(id);
			if ( ge>0 ) {
				String className=mapper.getClassNameByCkId(id);
				names = names+className+"," ;
			} else {
				mapper.deleteByPrimaryKey(id);
			}
		}
		if(!"".equals(names)) {
			names=names.substring(0,names.length()-1);//把后面的逗号去掉
		}
		return names;
	}
	/**
	 * 验证名字是否重复
	 */
	@Override
	public int checkName(String className) {
		// TODO Auto-generated method stub
		return mapper.checkName(className);
	}
	/**
	 * 查询所有数据
	 */
	@Override
	public List<Classs> queryAll(ClassPage page) {
		// TODO Auto-generated method stub
		return mapper.queryAll(page);
	}
	
	/**
	 * 设置默认
	 */
	@Override
	@Transactional
	public int moren(Classs c, String id) {
		//设置班级为非默认
		c.setClassState("N");
		c.setClassId(null);
		int tiao=mapper.updateByPrimaryKeySelectiveFei(c);
		//设置选中班级为默认班级
		c.setClassState("Y");
		c.setClassId(id); 
		tiao=mapper.updateByPrimaryKeySelective(c);
		return tiao;
	}
	
}
