package com.turing.manage.mapper;

import java.util.List;

import com.turing.manage.entity.SubjectPaperTable;

public interface SubjectPaperTableMapper {
    int deleteByPrimaryKey(String subjectPaperId);

    int insert(SubjectPaperTable record);

    int insertSelective(SubjectPaperTable record);

    SubjectPaperTable selectByPrimaryKey(String subjectPaperId);

    int updateByPrimaryKeySelective(SubjectPaperTable record);

    int updateByPrimaryKey(SubjectPaperTable record);
    /**
     * 删除试卷表
     * @param paperId
     */
	void deleteByPaperId(String paperId);
	/**
	 * 查询试卷的所有学科的id
	 * @param paperId
	 * @return
	 */
	List<SubjectPaperTable> queryquerySubjectPaerById(String paperId);
	/**
	 * 删除中间表数据
	 */
	void deleteSptByPaperId(String string);
}