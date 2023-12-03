import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Book {
    private String title;
    private int year;

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + " (" + year + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Book> books = List.of(
                new Book("Book1", 1998),
                new Book("Book2", 2005),
                new Book("Book3", 1985),
                new Book("Book4", 1995)
                // Додайте інші книги...
        );

        List<Book> oldestBooks = books.stream()
                .sorted(Comparator.comparingInt(Book::getYear))
                .limit(3)
                .collect(Collectors.toList());

        oldestBooks.forEach(System.out::println);
    }
}
