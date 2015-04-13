package br.com.conube.model.dao;

/**
 * Objeto de acesso aos dados.
 * Data Access Object
 * @author Paul
 * @see <a href="http://en.wikipedia.org/wiki/Data_access_object">Data Access Object on wikipedia</a>
 */
public interface DAO<T,K> {
	
	void persist(T entity);
	
	T searchByName(String name);
	
}
