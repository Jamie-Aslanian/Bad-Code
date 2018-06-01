package org.springframework.jdbc.core;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import Excell_converter.todatabase.dao.DatabaseAO;

@Resource
public class JdbcTemplate implements EntityManager{


	static DatabaseAO jdbc=DatabaseAO.getdb();
	
	public static void query(String q) throws Exception 
	{
		if(q.contains("select")&&q.contains("id=")) 
		{
			int beginIndex=q.indexOf("=");
			long id = Integer.parseInt(q.substring(beginIndex));
			jdbc.findById(id);
		}
	}

	@Override
	public List<Object> createNativeQuery(String q) {
		List<Object> list = new ArrayList<>();
		if(q.contains("select")&&q.contains("id=")) 
		{
			int beginIndex=q.indexOf("=");
			long id = Integer.parseInt(q.substring(beginIndex));
			try {
				list.add(jdbc.findById(id));
			    return	list;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Object> createQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
