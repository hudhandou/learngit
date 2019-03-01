package com.turing.system.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.turing.system.entity.SysUser;
import com.turing.system.page.UserPage;

public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    /**
     * 对用户进行条件查询
     * @param user
     * @return
     */
	List<SysUser> selective(SysUser user);
	/**
	 * 对用户和人员联表进行条件查询
	 * @param user
	 * @return
	 */
	List<SysUser> selectiveWithPerson(SysUser user);
	
	/**
	 * 进行分页的条件查询
	 * @param page
	 * @return
	 */
	@SelectProvider(method="pageSql",type=UserPage.class)
	@ResultMap("BaseResultMap")
	List<SysUser> query(UserPage page);
	
	/**
	 * 对所有数据进行条件查询
	 * @param page
	 * @return
	 */
	@SelectProvider(method="allSql" ,type=UserPage.class)
	@ResultMap("BaseResultMap")
	List<SysUser> queryAll(UserPage page);

	List<SysUser> selectNotInIds(List list);
	/**
	 * 根据用户id查询角色名称
	 * @param sysUser
	 * @return
	 */
	/**
	@Select("select  role_name from sys_role \r\n" + 
			"where role_id in (select role_id from sys_user_role where user_id=#{param1})")
	*/
	Set<String> getRoleNameByUserId(String userId);
}