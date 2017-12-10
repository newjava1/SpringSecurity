/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.byron.ss.dao;

import org.springframework.stereotype.Repository;

import com.byron.ss.common.base.BaseHibernateDao;
import com.byron.ss.model.UsersGroups;

@Repository
public class UsersGroupsDao extends BaseHibernateDao<UsersGroups,java.lang.String>{

	public Class getEntityClass() {
		return UsersGroups.class;
	}
	
	/*public Page findPage(UsersGroupsQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from UsersGroups t where 1=1 "
			  	+ "/~ and t.groupId = {groupId} ~/"
			  	+ "/~ and t.groupName = {groupName} ~/"
			  	+ "/~ and t.userId = {userId} ~/"
			  	+ "/~ and t.userName = {userName} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from UsersGroups t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getGroupId())) {
            sql2.append(" and  t.groupId = :groupId ");
        }
        if(isNotEmpty(query.getGroupName())) {
            sql2.append(" and  t.groupName = :groupName ");
        }
        if(isNotEmpty(query.getUserId())) {
            sql2.append(" and  t.userId = :userId ");
        }
        if(isNotEmpty(query.getUserName())) {
            sql2.append(" and  t.userName = :userName ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}
	*/

}
