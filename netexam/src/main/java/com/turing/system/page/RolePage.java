package com.turing.system.page;

import org.apache.commons.lang.StringUtils;

import com.turing.framework.util.BasicPage;


public class RolePage extends BasicPage<RolePage> {


	private String roleName;
	
	
	public String  reWhereString(RolePage page){
		StringBuffer whereSql=new StringBuffer();
		if(StringUtils.isNotBlank(page.getRoleName())){
			whereSql.append(" and s.ROLE_NAME like '%"+page.getRoleName().trim()+"%'");
		}
		return whereSql.toString();
	}

	public String   pageSql(RolePage page){
		
		int firstResult=(page.getPageNo()-1)*page.getPageSize()+1;        //从第几条开始
		int lastResult=page.getPageNo()*page.getPageSize();			//到第几条结束
		String sql="	select t.* from "+
						"(select rownum rn,s.* from sys_role s  "+
						"where 1=1 "+this.reWhereString(page)+") t  where t.rn>="+firstResult+" and t.rn<="+lastResult; 
		return sql;
		
	}

	public String   allSql(RolePage page){
		
		String sql="	select s.*"+ 
				"	from sys_role s "+ 
				"   where 1=1"  +
				this.reWhereString(page);
		return sql;
		
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
