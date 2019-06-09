package ru.dstu.railway.storage.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dstu.railway.storage.entity.StatesEntity;

@Repository
public interface StatesDao extends CrudRepository<StatesEntity, Long> {
    Iterable<StatesEntity> findByCodeAndType(String code, String type);
}
