package training360.books_app.dtos;

import lombok.Data;

@Data
public class CreateBookCommand {

    private String isbn;
    private String title;
}
