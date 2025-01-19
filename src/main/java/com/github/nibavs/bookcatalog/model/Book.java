package com.github.nibavs.bookcatalog.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private int pages;
    private Status status;

    public Book(int id, String title, String author, int year, int pages, Status status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.status = status;
    }

    public Book(String title, String author, int year, int pages, Status status) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
