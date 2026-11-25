/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._please_return_your_books_on_time;

/**
 *
 * @author user
 */
public abstract class Book {
    //books appear when the player roams around the library
    //books can be opened up and read so add a small line of text of the book
    //some books are monsters 
    //The Books that the player finds (the individual books) get their own class
    private String bookName;
    private boolean bookIsMonster;
    private int bookSection;       // 1-12, matches the numbers on the LibraryMap
    private String bookHint;        // a clue shown in the sorting mini-game
    private String bookType; //BookGiveStat or BookGiveSkill
    
    
    private String[] bookFoundText = new String[3];
    private String[] bookReturnedText = new String[3];
    
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookIsMonster(boolean bookIsMonster) {
        this.bookIsMonster = bookIsMonster;
    }

    public boolean isBookIsMonster() {
        return this.bookIsMonster;
    }
    
    public void setBookSection(int bookSection) {
        if (bookSection >= 1 && bookSection <= 12) {
            this.bookSection = bookSection;
        }
    }
    
    public int getBookSection() {
        return this.bookSection;
    }

    public void setBookHint(String bookHint) {
        this.bookHint = bookHint;
    }

    public String getBookHint() {
        return this.bookHint;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
    
    public String getBookType() {
        return this.bookType;
    }
    
    public void setBookFoundText(String[] bookFoundText) {
        this.bookFoundText = bookFoundText;
    } 
    
    public String[] getBookFoundText() {
        return this.bookFoundText;
    }
    
    public void setBookReturnedText(String[] bookReturnedText) {
        this.bookReturnedText = bookReturnedText;
    }

    public String[] getBookReturnedText() {
        return this.bookReturnedText;
    }
    
    public abstract void initializeBookStats();

    //The monster hiding inside the book (only used when bookIsMonster is true)
    public abstract Monsters createMonster();

    //What the player gets for shelving this book in the correct section
    public abstract void giveReward(Player player);

}
