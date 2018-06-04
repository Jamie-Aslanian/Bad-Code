package org.springframework.jdbc.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import Excell_converter.todatabase.dao.DatabaseAO;

@Resource
public class JdbcTemplate implements EntityManager{
	private static final Logger LOGGER = Logger.getLogger( JdbcTemplate.class.getName() );

	static DatabaseAO jdbc=DatabaseAO.getdb();
	
	public static void query(String q)
	{
		if(q.contains("select")&&q.contains("id=")) 
		{
			int beginIndex=q.indexOf('=');
			long id = Integer.parseInt(q.substring(beginIndex));
			jdbc.findById(id);
		}
	}

	@Override
	public List<Object> createNativeQuery(String q) {
		List<Object> list = new ArrayList<>();
		if(q.contains("select")&&q.contains("id=")) 
		{
			int beginIndex=q.indexOf('=');
			long id = Integer.parseInt(q.substring(beginIndex));
			try {
				list.add(jdbc.findById(id));
			    return	list;
			} catch (Exception e) {
				LOGGER.severe(e.getMessage());
			}
		}
		return Collections.emptyList();
	}

	@Override
	public List<Object> createQuery(String query) {
		return Collections.emptyList();
	}
	
	
}
