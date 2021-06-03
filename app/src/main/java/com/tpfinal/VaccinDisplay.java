package com.tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Locale;

public class VaccinDisplay extends AppCompatActivity {

    ImageView qrCodeImageView;

    TextView detentorTextView;
    TextView ageCategoryTextView;
    TextView passportTypeTextView;
    TextView expirationDateTextView;
    TextView expirationDateLabelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccin_display);

        qrCodeImageView = findViewById(R.id.qrCodeImageView);

        detentorTextView = findViewById(R.id.detentorTextView);
        ageCategoryTextView = findViewById(R.id.ageCategoryTextView);
        passportTypeTextView = findViewById(R.id.passportTypeTextView);
        expirationDateTextView = findViewById(R.id.expirationDateTextView);
        expirationDateLabelTextView = findViewById(R.id.expirationDateLabelTextView);

        PermitService.getPermitData(UserService.getCurrentUser().getNumeroAssuranceSocial(), this::OnPermitDataReceived);
    }

    private void OnPermitDataReceived(PermitData permitData) {
        System.out.println(permitData.getCodeQRBase64());

        detentorTextView.setText(String.format("%s %s", permitData.getPrenom(), permitData.getNom()));
        ageCategoryTextView.setText(permitData.getCategorieAge().getCategory());
        passportTypeTextView.setText(permitData.getTypePermis().toString());

        if(permitData.getTypePermis() == PermisType.TEST) {
            expirationDateTextView.setVisibility(View.VISIBLE);
            expirationDateLabelTextView.setVisibility(View.VISIBLE);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA_FRENCH);
            expirationDateTextView.setText(dateFormat.format(permitData.getDateExpiration()));
        } else {
            expirationDateTextView.setVisibility(View.INVISIBLE);
            expirationDateLabelTextView.setVisibility(View.INVISIBLE);
        }

        displayQRImage(permitData.getCodeQRBase64());
    }

    private void displayQRImage(String base64) {
        byte[] decodeBytes = Base64.getDecoder().decode(base64);
        Bitmap image = BitmapFactory.decodeByteArray(decodeBytes, 0, decodeBytes.length);
        qrCodeImageView.setImageBitmap(Bitmap.createScaledBitmap(image, 256, 256, false));
    }
}