package io.github.jhoneagle.birdobservations.db;

import java.util.List;

/**
 * Dao interface.
 *
 * @param <T> Object that is stored and taken from database.
 * @param <K> type of the key that is unique for elements in database and is used to select wanted objects.
 */
public interface Dao<T, K> {
    long insert(T save);
    T getOne(K key);
    List<T> getAll();
    int update(T update);
    void delete(T delete);
}
