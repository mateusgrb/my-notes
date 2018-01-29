package com.example.mynotes.notes;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesDataSource;
import com.example.mynotes.data.source.NotesRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mateus on 29/01/18.
 */
public class NotesPresenterTest {

    private static List<Note> NOTES;

    @Mock
    private NotesContract.View view;

    @Mock
    private NotesRepository repository;

    @Captor
    private ArgumentCaptor<NotesDataSource.LoadNotesCallback> loadNotesCallbackCaptor;

    private NotesPresenter notesPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        notesPresenter = new NotesPresenter(view, repository);

        when(view.isActive()).thenReturn(true);

        NOTES = Arrays.asList(new Note("Title1", "Description1"),
                new Note("Title2", "Description2"), new Note("Title3", "Description3"));
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        notesPresenter = new NotesPresenter(view, repository);

        verify(view).setPresenter(notesPresenter);
    }

    @Test
    public void loadAllNotesFromRepositoryAndLoadIntoView() {
        notesPresenter.start();

        verify(repository).getNotes(loadNotesCallbackCaptor.capture());
        loadNotesCallbackCaptor.getValue().onNotesLoaded(NOTES);

        ArgumentCaptor<List> showNotesArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).showNotes(showNotesArgumentCaptor.capture());
        assertTrue(showNotesArgumentCaptor.getValue().size() == 3);
    }

    @Test
    public void unavailableNotes_ShowsError() {
        notesPresenter.start();

        verify(repository).getNotes(loadNotesCallbackCaptor.capture());
        loadNotesCallbackCaptor.getValue().onDataNotAvailable();

        verify(view).showLoadingNotesError();
    }

    @Test
    public void clickOnFab_ShowsAddEditNoteUi() {
        notesPresenter.addNewNote();

        verify(view).showAddEditNotePage(0);
    }

    @Test
    public void clickOnNote_ShowsAddEditNoteUi() {
        Note requestedNote = new Note(10, "Protocol Number", "23165712");

        notesPresenter.onNoteClicked(requestedNote);

        verify(view).showAddEditNotePage(10);
    }
}