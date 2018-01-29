package com.example.mynotes.notes;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mynotes.R;
import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

/**
 * Created by mateus on 29/01/18.
 */
public class NotesViewModelTest {

    private static List<Note> NOTES;

    @Mock
    private NotesRepository repository;

    @Mock
    private NotesNavigator navigator;

    private NotesViewModel viewModel;

    private TestObserver<List<NoteItem>> notesObserver;

    private TestObserver<Integer> errorMessageObserver;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        viewModel = new NotesViewModel(repository, navigator);

        NOTES = Arrays.asList(new Note(10, "Title1", "Description1"),
                new Note("Title2", "Description2"), new Note("Title3", "Description3"));

        notesObserver = new TestObserver<>();
        errorMessageObserver = new TestObserver<>();
    }

    @Test
    public void errorMessage_emits_whenErrorRetrievingNotes() {
        when(repository.getNotes()).thenReturn(Observable.error(new RuntimeException()));
        viewModel.getErrorMessage().subscribe(errorMessageObserver);

        viewModel.getNotes().subscribe(notesObserver);

        errorMessageObserver.assertValue(R.string.error_loading_notes);
    }

    @Test
    public void getNotesModel_emits_whenNotes() {
        withNotesInRepositoryAndSubscribed(NOTES);

        notesObserver.assertValueCount(1);
        List<NoteItem> model = notesObserver.values().get(0);
        assertNoteItems(model);
    }

    @Test
    public void noteItem_tapAction_opensAddEditNote() {
        withNotesInRepositoryAndSubscribed(NOTES);

        List<NoteItem> items = notesObserver.values().get(0);
        NoteItem noteItem = items.get(0);

        Completable.fromAction(noteItem.getOnClickAction()).subscribe();

        verify(navigator).openAddEditNotePage(eq(NOTES.get(0).getId()));
    }

    @Test
    public void addNote_callsOpenAddEditNotePage() {
        viewModel.openAddNewNotePage();

        verify(navigator).openAddEditNotePage(eq(0));
    }

    private void assertNoteItems(List<NoteItem> items) {
        assertEquals(items.size(), NOTES.size());

        assertEquals(NOTES.get(0), items.get(0).getNote());
        assertEquals(NOTES.get(1), items.get(1).getNote());
        assertEquals(NOTES.get(2), items.get(2).getNote());
    }

    private void withNotesInRepositoryAndSubscribed(List<Note> notes) {
        when(repository.getNotes()).thenReturn(Observable.just(notes));
        viewModel.getNotes().subscribe(notesObserver);
    }
}