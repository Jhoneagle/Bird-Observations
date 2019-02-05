package io.github.jhoneagle.birdobservations.db;

import java.util.List;

public interface Dao<T, K> {
    long insert(T save);
    T getOne(K id);
    List<T> getAll();
    int update(T update);
    void delete(T delete);
}
