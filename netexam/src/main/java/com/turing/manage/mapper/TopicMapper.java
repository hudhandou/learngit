package com.turing.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.turing.manage.entity.Topic;
import com.turing.manage.page.TopicPage;

public interface TopicMapper {
    int deleteByPrimaryKey(String id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(String topicId);

    int updateByPrimaryKeySelective(Topic record);
 
    int updateByPrimaryKey(Topic record);
    /**
     * 查询所有数据
     * @param page
     * @return
     */
	List<Topic> queryAll(TopicPage page);
	/**
	 * 验证是否可以删除
	 * @param id
	 * @return
	 */
	int deleteYanzheng(String id);
	/**
	 * 根据题目id查询题目名称
	 * @param id
	 * @return
	 */
	@Select ("select  topic_name from topic where topic_id=#{param1}")
	String getTopicNameByTopId(String id);
	/**
     * 查询学生考试页面的题目
     * @param id
     * @return
     */
	@Select("select topic_id, s.subject_id, topic_name, topic_answer_a, topic_answer_b, topic_answer_c, topic_answer_d, topic_answer_true ,\r\n" + 
			"	          p.paper_tmsl\r\n" + 
			"	          from topic ti inner join subject s\r\n" + 
			"	          on ti.subject_id = s.subject_id inner join subject_paper_table spt\r\n" + 
			"	          on s.subject_id  = spt.subject_id inner join paper p\r\n" + 
			"	          on spt.paper_id = p.paper_id\r\n" + 
			"	          where p.paper_id= #{id}")
	@ResultMap("BaseResultMap")
	List<Topic> queryTopById(String id);
}