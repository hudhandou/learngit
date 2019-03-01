package com.turing.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.turing.manage.entity.Paper;
import com.turing.manage.page.PaperPage;

public interface PaperMapper {
    int deleteByPrimaryKey(String paperId);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(String paperId);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);
    /**
     * 查询所有数据
     * @param page 
     * @return
     */
	List<Paper> queryAll(PaperPage page);
	/**
	 * 转到修改页面
	 * @param id
	 * @return
	 */
	Paper updateByPrimaryKeySelective(String id);
	/**
	 * 查询学生试卷
	 * @return
	 */
	List<Paper> queryStudentPaper();
	/**
	 * 删除验证
	 * @param id
	 * @return
	 */
	@Select("select count(*) from studentpaper where paper_id=#{param1}")
	int deleteYanZheng(String id);
	/**
	 * 根据id查询试卷名
	 * @param id
	 * @return
	 */
	@Select("select paper_name from paper where paper_id = #{id}")
	String getpaperNameById(String id);
	/**
	 * 根据id查询试卷
	 * @param id
	 * @return
	 */
	@Select("select paper_id, paper_name, paper_createtime, paper_needtime, paper_score, paper_state, paper_tmsl \r\n" + 
			"from paper where paper_id=#{param1}")
	Paper queryPaperById(String id);

	

	

	
}