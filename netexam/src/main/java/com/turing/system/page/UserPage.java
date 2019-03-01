package com.turing.system.page;

import org.apache.commons.lang.StringUtils;

import com.turing.framework.util.BasicPage;


public class UserPage extends BasicPage<UserPage> {


	private String userName;
	private String nickName;
	
	
	public String  reWhereString(UserPage page){
		StringBuffer whereSql=new StringBuffer();
		if(StringUtils.isNotBlank(page.getUserName())){
			whereSql.append(" and s.USER_NAME like '%"+page.getUserName().trim()+"%'");
		}
		if(StringUtils.isNotBlank(page.getNickName())){
			whereSql.append(" and s.NICK_NAME like '%"+page.getNickName().trim()+"%'");
		}
		
		return whereSql.toString();
	}

	public String   pageSql(UserPage page){
		
		int firstResult=(page.getPageNo()-1)*page.getPageSize()+1;        //从第几条开始
		int lastResult=page.getPageNo()*page.getPageSize();			//到第几条结束
		String sql="	select t.* from "+
						"(select rownum rn,s.* from sys_user s  "+
						"where 1=1 "+this.reWhereString(page)+") t  where t.rn>="+firstResult+" and t.rn<="+lastResult; 
		return sql;
		
	}

	public String   allSql(UserPage page){
		
		String sql="	select s.*"+ 
				"	from sys_user s "+ 
				"   where 1=1"  +
				this.reWhereString(page);
		return sql;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
