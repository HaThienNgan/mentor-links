package com.example.home;

public class Mentor {
    private String id;
    private String fullname;
    private String phone;
    private String experience;
    private String image;
    private String url_tags;
    private String cardtype;

    public Mentor(String id, String fullname, String phone, String experience, String image, String url_tags, String cardtype) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.experience = experience;
        this.image = image;
        this.url_tags = url_tags;
        this.cardtype = cardtype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

