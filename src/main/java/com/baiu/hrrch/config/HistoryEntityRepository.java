package com.baiu.hrrch.config;

import com.baiu.hrrch.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface HistoryEntityRepository<T extends HistoryEntity, ID extends String> extends JpaRepository<T, ID> {
    @Transactional
    @Modifying
    default <S extends T> S update(S entity) {
        Optional<T> oldVersion = findById((ID) entity.getGuid());
        if (oldVersion.isEmpty()) {
            throw new EntityNotFoundException(entity.getClass(), entity.getGuid());
        }
        deleteById((ID) oldVersion.get().getGuid());
        entity.setId(null);
        return save(entity);
    }

    @Transactional
    @Modifying
    default <S extends T> List<S> updateAll(Iterable<S> entities) {
        List<String> guids = new ArrayList<>();
        entities.forEach(e -> {
            guids.add(e.getGuid());
            e.setId(null);
        });
        deleteAllById((Iterable<? extends ID>) guids);
        return saveAll(entities);
    }

    @Transactional
    @Modifying
    default <S extends T> S insert(S entity) {
        entity.setGuid(UUID.randomUUID().toString());
        return save(entity);
    }

    @Transactional
    @Modifying
    default <S extends T> List<S> insertAll(Iterable<S> entities) {
        entities.forEach(e -> e.setGuid(UUID.randomUUID().toString()));
        return saveAll(entities);
    }

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.guid = ?1 and e.actual = true")
    Optional<T> findById(ID id);

    @Override
    @Transactional(readOnly = true)
    @Query("select (count(e) > 0) from #{#entityName} e where e.guid = ?1 and e.actual = true")
    boolean existsById(ID id);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.actual = true")
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.guid in ?1 and e.actual = true")
    List<T> findAllById(Iterable<ID> ids);

    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.guid = ?1")
    Optional<T> findAllVersionsById(ID id);

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.actual = true")
    long count();

    @Override
    @Query("update #{#entityName} e set e.actual=false where e.guid = ?1")
    @Transactional
    @Modifying
    void deleteById(ID id);

    @Query("update #{#entityName} e set e.actual=false where e.guid in ?1")
    @Transactional
    @Modifying
    void deleteAllById(Iterable<? extends ID> guids);

    @Override
    @Query("update #{#entityName} e set e.actual=false where e = ?1")
    @Transactional
    @Modifying
    void delete(T entity);

    @Override
    @Query("update #{#entityName} e set e.actual=false where e in ?1")
    @Transactional
    @Modifying
    void deleteAll(Iterable<? extends T> entities);

    @Override
    @Query("update #{#entityName} e set e.actual=false")
    @Transactional
    @Modifying
    void deleteAll();
}
