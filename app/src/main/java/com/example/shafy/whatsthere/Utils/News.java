package com.example.shafy.whatsthere.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mina essam on 08-Dec-16.
 */
public class News {
    private String author;
    private String title;
    private String description;
    private String newsURL;
    private String newsImageURL;
    private String category;
    private static String source="techcrunch";
    private static String API_KEY="547b4ff3bb534c99bdac17814859422c";
    private static String ARTICLES_URL="https://newsapi.org/v1/articles?source="+source+"&apiKey="+API_KEY;
    public static List<News> news=new ArrayList<>();
    public News(String author,String title,String description,String newsURL,String newsImageURL){
        this.author=author;
        this.title=title;
        this.description=description;
        this.newsURL=newsURL;
        this.newsImageURL=newsImageURL;
    }

    public static String getArticlesUrl() {
        return ARTICLES_URL;
    }

    public static void setSource(String source) {
        News.source = source;
        ARTICLES_URL="https://newsapi.org/v1/articles?source="+source+"&apiKey="+API_KEY;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getNewsURL() {
        return newsURL;
    }

    public String getNewsImageURL() {
        return newsImageURL;
    }

}
