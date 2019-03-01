package com.turing.system.page;

import org.apache.commons.lang.StringUtils;

import com.turing.framework.util.BasicPage;

public class PersonPage extends BasicPage<PersonPage>{
	
	private String ppName;
	private String deptId;
	
	public String  reWhereString(PersonPage page){
		StringBuffer whereSql=new StringBuffer();
		if(StringUtils.isNotBlank(page.getPpName())){
			whereSql.append(" and s.PP_NAME like concat(concat('%',#{ppName}),'%')");
		}
		
		if(StringUtils.isNotBlank(page.getDeptId())){
			whereSql.append(" and s.DEPT_ID = #{deptId}");
		}
		return whereSql.toString();
	}

	public String   pageSql(PersonPage page){
		int firstResult=(page.getPageNo()-1)*page.getPageSize()+1;        //从第几条开始
		int lastResult=page.getPageNo()*page.getPageSize();			//到第几条结束
		String sql="	select t.* from "+
						"(select rownum rn,s.*,sd.dept_name,sp.sp_name,su.nick_name from sys_person s  "+
						"left join sys_dept sd on s.dept_id=sd.dept_id "+
						"left join sys_post sp on s.sp_id=sp.sp_id "+
						"left join sys_user su on s.user_id = su.user_id "+
						"where 1=1 "+this.reWhereString(page)+") t  where t.rn>="+firstResult+" and t.rn<="+lastResult; 
		return sql;
	}

	public String   allSql(PersonPage page){	
		String sql="	select s.*"+ 
				"	from sys_person s "+
				"left join sys_dept sd on s.dept_id=sd.dept_id "+
				"left join sys_post sp on s.sp_id=sp.sp_id "+
				"left join sys_user su on s.user_id = su.user_id "+
				" where 1=1"  +
				this.reWhereString(page);
		return sql;
	}

	public String getPpName() {
		return ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
