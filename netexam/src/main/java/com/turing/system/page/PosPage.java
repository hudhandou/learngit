package com.turing.system.page;

import org.apache.commons.lang.StringUtils;

import com.turing.framework.util.BasicPage;

public class PosPage extends BasicPage<PosPage>{

	private String posName;
	private String deptId;

	@Override
	public String reWhereString(PosPage page) {
		StringBuffer whereSql=new StringBuffer();
		if(StringUtils.isNotBlank(page.getPosName())){
			whereSql.append(" and s.SP_NAME like concat(concat('%',#{posName}),'%')");
		}
		
		if(StringUtils.isNotBlank(page.getDeptId())){
			whereSql.append(" and s.DEPT_ID = #{deptId}");
		}
		return whereSql.toString();
	}

	@Override
	public String pageSql(PosPage page) {
		int firstResult=(page.getPageNo()-1)*page.getPageSize()+1;        //从第几条开始
		int lastResult=page.getPageNo()*page.getPageSize();			//到第几条结束
		String sql="	select t.* from "+
						"(select rownum rn,s.*,sd.dept_name from sys_post s  "+
						"left join sys_dept sd on s.dept_id = sd.dept_id "+
						"where 1=1 "+this.reWhereString(page)+") t  where t.rn>="+firstResult+" and t.rn<="+lastResult; 
		return sql;
	}

	@Override
	public String allSql(PosPage page) {
		String sql="	select s.*"+ 
				"	from sys_post s "+
				"left join sys_dept sd on s.dept_id = sd.dept_id "+
				" where 1=1"  +
				this.reWhereString(page);
		return sql;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}
