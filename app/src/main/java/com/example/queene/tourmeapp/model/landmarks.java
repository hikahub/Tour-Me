package com.example.queene.tourmeapp.model;

/**
 * Created by Queene on 11/11/2015.
 */
public class landmarks {
    private String place_name;
    private String address;
    private String place_desc;
    private String imageUrl;
    private String wiki_link;
    private String opening_days;
    private String admission;

    public landmarks(){}

    public landmarks(String place_name,String address,String place_desc,String imageUrl,String wiki_link, String opening_days, String admission)
    {
        this.place_name= place_name;
        this.address = address;
        this.place_desc  = place_desc;
        this.imageUrl = imageUrl;
        this.wiki_link = wiki_link;
        this.opening_days = opening_days;
        this.admission = admission;

    }

    public String getPlace_name(){return place_name;}
    public void setPlace_name(String place_name) {this.place_name = place_name;}

    public String getAddress(){return address;}
    public void setAddress(String address) {this.address=address;}

    public String getPlace_desc(){return place_desc;}
    public void setPlace_desc(String place_desc) {this.place_desc=place_desc;}

    public String getWiki_link(){return wiki_link;}
    public void setWiki_link(String wiki_link) {this.wiki_link=wiki_link;}

    public String getOpening_days(){return opening_days;}
    public void setOpening_days(String opening_days) {this.opening_days=opening_days;}

    public String getImageUrl() {return imageUrl;}
    public void setImageUrl(String imageUrl) {this.imageUrl= imageUrl;}

    public String getAdmission() {return admission;}
    public void setAdmission(String admission) {this.admission= admission;}
}
