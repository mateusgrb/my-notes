package com.example.mynotes.addeditnote;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEditNoteFragment extends Fragment implements AddEditNoteContract.View {

    public static final String TAG = AddEditNoteFragment.class.getSimpleName();
    private static final String ARGUMENT_NOTE_ID = "NOTE_ID";

    @BindView(R.id.titleEditText)
    EditText titleEditText;

    @BindView(R.id.descriptionEditText)
    EditText descriptionEditText;

    private AddEditNoteContract.Presenter presenter;

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
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(AddEditNoteContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showNotesList() {
        getActivity().finish();
    }

    @Override
    public void fillForm(String title, String description) {
        titleEditText.setText(title);
        descriptionEditText.setText(description);
    }

    @Override
    public void showLoadingNotesError() {
        Toast.makeText(getActivity(), R.string.error_loading_note, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.floatingActionButton)
    public void onClickSaveNote() {
        presenter.saveNote(titleEditText.getText().toString(),
                descriptionEditText.getText().toString());
    }
}
