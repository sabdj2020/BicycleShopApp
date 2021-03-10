package com.revature.data;

import java.util.Set;

import com.revature.beans.User;

public interface GenericDAO <T> {
	// CRUD operations (create, read, update, delete)
	public T add(T t) throws Exception;
	public T getById(Integer id);
	public Set<T> getAll(User u);
	public T update(T t);
	public T delete(T t);
}

