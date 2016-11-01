package com.books.web.api;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    public Long id;
    private String bookName;
    private String authorName;


}