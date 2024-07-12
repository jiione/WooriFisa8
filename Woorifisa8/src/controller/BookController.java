package controller;




import model.domain.Book;
import model.domain.User;
import service.LibraryService;
import view.EndView;
import view.FailView;

public class BookController {
    private LibraryService libraryService = new LibraryService();

    public void viewAllBooks() {
        EndView.bookListView(libraryService.searchAllBook());
    }

    public void borrowBook(String isbn, User user) {
        Book book = libraryService.borrowBook(isbn, user);
        if (book != null) {
            EndView.successMessage("Book borrowed successfully: " + book.getTitle());
        } else {
            FailView.failMessage("Failed to borrow book. Book might be already borrowed or not found.");
        }
    }

    public void returnBook(String isbn, User user) {
        Book book = libraryService.returnBook(isbn, user);
        if (book != null) {
            EndView.successMessage("Book returned successfully: " + book.getTitle());
        } else {
            FailView.failMessage("Failed to return book. Book not found in user's borrowed list.");
        }
    }

    public void addBook(Book book, User admin) {
        Book newBook = libraryService.insertBook(book, admin);
        if (newBook != null) {
            EndView.successMessage("Book added successfully: " + newBook.getTitle());
        } else {
            FailView.failMessage("Failed to add book. Only admins can add books.");
        }
    }

    public void removeBook(String isbn, User admin) {
        boolean success = libraryService.deleteBook(isbn, admin);
        if (success) {
            EndView.successMessage("Book removed successfully.");
        } else {
            FailView.failMessage("Failed to remove book. Only admins can remove books.");
        }
    }
}
