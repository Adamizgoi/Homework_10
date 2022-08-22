package ru.netology.homeworks.filmmanager;

public class FilmRepo {
    private FilmItem[] films = new FilmItem[0];

    public FilmItem[] findAll() {
        return films;
    }

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

    //доп метод для метода save - проверка корректности id
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

    public FilmItem findById(int id) {
        for (FilmItem someFilm : films) {
            if (id == someFilm.getId()) {
                return someFilm;
            }
        }
        return null;
    }

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

    public void removeAll() {
        FilmItem[] tmp = new FilmItem[0];
        films = tmp;
    }
}
