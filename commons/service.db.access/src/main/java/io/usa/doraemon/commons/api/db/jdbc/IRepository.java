package io.usa.doraemon.commons.api.db.jdbc;

import java.util.List;

/**
 * 
 * @author Rambo
 *
 * @param <T>
 */
public interface IRepository<T> {
	public void add(T model);
	
	public void add(List<T> models);
	
	public void update(T model);
	
	public void query(T model);
	
	public void delete(T model);
}
