package fpoly.tuyentdph23684.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {
    FirebaseFirestore database;
    Button btninsert, btnupdate, btnXoa;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvResult=findViewById(R.id.Tv1);
        database = FirebaseFirestore.getInstance();
        btninsert=findViewById(R.id.BtnInsert);
        btninsert.setOnClickListener(v->{
            insertFirebase(tvResult);
        });
        btnupdate.findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(v->{
            updateFirebase(tvResult );
        });
//        btnXoa= findViewById(R.id.btnDelete);
//        btnXoa.setOnClickListener(v->{
//            deleteFilebase (tvResult);
//        });
    }
    String id="";
    ToDo toDo= null;

    public void insertFirebase(TextView tvResult){
        id= UUID.randomUUID().toString();
        toDo= new ToDo(id, "title 1", "content 1");
        HashMap<String, Object> mapTodo= toDo.convertHashMap();
        database.collection("TODO").document(id).set(mapTodo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        tvResult.setText("Thêm thành công");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });

    }
    public void updateFirebase(TextView tvResult){
        id="694b509b-c482-4f11-b98b-809c561e9478";
        toDo= new ToDo(id, "sua title 1","sua content 1");
        database.collection("TODO").document(toDo.getId()).update(toDo.convertHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        tvResult.setText("sua thanh cong");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
    }
    public void deleteFilebase (TextView tvResult){
        id="694b509b-c482-4f11-b98b-809c561e9478";
        database.collection("TODO").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        tvResult.setText("Xoa thanh cong");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });

    }
}