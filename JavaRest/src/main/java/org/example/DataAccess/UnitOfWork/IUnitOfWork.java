package org.example.DataAccess.UnitOfWork;

import org.example.DataAccess.Repository.IRepository;

public interface IUnitOfWork {
    <T> IRepository<T> GetRepository(Class<T> type);
}
