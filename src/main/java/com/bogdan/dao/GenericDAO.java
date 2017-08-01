package com.bogdan.dao;

import java.io.Serializable;
/**
 *  Para evitar definir los mismos métodos en cada interfaz DAO se ha creado esta interfaz 
 * @author bogdan
 * Tutorial -> http://www.cursohibernate.es/doku.php?id=unidades:07_arquitectura:03_dao
 * @param <T> representa la entidad
 * @param <ID> representa el identificador de la entidad
 */
public interface GenericDAO<T,ID extends Serializable> {

	T create ();
	void saveOrUpdate(T entity);
	T get(ID id);
	void delete(ID id);
}
