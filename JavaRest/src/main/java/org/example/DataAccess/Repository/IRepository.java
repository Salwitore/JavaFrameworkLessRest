package org.example.DataAccess.Repository;

import java.util.List;

public interface IRepository<T> {

    int Add (T model);
    T Get(int id);
    List<T> GetAll();
    void Update(T entity);
    void Delete(T entity);
    boolean SaveChanges();

}
