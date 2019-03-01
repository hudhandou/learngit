package com.turing.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turing.manage.entity.Subject;
import com.turing.manage.entity.Topic;
import com.turing.manage.mapper.SubjectMapper;
import com.turing.manage.mapper.TopicMapper;
import com.turing.manage.page.TopicPage;
import com.turing.manage.service.ITopicService;
import com.turing.system.entity.SysDept;
@Service
public class TopicService implements ITopicService{

	@Autowired
	private TopicMapper mapper;
	@Autowired
	private SubjectMapper submapper;
	@Override
	public List<Topic> queryAll(TopicPage page) {
		// TODO Auto-generated method stub
		return mapper.queryAll(page);
	}

	@Override
	public Topic queryById(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}


	@Override
	@Transactional
	public int save(Topic t) {
		t.setTopicId(System.currentTimeMillis()+"");
		return mapper.insert(t);
	}

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
				String topicName=mapper.getTopicNameByTopId(id);
				names = names+topicName+"," ;
			} else {
				mapper.deleteByPrimaryKey(id);
			}
		}
		if(!"".equals(names)) {
			names=names.substring(0,names.length()-1);//把后面的逗号去掉
		}
		return names;
	}

	@Override
	public List<Subject> query() {
		return submapper.query();
	}

	@Override
	@Transactional
	public int edit(Topic t) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(t);
	}

	
}
