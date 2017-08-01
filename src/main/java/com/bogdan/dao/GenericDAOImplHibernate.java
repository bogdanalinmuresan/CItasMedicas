package com.bogdan.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bogdan.persistence.HibernateUtil;

/**
 * Clase que implementa de forma genérica el acceso a la base de datos mediante Hibernate. 
 * De esta clase heredan las implementaciones de cada interfaz DAO.
 * @author bogdan
 *
 * @param <T>
 * @param <ID>
 */
public class GenericDAOImplHibernate<T,ID extends Serializable> implements GenericDAO<T, ID> {

	SessionFactory sessionFactory;
	
	private final static Logger LOGGER = Logger.getLogger(GenericDAOImplHibernate.class.getName());
	
	public GenericDAOImplHibernate() {
		sessionFactory=HibernateUtil.getSessionFactory();
	}

	@Override
	public T create() {
		try {
			return getEntityClass().newInstance();
      	} catch (InstantiationException | IllegalAccessException ex) {
	          throw new RuntimeException(ex);
      	} catch (RuntimeException ex) {
	          throw ex;
      	} catch (Exception ex) {
	          throw new RuntimeException(ex);
      	}
	}

	@Override
	public void saveOrUpdate(T entity) {
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
		}catch(Exception e){
			
		}
		
	}

	@Override
	public T get(ID id) {
		Session session = sessionFactory.openSession();
		
			session.beginTransaction();
            T entity = (T)session.get(getEntityClass(), id);
            session.getTransaction().commit();
 
            return entity;
	}
	
	
	@Override
	public void delete(ID id) {
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			T entity = (T) session.get(getEntityClass(), id);
			if (entity == null) {
				throw new Exception("Los datos a borrar ya no existen");
			}
			session.delete(entity);
			session.getTransaction().commit();
		}catch(Exception e){
			
		}
	}
	
	/**
	 * metodo que devuelve la clase entidad, mediante reflexion
	 * @return la entidad
	 */
	private Class<T> getEntityClass() {
		//ParameterizedType -> https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/ParameterizedType.html
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}


	
	
}
