package edu.escuelaing.arep.controller;

import edu.escuelaing.arep.annotations.*;
import edu.escuelaing.arep.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@RestController
public class BookController {

    private static final List<Book> books = new ArrayList<>();

    @GetMapping("/getBooks")
    public String getBooks() {
        StringBuilder json = new StringBuilder("{ \"books\": [");
        for (int i = 0; i < books.size(); i++) {
            json.append("{\"title\": \"").append(books.get(i).getTitle())
                    .append("\", \"author\": \"").append(books.get(i).getAuthor()).append("\"}");
            if (i < books.size() - 1) json.append(", ");
        }
        json.append("] }");
        return json.toString(); // Devuelve la lista de libros como un String en formato JSON
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam(value = "bookTitle") String title,
                          @RequestParam(value = "bookAuthor") String author) {
        if (title.isEmpty() || author.isEmpty()) {
            return "{\"error\": \"El título y el autor no pueden estar vacíos.\"}";
        }
        System.out.println(title + author);
        books.add(new Book(title, author));
        return "{\"message\": \"Libro añadido: " + title + " por " + author + "\"}";
    }


    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestParam("title") String title) {
        if (title.isEmpty()) {
            return "{\"error\": \"El título no puede estar vacío.\"}";
        }

        boolean removed = books.removeIf(book -> book.getTitle().equals(title));
        return removed ? "{\"message\": \"Libro eliminado: " + title + "\"}" : "{\"error\": \"Libro no encontrado\"}";
    }

    private String readRequestBody(BufferedReader reader) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return requestBody.toString().trim();
    }

    private String extractValue(String json, String key) {
        String searchKey = "\"" + key + "\":\"";
        int start = json.indexOf(searchKey);
        if (start == -1) return "";

        start += searchKey.length();
        int end = json.indexOf("\"", start);
        return (end != -1) ? json.substring(start, end).trim() : "";
    }
}