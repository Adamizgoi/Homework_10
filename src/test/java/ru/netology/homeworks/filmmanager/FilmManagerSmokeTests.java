package ru.netology.homeworks.filmmanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FilmManagerSmokeTests {

    FilmItem film1 = new FilmItem(1, "Bloodshot", "Action", "https//...");
    FilmItem film2 = new FilmItem(2, "Forward", "Cartoon", "https//...");
    FilmItem film3 = new FilmItem(3, "Don't look back", "Horror", "https//...");
    FilmItem film4 = new FilmItem(4, "Be mine", "Melodrama", "https//...");
    FilmItem film5 = new FilmItem(5, "Eleven friends", "Action", "https//...");
    FilmItem film6 = new FilmItem(6, "Last time", "Drama", "https//...");
    FilmItem film7 = new FilmItem(7, "Little toys", "Cartoon", "https//...");
    FilmItem film8 = new FilmItem(8, "Run, run", "Horror", "https//...");
    FilmItem film9 = new FilmItem(9, "Blood theory", "Horror", "https//...");
    FilmItem film10 = new FilmItem(10, "University", "Science film", "https//...");
    FilmItem film11 = new FilmItem(11, "Lost", "Serial", "https//...");

    FilmManager manager = new FilmManager();

    @Test
    public void shouldBeOpportunityToAddNewFilms() {
        manager.save(film1);

        FilmItem[] expected = {film1};
        FilmItem[] actual = manager.getFilms();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeOpportunityToDeleteFilm() {
        manager.save(film1);
        manager.save(film2);
        manager.save(film3);
        manager.removeById(film2.getId());

        FilmItem[] expected = {film1, film3};
        FilmItem[] actual = manager.getFilms();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowAllFilms() {
        manager.save(film1);
        manager.save(film2);
        manager.save(film3);
        manager.save(film4);
        manager.save(film5);
        manager.save(film6);

        FilmItem[] expected = {film1, film2, film3, film4, film5, film6};
        FilmItem[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastTenFilms() {
        manager.save(film1);
        manager.save(film2);
        manager.save(film3);
        manager.save(film4);
        manager.save(film5);
        manager.save(film6);
        manager.save(film7);
        manager.save(film8);
        manager.save(film9);
        manager.save(film10);
        manager.save(film11);

        FilmItem[] expected = {film11, film10, film9, film8, film7, film6, film5, film4, film3, film2};
        FilmItem[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeOpportunityToSetNumberOfLastFilmsToShow() {
        manager.setLastFilmsToShow(5);

        int expected = 5;
        int actual = manager.getLastFilmsToShow();

        Assertions.assertEquals(expected, actual);
    }
}
