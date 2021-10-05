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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.name.R;
import com.example.name.model.FBFriends;
import com.example.name.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

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

    //fb新加的
    ImageView btnFacebook;
    private CallbackManager mCallbackManager;
    String id, FBname, FBemail, gender, fbId;
//    private DatabaseReference reference, FriendReference;

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

        //fb新加的
//        reference = database.getReference("chatRooms/FB");
//        FriendReference = database.getReference("chatRooms/FB/"+id+"/fbFriends");
        mCallbackManager = CallbackManager.Factory.create();
        btnFacebook = findViewById(R.id.btn_fbutton);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("user_gender, email"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                    }
                });
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.d("demo", object.toString());
//                        try {
//                            id = object.getString("id");
//                            FBname = object.getString("name");
//                            //FBemail = object.getString("email");
////                            FBemail = mUser.getEmail();
//                            gender =  object.getString("gender");
//                            fbId = object.getString("id");
//                            //UserHelperClass helperClass = new UserHelperClass(id, FBname,FBemail, gender, fbId);
//                            //reference.child(id).setValue(helperClass);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//        //實立化一個 Bundle
//        Bundle bundle = new Bundle();
//        //儲存資料　第一個為參數key，第二個為Value
//        bundle.putString("fields", "gender, name, id, email, first_name, last_name");
//        graphRequest.setParameters(bundle);
//        //An asynchronous call does not block the program from code execution.
//        // In other word, it is running on a separate thread in the background.
//        // Therefore, this Graph API request will be occuring in the background.
//        graphRequest.executeAsync();
//
//        //retrieve FB friends
//        GraphRequest graphRequestFriends = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(),
//                new GraphRequest.GraphJSONArrayCallback() {
//                    @Override
//                    public void onCompleted(JSONArray objects, GraphResponse response) {
//                        Log.d("Demo", objects.toString());
//                        //Log.d("demo", String.valueOf(objects.length())); objects.length()可以成功顯示該用戶有多少位朋友。
//                        ArrayList<FBFriends> fbFriends = new ArrayList<>();
//                        for (int i = 0; i < objects.length(); i++) {
//                            try {
//                                JSONObject object = objects.getJSONObject(i);
//                                String FacebookId = object.getString("id");
//                                fbFriends.add(new FBFriends(object.getString("name"), FacebookId));
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                });
//        Bundle bundleFriend = new Bundle();
//        bundleFriend.putString("fields", "id, name");
//        graphRequestFriends.setParameters(bundleFriend);
//        graphRequestFriends.executeAsync();

    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this, MainActivity23.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
