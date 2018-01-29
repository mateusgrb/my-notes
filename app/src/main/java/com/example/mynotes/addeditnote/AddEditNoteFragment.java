package com.example.mynotes.addeditnote;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.R;
import com.example.mynotes.data.Note;
import com.example.mynotes.data.source.NotesRepository;
import com.example.mynotes.data.source.local.AppDatabase;
import com.example.mynotes.data.source.local.NotesLocalDataSource;
import com.example.mynotes.data.source.remote.NotesRemoteDataSource;
import com.example.mynotes.util.providers.Navigator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEditNoteFragment extends Fragment {

    public static final String TAG = AddEditNoteFragment.class.getSimpleName();
    private static final String ARGUMENT_NOTE_ID = "NOTE_ID";

    @BindView(R.id.titleEditText)
    EditText titleEditText;

    @BindView(R.id.descriptionEditText)
    EditText descriptionEditText;

    private boolean showDeleteButton = true;

    private AddEditNoteViewModel viewModel;
    private CompositeDisposable disposables = new CompositeDisposable();

    public static AddEditNoteFragment newInstance(int noteId) {
        AddEditNoteFragment fragment = new AddEditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_NOTE_ID, noteId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_note, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        viewModel = new AddEditNoteViewModel(
                NotesRepository.getInstance(NotesLocalDataSource.getInstance(
                        AppDatabase.getInstance(
                                getActivity().getApplicationContext()).getNotesDao()),
                        NotesRemoteDataSource.getInstance()),
                new AddEditNoteNavigator(new Navigator(getActivity())), getNoteId());

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.addeditnote, menu);

        MenuItem item = menu.findItem(R.id.menu_delete);
        item.setVisible(showDeleteButton);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                viewModel.deleteNote()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.floatingActionButton)
    public void onClickSaveNote() {
        viewModel.saveNote(titleEditText.getText().toString(),
                descriptionEditText.getText().toString())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void bindViewModel() {
        disposables.add(viewModel.getShowDeleteOption()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateDeleteButtonVisibility));
        disposables.add(viewModel.getErrorMessage()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showError));
        disposables.add(viewModel.getNote()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fillForm));
    }

    private void unbindViewModel() {
        disposables.clear();
    }

    public void updateDeleteButtonVisibility(boolean showDeleteButton) {
        this.showDeleteButton = showDeleteButton;
        getActivity().invalidateOptionsMenu();
    }

    private void fillForm(Note note) {
        titleEditText.setText(note.getTitle());
        descriptionEditText.setText(note.getDescription());
    }

    public void showError(@StringRes int resourceId) {
        Toast.makeText(getActivity(), resourceId, Toast.LENGTH_LONG).show();
    }

    private int getNoteId() {
        if (getArguments() != null) {
            return getArguments().getInt(ARGUMENT_NOTE_ID);
        }
        return 0;
    }
}
