package com.example.pathebredabioscoopapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Films implements Serializable {
    private final String TAG = getClass().getSimpleName();
    private int id;
    private int duration;
    private double rating;
    private String title;
    private String description;
    private String genre;
    private String poster;
    private String director;
    private String trailer;
    private String releaseDate;
    private ArrayList<Reviews> reviews;
    private ArrayList<Actors> actors;

    public Films(int id, String title, String imgUrl, String overview, String release, double rating) {
        this.id = id;
        this.rating = rating;
        this.poster = imgUrl;
        this.title = title;
        this.description = overview;
        this.releaseDate = release;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Reviews> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Actors> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actors> actors) {
        this.actors = actors;
    }
    }

