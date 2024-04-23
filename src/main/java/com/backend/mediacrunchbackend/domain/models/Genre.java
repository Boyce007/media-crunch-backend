package com.backend.mediacrunchbackend.domain.models;

public enum Genre {
    SCI_FI("Sci-Fi"),ACTION("Action"),ROMANCE("Romance"),ANIMATION("Animation"),
    COMEDY("Comedy"),THRILLER("Thriller "),HORROR("Horror"),ADVENTURE("Adventure "),MYSTERY("Mystery"),NON_FICTION("Non Fiction");

    private String value;
    Genre(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
