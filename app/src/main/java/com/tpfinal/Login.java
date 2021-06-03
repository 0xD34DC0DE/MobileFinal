package com.tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView errorMessage;
    EditText SSNEditTExt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        errorMessage = findViewById(R.id.errorMessageTextView);
        SSNEditTExt = findViewById(R.id.SSNEditText);

        errorMessage.setText("");

        //TODO REMOVE TEST SSN
        SSNEditTExt.setText("708193383");

        setupSSNEditTextListener(SSNEditTExt);
    }

    private void setupSSNEditTextListener(EditText editTextView) {
        editTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!errorMessage.getText().toString().equals(""))
                    errorMessage.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    public void OnLoginClick(View view) {
        String editTextContent = SSNEditTExt.getText().toString();

        if(editTextContent.length() != 9) {
            errorMessage.setText("SSN should be 9 digits");
            return;
        }

        UserService.registerOrLogin(editTextContent, new UserService.RegisterOrLoginCallback() {
            @Override
            public void onDataReceived(UserData userData) {
                if(userData == null) {
                    errorMessage.setText("An error occurred, please try again later");
                    return;
                }

                if(userData.getErrorMessage() != null) {
                    errorMessage.setText(userData.getErrorMessage());
                    return;
                }

                Intent vaccinDisplayIntent = new Intent(Login.this, VaccinDisplay.class);
                startActivity(vaccinDisplayIntent);
            }
        });

//        UserService.getMinisterData(editTextContent, new UserService.MinisterDataCallback() {
//            @Override
//            public void onDataReceived(MinisterData ministerData) {
//
//                if(ministerData.getErrorMessage() != null) {
//                    errorMessage.setText(ministerData.getErrorMessage());
//                    return;
//                }
//
//                CategoryAge ageCategory = CategoryAge.construct(ministerData.getAge());
//
//                if(ageCategory == CategoryAge.ADULTE || ageCategory == CategoryAge.SENIOR) {
//                    if(ministerData.getTypePermis() != null) {
//                        Intent permitDisplayIntent = new Intent(Login.this, VaccinDisplay.class);
//                        permitDisplayIntent.putExtra("SSN", ministerData.getNumeroAssuranceSocial());
//                        startActivity(permitDisplayIntent);
//                    } else {
//                        errorMessage.setText("No permit associated with this SSN");
//                    }
//                } else {
//                    errorMessage.setText("Age category not supported (Only adults)");
//                }
//            }
//        });
    }
}