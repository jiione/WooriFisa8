package model.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.domain.Book;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String role;
    private String name;
    private String id;
    private String pw;
    private ArrayList<Book> bookList;
}
