package com.piticlistudio.androidavengers.comics.ui.list.presenter;

import com.piticlistudio.androidavengers.RxSchedulersOverride;
import com.piticlistudio.androidavengers.comics.model.entity.Comic;
import com.piticlistudio.androidavengers.comics.model.repository.ComicRepository;
import com.piticlistudio.androidavengers.comics.ui.list.view.IComicsListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Test cases for ComicsListPresenter
 * Created by jorge.garcia on 13/07/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ComicsListPresenterTest {

    @Rule
    public final RxSchedulersOverride mOverrideSchedulersRule = new RxSchedulersOverride();

//    @Rule
//    public JUnitComicsMockRule rule = new JUnitComicsMockRule();

    @Mock
    ComicRepository repository;

    @Mock
    IComicsListView view;

    private ComicsListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ComicsListPresenter(repository);
        presenter.attachView(view);
    }

    @Test
    public void showsErrorWhenFailedFetchingFromRepository() {

        Exception error = new Exception("bla");
        doReturn(Observable.error(error))
                .when(repository)
                .fetchForCharacter(anyString());

        // Act
        presenter.loadData("123", false);

        // Assert
        verify(view).showLoading(false);
        verify(repository).fetchForCharacter("123");
        verify(view).showError(error, false);
        verify(view, never()).setData(anyList());
        verify(view, never()).showContent();
    }

    @Test
    public void emitsDataFromRepository() {

        List<Comic> data = new ArrayList<>();
        doReturn(Observable.just(data))
                .when(repository)
                .fetchForCharacter("123");

        // Act
        presenter.loadData("123", true);

        // Assert
        verify(view).showLoading(true);
        verify(repository).fetchForCharacter("123");
        verify(view, never()).showError(any(), anyBoolean());
        verify(view).setData(data);
        verify(view).showContent();
    }
}
