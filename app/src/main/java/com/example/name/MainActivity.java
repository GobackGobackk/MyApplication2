package com.example.name;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.name.R;
import com.example.name.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    @BindView(R.id.editTextName)
    EditText name;
    @BindView(R.id.editTextEmail)
    EditText email;
    @BindView(R.id.editTextPassword)
    EditText password;
    @BindView(R.id.RegisterButton)
    Button button;
    @BindView(R.id.radioGroup)
    RadioGroup genderRadioGroup;
    @BindView(R.id.textview)
    TextView textView3;
    String email_s;
    String password_s;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/userProfiles");
//        String[] City = new String[]{"臺北市","新北市","基隆市","宜蘭縣",
//                "桃園市","新竹市","新竹縣","苗栗縣",
//                "臺中市","彰化縣","南投縣","雲林縣",
//                "嘉義市","嘉義縣","臺南市","高雄市", "屏東縣",
//                "花蓮縣","臺東縣","澎湖縣","金門縣","連江縣"};
//        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_dropdown_item, City);
//        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapterCity);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @OnClick(R.id.RegisterButton)
    public void submit(View view) {
        email_s = email.getText().toString();
        password_s = password.getText().toString();
        int checkedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(checkedRadioButtonId);
        user = new User(name.getText().toString(), email_s, radioButton.getText().toString());
        mAuth.createUserWithEmailAndPassword(email_s, password_s)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signIn:success");
                            FirebaseUser user1 = mAuth.getCurrentUser();
                            user.setId(user1.getUid());
                            myRef.child(user.getId()).setValue(user);
                            updateUItoMental(user1);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signIn:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUItoMental(null);
                        }
                    }
                });
    }
    @OnClick(R.id.textview)//註冊到登入
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void updateUItoMental(FirebaseUser user) {
        if (user != null) {
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("UserId", user.getUid());
//            editor.apply();
            Intent intent = new Intent(MainActivity.this, MainActivity23.class);
            Bundle bundle = new Bundle();
            bundle.putString("UserId", user.getUid());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }
    public void updateUI(FirebaseUser user) {
        if (user != null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("UserId", user.getUid());
            editor.apply();
            Intent intent = new Intent(MainActivity.this, MainActivity6.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("UserId", user.getUid());
//            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }
    @OnClick(R.id.login)
    public void zxcv(View view){
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
