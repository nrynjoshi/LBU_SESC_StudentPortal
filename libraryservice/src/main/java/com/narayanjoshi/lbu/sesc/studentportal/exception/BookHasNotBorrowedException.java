package com.narayanjoshi.lbu.sesc.studentportal.exception;

public class BookHasNotBorrowedException  extends Exception{

    public BookHasNotBorrowedException(String title){
        super("This book has not been borrowed by student ");
    }
}
