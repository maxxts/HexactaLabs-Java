package ar.com.hexacta.tpl.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.service.impl.BooksServiceImpl;

/**
 * @author lnapoli
 * 
 */
public class BookWS{

    public BookWS() {

    }
    
    BooksServiceImpl bookService;

	@GET
	@Path("/books")
	@Produces("application/json")
	public List<Book> findAllBooks() {
		return bookService.findAllBooks();
	}
	
	@GET
	@Path("/books/{bookId}")
	@Produces("application/json")
	public Book findBook(@PathParam("bookId") final String bookId) {
		return bookService.findBook(bookId);
	}

	@POST
	@Path("/books")
	@Consumes("application/json")
	public Response createBook(@Multipart(value = "book2", type = "application/json") String jsonBook) {
		try {
			
			bookService.createBook(parseBook(jsonBook));
			
		} catch (JsonParseException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();

	}

	@PUT
	@Path("/books/{bookId}")
	@Consumes("application/json")
	public Response updateBook(@PathParam("bookId") final String bookId, String jsonBook) {
		try {			
			Book book = parseBook(jsonBook);
			book.setId(new Long(bookId));
			bookService.updateBook(book);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/books/{bookId}")
	public void deleteBook(@PathParam("bookId") final String bookId) {
		bookService.deleteBookById(bookId);
	}
	
	private Book parseBook(String jsonBook) throws JsonParseException, JsonMappingException, IOException {
		Book newBook = new Book();
		ObjectMapper mapper = new ObjectMapper();
		newBook = mapper.readValue(jsonBook, Book.class);
		return newBook;
	}
	
	public BooksServiceImpl getBookService() {
		return bookService;
	}

	public void setBookService(BooksServiceImpl bookService) {
		this.bookService = bookService;
	}
}
