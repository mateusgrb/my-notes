package com.example.mynotes.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mateus on 23/01/18.
 */

public class NotesFragment extends Fragment implements NotesContract.View {

    public static final String TAG = NotesFragment.class.getSimpleName();

    @BindView(R.id.notesList)
    RecyclerView notesList;

    private NotesContract.Presenter presenter;
    private NotesAdapter adapter;

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

        adapter = new NotesAdapter(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onClick(Note note) {
                presenter.onNoteClicked(note);
            }
        });
        notesList.setAdapter(adapter);
        notesList.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(NotesContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNotes(List<Note> notes) {
        adapter.setData(notes);
    }

    @Override
    public void showLoadingNotesError() {
        Toast.makeText(getActivity(), R.string.error_loading_notes, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAddEditNotePage(int noteId) {
        Intent intent = new Intent(getContext(), AddEditNoteActivity.class);
        intent.putExtra(AddEditNoteActivity.EXTRA_NOTE_ID, noteId);
        startActivity(intent);
    }

    @OnClick(R.id.floatingActionButton)
    public void onClickAddNote() {
        presenter.addNewNote();
    }
}
