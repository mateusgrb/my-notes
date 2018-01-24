package com.example.mynotes.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        ButterKnife.bind(this, view);

        adapter = new NotesAdapter();
        notesList.setAdapter(adapter);
        notesList.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void setPresenter(NotesContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
