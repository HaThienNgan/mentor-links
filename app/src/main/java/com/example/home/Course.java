package com.example.home;

public class Course {
    private String id;
    private String shortname;
    private String image;
    private String slug;
    private String introduction;
    private String title;
    private String mentor_Image;
    private String mentorid;
    private String mentor_Name;
    private String fee;
    private String data_End_Register;
    private String date_Begin;
    private String ratingval;
    private int ratingnum;
    private String url_tags;
    public String cardtype;

    public Course(String id, String shortname, String image, String slug, String introduction, String title, String mentor_Image, String mentorid, String mentor_Name, String fee, String data_End_Register, String date_Begin, String ratingval, int ratingnum, String url_tags, String cardtype) {
        this.id = id;
        this.shortname = shortname;
        this.image = image;
        this.slug = slug;
        this.introduction = introduction;
        this.title = title;
        this.mentor_Image = mentor_Image;
        this.mentorid = mentorid;
        this.mentor_Name = mentor_Name;
        this.fee = fee;
        this.data_End_Register = data_End_Register;
        this.date_Begin = date_Begin;
        this.ratingval = ratingval;
        this.ratingnum = ratingnum;
        this.url_tags = url_tags;
        this.cardtype = cardtype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMentor_Image() {
        return mentor_Image;
    }

    public void setMentor_Image(String mentor_Image) {
        this.mentor_Image = mentor_Image;
    }

    public String getMentorid() {
        return mentorid;
    }

    public void setMentorid(String mentorid) {
        this.mentorid = mentorid;
    }

    public String getMentor_Name() {
        return mentor_Name;
    }

    public void setMentor_Name(String mentor_Name) {
        this.mentor_Name = mentor_Name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getData_End_Register() {
        return data_End_Register;
    }

    public void setData_End_Register(String data_End_Register) {
        this.data_End_Register = data_End_Register;
    }

    public String getDate_Begin() {
        return date_Begin;
    }

    public void setDate_Begin(String date_Begin) {
        this.date_Begin = date_Begin;
    }

    public String getRatingval() {
        return ratingval;
    }

    public void setRatingval(String ratingval) {
        this.ratingval = ratingval;
    }

    public int getRatingnum() {
        return ratingnum;
    }

    public void setRatingnum(int ratingnum) {
        this.ratingnum = ratingnum;
    }

    public String getUrl_tags() {
        return url_tags;
    }

    public void setUrl_tags(String url_tags) {
        this.url_tags = url_tags;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
}
