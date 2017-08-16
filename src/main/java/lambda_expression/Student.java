package lambda_expression;

import java.util.HashSet;
import java.util.Set;

public class Student {
  String name;
  Set<String> book;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<String> getBook() {
    return book;
  }

  public void setBook(Set<String> book) {
    this.book = book;
  }

  public void addBook(String book) {
    if (this.book == null)
      this.book = new HashSet<>();
    this.book.add(book);
  }
}
