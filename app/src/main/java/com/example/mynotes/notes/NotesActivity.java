package com.example.mynotes.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mynotes.R;
import com.example.mynotes.data.source.NotesRepository;
import com.example.mynotes.data.source.local.AppDatabase;
import com.example.mynotes.data.source.local.NotesLocalDataSource;
import com.example.mynotes.data.source.remote.NotesRemoteDataSource;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        NotesFragment fragment =
                (NotesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = NotesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame, fragment,
                    NotesFragment.TAG).commit();
        }
        new NotesPresenter(fragment, NotesRepository.getInstance(NotesLocalDataSource.getInstance(
                AppDatabase.getInstance(getApplicationContext()).getNotesDao()),
                NotesRemoteDataSource.getInstance()));
    }
}
