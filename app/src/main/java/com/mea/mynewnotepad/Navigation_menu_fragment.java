package com.mea.mynewnotepad;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Navigation_menu_fragment extends Fragment {

    private boolean isLandscape;
    private StyleFormat[] arrStyleformatList;
    private StyleFormat currentStyleformat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listOfNotes(view);
    }

    private void listOfNotes (View view) {
        arrStyleformatList = new StyleFormat[]{
                new StyleFormat(getString(R.string.one_title), getString(R.string.one_description), Calendar.getInstance()),
                new StyleFormat(getString(R.string.two_title), getString(R.string.two_description), Calendar.getInstance()),
                new StyleFormat(getString(R.string.three_title), getString(R.string.three_description), Calendar.getInstance()),
        };



        for (StyleFormat note : arrStyleformatList) {
            Context context = getContext();
            if (context != null) {
                LinearLayout linearView = (LinearLayout) view;
                TextView firstTextView = new TextView(context);
                TextView secondTextView = new TextView(context);
                firstTextView.setText(note.getTitle());

                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy", Locale.getDefault());
                secondTextView.setText(formatter.format(note.getCreationDate().getTime()));

                linearView.addView(firstTextView);
                linearView.addView(secondTextView);
                firstTextView.setPadding(0, 22, 0, 0);
                firstTextView.setOnClickListener(v -> {
                    currentStyleformat = note;
                    showNote(currentStyleformat);
                });
            }
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(NoteFragment.CURRENT_NOTE, currentStyleformat);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentStyleformat = savedInstanceState.getParcelable(NoteFragment.CURRENT_NOTE);
        } else {
            currentStyleformat = arrStyleformatList[0];
        }
        if (isLandscape) {
            showLandNote(currentStyleformat);
        }
    }
    private void showNote(StyleFormat currentNote) {
        if (isLandscape) {
            showLandNote(currentNote);
        } else {
            showPortNote(currentNote);
        }
    }
    private void showLandNote(StyleFormat currentNote) {
        NoteFragment fragment = NoteFragment.newInstance(currentNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.note_layout, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    private void showPortNote(StyleFormat currentNote) {
        Intent intent = new Intent(getActivity(), NoteBook.class);
        intent.putExtra(NoteFragment.CURRENT_NOTE, currentNote);
        startActivity(intent);
    }


}