package com.turing.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.turing.manage.entity.Classs;
import com.turing.manage.page.ClassPage;

public interface ClasssMapper {
    int deleteByPrimaryKey(String classId);

    int insert(Classs record);

    int insertSelective(Classs record);

    Classs selectByPrimaryKey(String classId);

    int updateByPrimaryKeySelective(Classs record);

    int updateByPrimaryKey(Classs record);

    /**
     * 查询所有数据
     * @return
     */
   
	/**
	 * 验证名字是否重复
	 * @param className
	 * @return
	 */
	@Select("select count(*) from classs where class_name=#{param1}")
	int checkName(String className);

	List<Classs> queryAll(ClassPage page);
	/**
	 * 验证是否删除
	 * @param id
	 * @return
	 */
	int deleteYanzheng(String id);
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@Select("select class_name from classs where class_id=#{param1}")
	String getClassNameByCkId(String id);
	/**
	 * 改变当前状态
	 * @param bs
	 */
	void updateAll(Classs bs);

	/**
	 * 查询班级
	 * @param c
	 * @return
	 */
	@Select("select class_id, class_name from classs where class_state='Y'")
	@ResultMap("BaseResultMap")
	Classs queryClass();
	/**
	 * 设置非默认班级
	 * @param c
	 * @return
	 */
	int updateByPrimaryKeySelectiveFei(Classs c);

}