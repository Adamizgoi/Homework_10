package ru.netology.homeworks.filmmanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class FilmManagerTest {

    FilmItem film1 = new FilmItem(1, "Bloodshot", "Action", "https://kanobu.ru/");
    FilmItem film2 = new FilmItem(22, "Forward", "Cartoon", "https://file.liga.net/companies/google-3305320");
    FilmItem film3 = new FilmItem(13, "Don't look back", "Horror", "https://ru.wikipedia.org/wiki/Google_%D0%A4%D0%BE%D1%82%D0%BE");
    FilmItem film4 = new FilmItem(41, "Be mine", "Melodrama", "https://www.google.com/intl/ru/chrome/");
    FilmItem film5 = new FilmItem(5, "Eleven friends", "Thriller", "https://itc.ua/news/google-uskoryaet-chrome-obeshhaya-10-k-skorosti-zagruzki-vkladok/");
    FilmItem film6 = new FilmItem(61, "Last time", "Drama", "https://www.ixbt.com/news/2021/03/23/google-polomalsja-polzovateli-so-vsego-mira-soobshajut-o-sbojah.html");
    FilmItem film7 = new FilmItem(17, "Little toys", "Documentary", "https://www.tadviser.ru/index.php/%D0%9F%D1%80%D0%BE%D0%B4%D1%83%D0%BA%D1%82:Google_Chrome");
    FilmItem film8 = new FilmItem(18, "Run, run", "Art house", "https://www.ixbt.com/news/2021/12/14/google-vypustila-srochnoe-obnovlenie-dlja-chrome.html");
    FilmItem film9 = new FilmItem(90, "Blood theory", "Anime", "https://www.ferra.ru/lifehack/techlife/google-image-search-highres.htm");
    FilmItem film10 = new FilmItem(10, "University", "Science film", "https://www.google.com/intl/ru/chrome/");
    FilmItem film11 = new FilmItem(101, "Lost", "Serial", "https://www.ixbt.com/news/2020/12/06/bolshoe-obnovlenie-google-chrome-uzhe-mozhno-oprobovat.html");
    FilmItem film12error = new FilmItem(1, "Blabla", "Horror", "https//...");
    FilmItem film13 = new FilmItem(333, "Bloodshot", "Cartoon", "https//...");
    FilmItem film14 = new FilmItem(222, "Picnic", "Horror", "http//...");
    FilmItem film15 = new FilmItem(1105, "Prist", "Blabla", "https://kanobu.ru/");
    FilmItem film16error = new FilmItem(0, "First", "Horror", "https://");
    FilmItem film17error = new FilmItem(-1, "Bloodshot 2", "Action", "https://...");

    FilmRepo repo = new FilmRepo();
    FilmManager manager = new FilmManager(repo);

    FilmRepo mockRepo = Mockito.mock(FilmRepo.class);
    FilmManager mockManager = new FilmManager(mockRepo);

    @Test
    public void shouldBeOpportunityToAddNewFilms() {
        manager.save(film1);

        FilmItem[] expected = {film1};
        FilmItem[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    //пара тест-кейсов должна быть с макированием
    @Test
    public void shouldNotBeOpportunityToSaveFilmWithZeroId() {
        manager.save(film16error);

        FilmItem[] expected = {};
        FilmItem[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotBeOpportunityToSaveTwoEqualId() {
        manager.save(film1);
        manager.save(film12error);

        FilmItem[] expected = {film1};
        FilmItem[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotBeOpportunityToSaveIdLessThenZero() {
        manager.save(film17error);

        FilmItem[] expected = {};
        FilmItem[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeOpportunityToSaveSameFilmNames() {
        manager.save(film13);
        manager.save(film1);

        FilmItem[] expected = {film13, film1};
        FilmItem[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeOpportunityToSaveSameFilmGenres() {
        manager.save(film14);
        manager.save(film3);

        FilmItem[] expected = {film14, film3};
        FilmItem[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeOpportunityToSaveSameFilmImages() {
        manager.save(film15);
        manager.save(film1);

        FilmItem[] expected = {film15, film1};
        FilmItem[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeOpportunityToDeleteFilm() {
        manager.save(film1);
        manager.save(film2);
        manager.save(film3);
        manager.removeById(film2.getId());

        FilmItem[] expected = {film1, film3};
        FilmItem[] actual = manager.findAll();

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

    // тут тест должен показывать, если фильмов нет

    @Test
    public void shouldShowLastTenFilmsIfArrayBoundaryNineFilms() {
        manager.save(film1);
        manager.save(film2);
        manager.save(film3);
        manager.save(film4);
        manager.save(film5);
        manager.save(film6);
        manager.save(film7);
        manager.save(film8);
        manager.save(film9);

        FilmItem[] expected = {film9, film8, film7, film6, film5, film4, film3, film2, film1};
        FilmItem[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    //тест-кейс с использованием Mockito
    @Test
    public void shouldShowLastTenFilmsIfArrayConsistTenFilms() {
        FilmItem[] films = {film1, film2, film3, film4, film5, film6, film7, film8, film9, film10};
        doReturn(films).when(mockRepo).findAll();

        FilmItem[] expected = {film10, film9, film8, film7, film6, film5, film4, film3, film2, film1};
        FilmItem[] actual = mockManager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastTenFilmsIfArrayConsistBoundaryElevenFilms() {
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
    public void shouldShowLastTenFilmsIfArrayConsistMoreThenTenFilms() {
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
        manager.save(film13);
        manager.save(film14);

        FilmItem[] expected = {film14, film13, film11, film10, film9, film8, film7, film6, film5, film4};
        FilmItem[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastTenFilmsIfArrayConsistLessThenTenFilms() {
        manager.save(film1);
        manager.save(film2);
        manager.save(film3);
        manager.save(film4);
        manager.save(film5);
        manager.save(film6);

        FilmItem[] expected = {film6, film5, film4, film3, film2, film1};
        FilmItem[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void functionLastTenFilmMustWorkEvenIfArrayConsistBoundaryOneFilm() {
        manager.save(film1);

        FilmItem[] expected = {film1};
        FilmItem[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void functionLastTenShouldNotCrashTestsIfArrayConsistNoOneFilm() {
        FilmItem[] expected = {};
        FilmItem[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeOpportunityToSetZeroNumberOfLastFilmsToShow() {
        manager.setLastFilmsToShow(0);

        int expected = 0;
        int actual = manager.getLastFilmsToShow();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotCrushSystemIfSetZeroNumbersOfShowLastFilms() {
        manager.setLastFilmsToShow(0);

        manager.save(film1);
        manager.save(film2);
        manager.save(film3);
        manager.save(film4);


        FilmItem[] expected = {};
        FilmItem[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotCrushSystemIfSetZeroNumbersOfShowLastFilmsAndArrayIsEmpty() {
        manager.setLastFilmsToShow(0);

        FilmItem[] expected = {};
        FilmItem[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotBeOpportunityToSetLessZeroNumberOfLastFilmsToShow() {
        manager.setLastFilmsToShow(-1);

        int expected = 10;
        int actual = manager.getLastFilmsToShow();

        Assertions.assertEquals(expected, actual);
    }
}
