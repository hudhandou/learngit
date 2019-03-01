package com.turing.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.turing.manage.entity.Subject;
import com.turing.manage.page.SubjectBigPage;
import com.turing.manage.page.SubjectPage;

public interface SubjectMapper {
    int deleteByPrimaryKey(String subjectId);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(String subjectId);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
    /**
     * 查询大类别
     * @param page
     * @return
     */
	List<Subject> queryAll(SubjectBigPage page);
    /**
     * 验证大类别名称是否重复
     * @param subjectName
     * @return
     */
	@Select("select count(*) from subject where subject_pid = '1'  and  subject_name = #{param1}")
	int querysubjectBigname(String subjectName);
      /**
       * 删除大类别验证
       * @param id
       * @return
       */
	@Select("select count(*) ge from subject where subject_pid = #{param1}")
	int deleteYanZheng(String id);
     /**
      * 根据id查询大类别名字
      * @param id
      * @return
      */
	@Select("select subject_name from subject where subject_pid = #{param1}")
	String getsubjectNameById(String id);
     /**
      * 查询科目
      * @param page
      * @return
      */
	List<Subject> queryAllBysubject(SubjectPage page);
    /**
     * 验证科目名称是否重复
     * @param subjectName
     * @return
     */
	@Select("select count(*) from subject where subject_pid != '1'  and  subject_name = #{param1}")
	int querysubjectname(String subjectName);
    /**
     * 删除科目验证
     * @param id
     * @return
     */
	@Select("select subject_name from subject where subject_id = #{param1}")
	int deleteYanZheng1(String id);
     /**
      * 查询所有大类别
      * @return
      */
	List<Subject> queryAllBig();
     /**
      * 根据 pid查询学科 不传page
      * @return
      */
	List<Subject> queryAllBysubject2(String id);
    /**
     * 根据pid 查询科目
     *  
     * @param id
     * @return
     */
	List<Subject> querysujectByPid(String id);
}