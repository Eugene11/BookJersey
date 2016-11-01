package com.books.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.StatefulTimeout;
import javax.ejb.Stateless;

import com.books.ejb.model.Book;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;


@Stateless
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)
public class BookHandlerBean implements BookHandler {

    private static ConcurrentMap<Long, Book> inmemoryDataStorage;

    @PostConstruct
    private void initializeBean() {
        if (inmemoryDataStorage == null ) {
            inmemoryDataStorage = new ConcurrentHashMap<Long, Book>();
            /*
            Config config = new Config();
            HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
            inmemoryDataStorage = h.getMap("my-distributed-inmemoryDataStorage");*/
        }
    }

    @Override
    public Collection<Book> getAll() {
        return inmemoryDataStorage.values();
    }

    @Override
    public Book addBook(Book book) {
        // it's just example of adding new entity to storage because this example without DB
        long id = Math.abs(book.hashCode());
        book.setId(id);
        inmemoryDataStorage.putIfAbsent(id, book);
        return book;
    }

    @Override
    public Book getBook(Long id) {
        return  inmemoryDataStorage.get(id);
    }

    @Override
    public void deleteBook(Long id) {
        inmemoryDataStorage.remove(id);
    }

    @Override
    public Book update(Book book) {
        inmemoryDataStorage.put(book.getId(), book);
        return book;
    }

}
