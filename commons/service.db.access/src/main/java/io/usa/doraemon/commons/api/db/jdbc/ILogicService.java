package io.usa.doraemon.commons.api.db.jdbc;

import java.util.List;

/**
 * 
 * @author Rambo
 *
 * @param <T>
 */
public interface ILogicService<T> {
	public void create(T model);

	public void create(List<T> models);

	public void update(T model);

	public void update(List<T> models);

	public void delete(T model);

	public void delete(List<T> models);
}
