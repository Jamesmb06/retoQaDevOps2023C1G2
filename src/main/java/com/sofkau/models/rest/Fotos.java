package com.sofkau.models.rest;

public class Fotos {
    private String albumId;
    private String title;

    public Fotos() {
    }

    public Fotos(String albumId, String title) {
        this.albumId = albumId;
        this.title = title;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
