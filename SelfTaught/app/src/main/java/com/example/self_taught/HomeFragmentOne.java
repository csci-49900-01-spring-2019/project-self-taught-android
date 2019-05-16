package com.example.self_taught;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragmentOne extends Fragment {
    View view;
    int x;
    int y;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homemenufragone, container, false);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        x = size.x;
        y = size.y;

        TextView notes = (TextView) view.findViewById(R.id.HomeMenuNotes);
        TextView notebooks = (TextView) view.findViewById(R.id.HomeMenuNotebooks);
        TextView questions = (TextView) view.findViewById(R.id.HomeMenuQuestions);
        TextView tests = (TextView) view.findViewById(R.id.HomeMenuTests);
        TextView friends = (TextView) view.findViewById(R.id.HomeMenuFriends);

        FloatingActionButton toPicture = (FloatingActionButton) view.findViewById(R.id.HomeMenuTakePicture);

        ImageView imgNotes = (ImageView) view.findViewById(R.id.HomeMenuNoteImage);

        notes.setWidth(x/2);
        questions.setWidth(x/2);
        friends.setWidth(x/2);

        imgNotes.setMaxWidth(x/3);

        notes.setHeight(y/3);
        notebooks.setHeight(y/3);
        questions.setHeight(y/3);
        tests.setHeight(y/3);
        friends.setHeight(y/3);

        toPicture.setMaxWidth(x/6);
        toPicture.setMaxHeight(x/6);

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notes = new  Intent(getActivity().getApplicationContext(), MyNotes.class);
                startActivity(notes);
            }
        });

        notebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //swapFragment(new MyNotebooks());
                Intent notebooks = new  Intent(getActivity().getApplicationContext(), MyNotebooks.class);
                startActivity(notebooks);
            }
        });

        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questions = new Intent(getActivity().getApplicationContext(), MyQuestions.class);
                startActivity(questions);
            }
        });

        tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tests = new Intent(getActivity().getApplicationContext(), MyTests.class);
                startActivity(tests);
            }
        });

        toPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture = new Intent(getActivity().getApplicationContext(), TakePicture.class);
                startActivity(picture);
            }
        });

        return view;
    }

    public void swapFragment(Fragment frag)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.HomeMenuFragLayout, frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
