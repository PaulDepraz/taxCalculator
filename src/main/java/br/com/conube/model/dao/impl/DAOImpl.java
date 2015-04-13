package br.com.conube.model.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import br.com.conube.model.dao.DAO;

public abstract class DAOImpl<T, K> implements DAO<T, K> {
	
	protected List<T> mockedDB = new ArrayList<T>();

	@Override
	public void persist(T entity) {
		if(mockedDB.contains(entity)){
			mockedDB.set(mockedDB.indexOf(entity), entity);
		}else{
			mockedDB.add(entity);
		}
	}

	@Override
	public T searchByName(String name) {
		for (T entity : mockedDB) {
			String n;
			try {
				n = (String) entity.getClass().
						getMethod("getName").invoke(entity);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				return null;
			}
			if(n.contentEquals(name)){
				return entity;
			}
		}
		return null;
	}

}
