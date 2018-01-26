package com.example.mynotes.addeditnote;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mynotes.R;
import com.example.mynotes.data.source.NotesRepository;
import com.example.mynotes.data.source.local.AppDatabase;
import com.example.mynotes.data.source.local.NotesLocalDataSource;
import com.example.mynotes.data.source.remote.NotesRemoteDataSource;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE_ID = "NOTE_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int noteId = getIntent().getIntExtra(EXTRA_NOTE_ID, 0);
        AddEditNoteFragment fragment = AddEditNoteFragment.newInstance(noteId);
        new AddEditNotePresenter(fragment,
                NotesRepository.getInstance(NotesLocalDataSource.getInstance(
                        AppDatabase.getInstance(getApplicationContext()).getNotesDao()),
                        NotesRemoteDataSource.getInstance()), noteId);

        getSupportFragmentManager().beginTransaction().add(R.id.contentFrame, fragment,
                AddEditNoteFragment.TAG).commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
