package edu.psu.bookstore.model;

import edu.psu.bookstore.DateTime;
import edu.psu.bookstore.Genre;

public class Book
{
    //
    //  instance data
    private Integer bId;
    private String bTitle;
    private Long bISBN;
    private String bAuthor;
    private DateTime bDatepublished;
    private Genre bGenre;
    private Double bPrice;

    public Book(Integer bId, String bTitle, Long bISBN, String bAuthor, DateTime bDatepublished, Genre bGenre, Double bPrice) {
        this.bId = bId;
        this.bTitle = bTitle;
        this.bISBN = bISBN;
        this.bAuthor = bAuthor;
        this.bDatepublished = bDatepublished;
        this.bGenre = bGenre;
        this.bPrice = bPrice;
    }

    public Integer bId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getbTitle() {
        return bTitle;
    }

    public void setbTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public Long getbISBN() {return bISBN;}

    public void setbISBN(Long bISBN) {
        this.bISBN = bISBN;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }



    public DateTime getbDatepublished() {
        return bDatepublished;
    }

    public void setbDatepublishedd(DateTime bDatepublished) {
        this.bDatepublished = bDatepublished;
    }

    public Genre getbGenre() {
        return bGenre;
    }

    public void setbGenre(Genre bookGenre) {
        this.bGenre = bGenre;
    }


    public Double getbPrice() {
        return bPrice;
    }

    public void setbPrice(Double bPrice) {
        this.bPrice = bPrice;
    }

}
