package com.example.passingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityParcelable extends AppCompatActivity implements View.OnClickListener{
    public Button btnMoveActivity;
    public Button btnMoveWithDataActivity;
    public Button btnMoveWithObject;
    Button btnDialNumber;
    Button btnMoveResult;
    TextView tvResult;

    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parcelable);
        btnMoveActivity = (Button) findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);
        btnMoveWithDataActivity = (Button) findViewById(R.id.btn_move_with_data_activity);
        btnMoveWithDataActivity.setOnClickListener(this);

        btnMoveWithObject = (Button) findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        btnDialNumber = findViewById(R.id.btn_dial_number);
        btnDialNumber.setOnClickListener(this);

        btnMoveResult = findViewById(R.id.btn_move_for_result);
        btnMoveResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(MainActivityParcelable.this, MoveActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_move_with_data_activity:
                Intent moveWithDataIntent = new Intent(MainActivityParcelable.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Immanuel");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 17);
                startActivity(moveWithDataIntent);
                break;
            case R.id.btn_move_activity_object:
                Person mPerson = new Person();
                mPerson.setName("Immanuel");
                mPerson.setAge(17);
                mPerson.setEmail("refokes@gmail.com");
                mPerson.setCity("Bandung");
                Intent moveWithObjectIntent = new Intent(MainActivityParcelable.this, MoveWithObjectActivity.class);

                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, mPerson);
                startActivity(moveWithObjectIntent);
                break;
            case R.id.btn_dial_number:
                String phoneNumber = "082129716465";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialPhoneIntent);
                break;
            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent(MainActivityParcelable.this, MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }

    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, requestCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));

            }
        }
    }
}