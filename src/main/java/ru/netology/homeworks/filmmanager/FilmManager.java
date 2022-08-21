package ru.netology.homeworks.filmmanager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmManager {
    private FilmItem[] films = new FilmItem[0];
    private int lastFilmsToShow = 10;
    private FilmItem[] repoForLastFilms = new FilmItem[lastFilmsToShow];

    //установка кол-ва последних фильмов, которые нужно показать методом findLast
    public void setLastFilmsToShow(int numberOfFilms) {
        if (checkLastFilmsToShow(numberOfFilms)) {
            lastFilmsToShow = numberOfFilms;
        }
    }

    //проверка, ок ли число, которое пытаются установить методом setLastFilmToShow
    private boolean checkLastFilmsToShow(int numbersOfFilms) {
        return numbersOfFilms >= 0;
    }

    //проверка, чтобы айдишники были больше нуля и чтобы нельзя было сохранять два одинаковых
    private boolean checkId(FilmItem someFilm) {
        if (someFilm.getId() <= 0) {
            return false;
        }
        for (FilmItem checkFilmSlot : films) {
            if (someFilm.getId() == checkFilmSlot.getId()) {
                return false;
            }
        }
        return true;
    }

    //метод для сохранения фильмов
    public void save(FilmItem oneNewFilm) {
        FilmItem[] temporary = new FilmItem[films.length + 1];

        for (int i = 0; i < films.length; i++) {
            temporary[i] = films[i];
        }
        temporary[temporary.length - 1] = oneNewFilm;

        if (checkId(oneNewFilm)) {
            films = temporary;
        }
    }

    //метод для удаления фильмов по айди
    public void removeById(int id) {
        FilmItem[] temporary = new FilmItem[films.length - 1];
        int copyToIndex = 0;
        for (FilmItem someFilm : films) {
            if (someFilm.getId() != id) {
                temporary[copyToIndex] = someFilm;
                copyToIndex++;
            }
        }
        films = temporary;
    }

    //метод, возвращающий последние (10 по-умолчанию) фильмов
    public FilmItem[] findLast() {
        int copyToIndex = 0;
        FilmItem tmpFilmRepo;
        for (int lastFilmIndex = films.length; lastFilmIndex >= 1 && copyToIndex < lastFilmsToShow; lastFilmIndex--) {
            tmpFilmRepo = films[lastFilmIndex - 1];
            repoForLastFilms[copyToIndex] = tmpFilmRepo;
            copyToIndex++;
        }
        removeIfNull(repoForLastFilms);
        return repoForLastFilms;
    }

    //метод меняет длину массива repoForLastFilms, чтобы findLast работала после setLastFilmsToShow
    private void removeIfNull(FilmItem[] someRepo) {
        FilmItem[] tmpRepo;
        int tmpRepoLength = 0;
        for (FilmItem someFilm : someRepo) {
            if (someFilm != null) {
                tmpRepoLength++;
            }
        }
        tmpRepo = new FilmItem[tmpRepoLength];
        int copyToIndex = 0;
        for (FilmItem someFilm : someRepo) {
            if (copyToIndex < tmpRepoLength) {
                tmpRepo[copyToIndex] = someFilm;
                copyToIndex++;
            }
        }
        repoForLastFilms = tmpRepo;
    }

    //метод показывает все сохраненные фильмы
    public FilmItem[] findAll() {
        return films;
    }
}
