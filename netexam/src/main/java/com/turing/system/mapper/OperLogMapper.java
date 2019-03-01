package com.turing.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.turing.system.entity.OperLog;
import com.turing.system.page.OperLogPage;

public interface OperLogMapper {

	@Insert("insert into operlog "+
		 " (oper_id, oper_personid, oper_personname, oper_time, oper_type, oper_desc, oper_model) "+
		 " values "+
		 " (#{operId}, #{operPersonid}, #{operPersonname}, #{operTime}, #{operType}, #{operDesc}, #{operModel})")
	public void insertSelective(OperLog log);
	/**
	 * 查询所有日志
	 * @return
	 */
	public List<OperLog> queryLog(OperLogPage page);

}
