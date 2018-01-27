package com.example.mynotes.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mynotes.R;
import com.example.mynotes.addeditnote.AddEditNoteActivity;
import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesRepository;
import com.example.mynotes.data.source.local.AppDatabase;
import com.example.mynotes.data.source.local.NotesLocalDataSource;
import com.example.mynotes.data.source.remote.NotesRemoteDataSource;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mateus on 23/01/18.
 */

public class NotesFragment extends Fragment {

    public static final String TAG = NotesFragment.class.getSimpleName();

    @BindView(R.id.notesList)
    RecyclerView notesList;

    private CompositeDisposable disposables = new CompositeDisposable();
    private NotesAdapter adapter;
    private NotesViewModel notesViewModel;

    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        ButterKnife.bind(this, view);

        adapter = new NotesAdapter(note -> {
            //TODO handle click on list item
        });
        notesList.setAdapter(adapter);
        notesList.setLayoutManager(new LinearLayoutManager(getContext()));

        notesViewModel = new NotesViewModel(
                NotesRepository.getInstance(NotesLocalDataSource.getInstance(
                        AppDatabase.getInstance(
                                getActivity().getApplicationContext()).getNotesDao()),
                        NotesRemoteDataSource.getInstance()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        bindViewModel();
    }

    @Override
    public void onPause() {
        unbindViewModel();
        super.onPause();
    }

    public void showNotes(List<Note> notes) {
        adapter.setData(notes);
    }

    public void showAddEditNotePage(int noteId) {
        Intent intent = new Intent(getContext(), AddEditNoteActivity.class);
        intent.putExtra(AddEditNoteActivity.EXTRA_NOTE_ID, noteId);
        startActivity(intent);
    }

    @OnClick(R.id.floatingActionButton)
    public void onClickAddNote() {
        //TODO add note
    }

    private void bindViewModel() {
        disposables.add(notesViewModel.getNotes().subscribeOn(Schedulers.computation()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(notes -> adapter.setData(notes)));
        disposables.add(notesViewModel.getErrorMessage().subscribeOn(
                Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                this::showError));
    }

    public void showError(@StringRes int resourceId) {
        Toast.makeText(getActivity(), resourceId, Toast.LENGTH_LONG).show();
    }

    private void unbindViewModel() {
        disposables.clear();
    }

}
