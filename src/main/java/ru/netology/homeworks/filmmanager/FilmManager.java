package ru.netology.homeworks.filmmanager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmManager {

    private FilmRepo repo;
    //значение по-умолчанию числа фильмов для метода "покажи последние N фильмов"
    private int lastFilmsToShow = 10;

    public FilmManager(FilmRepo repo) {
        this.repo = repo;
    }

    //метод для сохранения фильмов
    public void save(FilmItem oneNewFilm) {
        if (checkId(oneNewFilm)) {
            repo.save(oneNewFilm);
        }
    }

    //проверка, чтобы айдишники были больше нуля и чтобы нельзя было сохранять два одинаковых
    private boolean checkId(FilmItem someFilm) {
        FilmItem[] films = repo.findAll();
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

    //метод для удаления фильмов по айди
    public void removeById(int id) {
        repo.removeById(id);
    }

    //метод показывает все сохраненные фильмы
    public FilmItem[] findAll() {
        return repo.findAll();
    }

    //метод, возвращающий последние (10 по-умолчанию) фильмов
    public FilmItem[] findLast() {
        FilmItem[] films = repo.findAll();
        FilmItem[] lastFilms = new FilmItem[lastFilmsToShow];

        int copyToIndex = 0;
        FilmItem tmpFilm;

        for (int lastFilmIndex = films.length; lastFilmIndex >= 1 && copyToIndex < lastFilmsToShow; lastFilmIndex--) {
            tmpFilm = films[lastFilmIndex - 1];
            lastFilms[copyToIndex] = tmpFilm;
            if (copyToIndex < lastFilmsToShow) {
                copyToIndex++;
            }
        }
        lastFilms = findLastArrayResizer(lastFilms);
        return lastFilms;
    }

    private FilmItem[] findLastArrayResizer (FilmItem[] lastFilms) {
        FilmItem[] tmpRepo;
        int tmpRepoLength = 0;
        for (FilmItem someFilm : lastFilms) {
            if (someFilm != null) {
                tmpRepoLength++;
            }
        }
        tmpRepo = new FilmItem[tmpRepoLength];
        int copyToIndex = 0;
        for (FilmItem someFilm : lastFilms) {
            if (copyToIndex < tmpRepoLength) {
                tmpRepo[copyToIndex] = someFilm;
                copyToIndex++;
            }
        }
        lastFilms = tmpRepo;
        return lastFilms;
    }

    //установка кол-ва последних фильмов, которые нужно показать методом findLast
    public void setLastFilmsToShow(int numberOfFilms) {
        if (checkLastFilmsToShow(numberOfFilms)) {
            lastFilmsToShow = numberOfFilms;
        }
    }

    //проверка, ок ли число, которое пытаются установить методом setLastFilmToShow
    private boolean checkLastFilmsToShow(int numbersOfFilms) {
        if (numbersOfFilms >= 0) {
            return true;
        }
        return false;
    }
}
