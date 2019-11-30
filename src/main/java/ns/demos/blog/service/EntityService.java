package ns.demos.blog.service;

import ns.demos.blog.exception.BlogException;

import java.util.List;

public interface EntityService<T> {

    List<T> getAll();

    T getById(long id) throws BlogException;

    void save(T entity);

    void delete(T entity);

}
