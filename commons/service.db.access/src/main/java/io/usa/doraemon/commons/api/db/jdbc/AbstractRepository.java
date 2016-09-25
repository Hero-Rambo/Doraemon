package io.usa.doraemon.commons.api.db.jdbc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


/**
 * 
 * @author Rambo
 *
 * @param <T>
 */
abstract public class AbstractRepository<T> implements IRepository<T>{
	@Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;
	
	
	public String getTable(){
		ParameterizedType t = (ParameterizedType) this.getClass().getGenericSuperclass();		
		for(Type t1:t.getActualTypeArguments()){
			Class<?> c = (Class<?>) t1;
			return c.getSimpleName();
		}
		throw new RuntimeException("This Class:"+this.getClass().getName()+" should define a generic AbstractModel type as Class Parameter!");
    }	   
  


	@Override
	public void add(T model) {	
		StringBuilder sb = new StringBuilder().append("insert into ").append(this.getTable()).append(this.getInsertSql());
		this.jdbcTemplate.update(sb.toString(), this.getAddParams(model));
	}
	abstract protected Map<String,Object> getAddParams(T model);
	abstract protected String getInsertSql();


	@Override
	public void update(T model) {
//		this.jdbcTemplate.update(this.getUpdateSql(), this.getUpdateParams(model));
	}
//	abstract protected String getUpdateSql() ;
//	abstract protected Map<String,Object> getUpdateParams(T model) ;




	@Override
	public void query(T model) {
 		
	}



	@Override
	public void delete(T model) {
 		
	}



	@Override
	public void add(List<T> models) {		 
//		this.jdbcTemplate.update(getInsertSql(), getAddParams());
	}
	

	
}
