package com.example.self_taught;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Dropdown for genders in the sign up page
        Spinner genderSpinner = (Spinner) findViewById(R.id.SignUpGenderSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genderSpinner.setAdapter(genderAdapter);

        //Dropdown for grades in the sign up page
        Spinner gradeSpinner = (Spinner) findViewById(R.id.SignUpGradeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> gradeAdapter = ArrayAdapter.createFromResource(this, R.array.grades, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gradeSpinner.setAdapter(gradeAdapter);

        Button signUpSubmitBtn = (Button) findViewById(R.id.SignUpSubmitBtn);
        signUpSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get all the views needed for the information gathering
                TextView firstNameView = (TextView) findViewById(R.id.SignUpFirstNameInput);
                TextView lastNameView = (TextView) findViewById(R.id.SignUpLastNameInput);
                TextView emailView = (TextView) findViewById(R.id.SignUpEmailInput);
                TextView passwordView = (TextView) findViewById(R.id.SignUpPasswordInput);
                Spinner genderSpinner = (Spinner) findViewById(R.id.SignUpGenderSpinner);
                Spinner gradeSpinner = (Spinner) findViewById(R.id.SignUpGradeSpinner);

                //Save the information for sending
                String firstNameStr = firstNameView.getText().toString();
                String lastNameStr = lastNameView.getText().toString();
                String emailStr = emailView.getText().toString();
                String passwordStr = passwordView.getText().toString();
                String genderStr = genderSpinner.getSelectedItem().toString();
                String gradeStr = gradeSpinner.getSelectedItem().toString();

                //Adding information through API calls.

                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeIntent);
            }
        });
    }
}
