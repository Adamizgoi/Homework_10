package ru.netology.homeworks.filmmanager;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class FilmItem {
    private int id;
    private @NonNull String name;
    private @NonNull String genre;
    private String image;
}
