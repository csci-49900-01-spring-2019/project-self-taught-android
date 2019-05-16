package com.example.self_taught;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FileSave extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filesave);

        final Bitmap file = getIntent().getParcelableExtra("imageToSave");

        Button cancel = (Button) findViewById(R.id.FileSaveCancelBtn);
        Button submit = (Button) findViewById(R.id.FileSaveSubmitBtn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(getApplicationContext(), Home.class);
                startActivity(toHome);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFields())
                {
                    submitFile(file);
                    Intent toHome = new Intent(getApplicationContext(), Home.class);
                    startActivity(toHome);
                }
            }
        });
    }

    public boolean checkFields()
    {
        boolean checked = false;
        TextView name = (TextView) findViewById(R.id.FileSaveNameText);
        TextView desc = (TextView) findViewById(R.id.FileSaveDescriptionText);
        TextView tags = (TextView) findViewById(R.id.FileSaveTagsText);

        String nameStr = name.getText().toString();
        String descStr = desc.getText().toString();
        String tagsStr = tags.getText().toString();

        if(!(nameStr.isEmpty() || descStr.isEmpty() || tagsStr.isEmpty()))
            checked = true;
        return checked;
    }

    public void submitFile(Bitmap file)
    {
        TextView name = (TextView) findViewById(R.id.FileSaveNameText);
        TextView desc = (TextView) findViewById(R.id.FileSaveDescriptionText);
        TextView tags = (TextView) findViewById(R.id.FileSaveTagsText);

        String nameStr = name.getText().toString();
        String descStr = desc.getText().toString();
        String tagsStr = tags.getText().toString();

        String toSend[] = new String[7];
        toSend[0] = "www.api.selftaughtapp.com/v1/notebooks/:notebook/notes";
        toSend[1] = "POST";
        toSend[2] = "4";
        toSend[3] = "name=" + nameStr;
        toSend[4] = "description=" + descStr;
        toSend[5] = "file=" + file.toString();
        toSend[6] = "tags=" + tagsStr;

        GetUrlContentTask fileSave = new GetUrlContentTask();
        fileSave.setContext(this);
        fileSave.execute(toSend);
    }
}
