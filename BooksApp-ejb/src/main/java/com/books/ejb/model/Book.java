package com.books.ejb.model;

import consts.CatalogType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Book implements Serializable {

    public Long id;
    private String bookName;
    private String authorName;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + bookName.hashCode();
        result = prime * result + authorName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (bookName != null && other.bookName != null && !bookName.equals(other.bookName) )
            return false;
        if (authorName != null && other.authorName != null && !authorName.equals(other.authorName))
            return false;
        return true;
    }
}
