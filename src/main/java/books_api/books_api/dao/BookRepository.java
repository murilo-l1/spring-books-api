package books_api.books_api.dao;


import books_api.books_api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

   @Query(value="SELECT b from Book b where b.bookCategory = :category")
   List<Book> queryBooksByCategory(@Param("category") String category);

   @Query("SELECT b FROM Book b where b.bookStatus = :status")
   List<Book> queryBooksByStatus(@Param("status") String status);

   @Query("select b from Book b where b.bookAuthor = :author")
   List<Book> queryBooksByAuthor(@Param("author") String author);

}