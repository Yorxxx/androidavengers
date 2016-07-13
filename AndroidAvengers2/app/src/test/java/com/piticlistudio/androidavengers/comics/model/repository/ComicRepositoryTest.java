package com.piticlistudio.androidavengers.comics.model.repository;

import com.piticlistudio.androidavengers.RxSchedulersOverride;
import com.piticlistudio.androidavengers.comics.model.MarvelComicsAPIResponseFactory;
import com.piticlistudio.androidavengers.comics.model.entity.Comic;
import com.piticlistudio.androidavengers.comics.model.entity.MarvelComicsAPIResponse;
import com.piticlistudio.androidavengers.comics.model.entity.Result;
import com.piticlistudio.androidavengers.dependencies.IMarvelService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Test cases for ComicRepository
 * TODO Dagger injection
 * Created by jorge.garcia on 13/07/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ComicRepositoryTest {

    @Rule
    public final RxSchedulersOverride mOverrideSchedulersRule = new RxSchedulersOverride();

//    @Rule
//    public JUnitComicsMockRule rule = new JUnitComicsMockRule();

    @Mock
    IMarvelService service;

    private ComicRepository repository;

    @Captor
    ArgumentCaptor<String> characterKeyCaptor;

    @Captor
    ArgumentCaptor<String> publicKeyArgumentCaptor;

    @Captor
    ArgumentCaptor<Long> timestampKeyArgumentCaptor;

    @Captor
    ArgumentCaptor<String> hashKeyArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository = new ComicRepository(service);
    }

    @Test
    public void shouldGenerateHash() {

        String publicKey = "1234";
        String privateKey = "abcd";
        long timestamp = 1;

        // Act
        String result = repository.md5(timestamp+privateKey+publicKey);

        // Assert
        assertEquals("ffd275c5130566a2916217b101f26150", result);
    }


    @Test
    public void generatesAComicEntityFromResponseModel() {

        Result apiData = MarvelComicsAPIResponseFactory.generate(1);

        // Act
        Comic data = repository.fromResponseAPIToModel(apiData);

        // Assert
        assertNotNull(data);
        assertEquals(1, data.getId());
        assertEquals(apiData.description, data.getDescription());
        assertEquals(apiData.pageCount.intValue(), data.getPageCount());
        assertEquals(apiData.images.size(), data.getImagesUri().size());
        assertTrue(data.getImagesUri().contains("image1.jpg"));
        assertTrue(data.getImagesUri().contains("image2.jpg"));
        assertEquals(apiData.title, data.getTitle());
        assertEquals(apiData.thumbnail.path+"."+apiData.thumbnail.extension, data.getThumbnailUri());
        assertEquals(apiData.urls.get(0).url, data.getDetailUrl());
        assertEquals(apiData.urls.get(1).url, data.getPurchaseUrl());
    }

    @Test
    public void generatesNullComicEntityWithInvalidResponseModel() {

        // Act
        Comic data = repository.fromResponseAPIToModel(null);

        // Assert
        assertNull(data);
    }

    @Test
    public void generatesNullComicEntityWithResponseModelWithoutId() {

        Result apiData = MarvelComicsAPIResponseFactory.generate(1);
        apiData.id = null;

        // Act
        Comic data = repository.fromResponseAPIToModel(null);

        // Assert
        assertNull(data);
    }

    @Test
    public void emitsNextEventsWhenFetchingComicsByCharacter() {

        MarvelComicsAPIResponse response = MarvelComicsAPIResponseFactory.generateResponse(8);

        doReturn(Observable.just(response))
                .when(service)
                .getComicsForCharacter(anyString(), anyString(), anyLong(), anyString());

        // Act
        TestSubscriber<List<Comic>> subscriber = new TestSubscriber<>();
        repository.fetchForCharacter("123").subscribe(subscriber);

        // Assert
        verify(service).getComicsForCharacter(characterKeyCaptor.capture(), publicKeyArgumentCaptor.capture(), timestampKeyArgumentCaptor
                .capture(), hashKeyArgumentCaptor.capture());
        assertEquals("123", characterKeyCaptor.getValue());
        assertEquals(ComicRepository.PUBLIC_KEY, publicKeyArgumentCaptor.getValue());
        assertFalse(timestampKeyArgumentCaptor.getValue() == 0);
        assertNotNull(hashKeyArgumentCaptor.getValue());
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
        List<List<Comic>> values = subscriber.getOnNextEvents();
        assertEquals(1, values.size());
        List<Comic> responseValues = values.get(0);
        assertEquals(8, responseValues.size());
        for (int i = 0; i < responseValues.size(); i++) {
            Comic data = responseValues.get(i);
            assertNotNull(data);
        }
    }


    @Test
    public void emitsErrorsWhenFetchingComicsByCharacter() {

        Exception error = new Exception("bla");
        doReturn(Observable.error(error))
                .when(service)
                .getComicsForCharacter(anyString(), anyString(), anyLong(), anyString());

        // Act
        TestSubscriber<List<Comic>> subscriber = new TestSubscriber<>();
        repository.fetchForCharacter("123").subscribe(subscriber);

        // Assert
        verify(service).getComicsForCharacter(characterKeyCaptor.capture(), publicKeyArgumentCaptor.capture(), timestampKeyArgumentCaptor
                .capture(), hashKeyArgumentCaptor.capture());
        assertEquals("123", characterKeyCaptor.getValue());
        assertEquals(ComicRepository.PUBLIC_KEY, publicKeyArgumentCaptor.getValue());
        assertFalse(timestampKeyArgumentCaptor.getValue() == 0);
        assertNotNull(hashKeyArgumentCaptor.getValue());
        subscriber.assertError(error);
    }
}
