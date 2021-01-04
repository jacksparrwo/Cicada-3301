package com.example.guessmysong;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessmysong.firebase.storage.StorageHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SongChoice extends AppCompatActivity {

    private StorageHandler mStorage = StorageHandler.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listsongssingle);

        final ListView list = (ListView) findViewById(R.id.listOfSongsText);
        final ArrayList<String> arrayList = new ArrayList<>();
        final String defaultString = " - Mysterious song";
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        MainActivity.UserRef.child("categorysongsguessednumbers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()) {
                    if(child.getKey().equals(getIntent().getStringExtra("category"))) {
                        String[] s = child.getValue().toString().split(", ");
                        int j=0;

                        for(int i=0; i<mStorage.GetTotalNumberOfSongs(); i++) {
                            if(j < s.length) {
                                int charToLong = Character.getNumericValue(s[j].charAt(0));
                                int charToLong2 = s[j].charAt(1) == ' ' ? -1 : Character.getNumericValue(s[j].charAt(1));
                                charToLong = charToLong2 == -1 ? charToLong : charToLong * 10 + charToLong2;

                                if (charToLong == i) {
                                    arrayList.add(s[j]);
                                    j++;
                                } else {
                                    arrayList.add(i + defaultString);
                                }
                            } else {
                                arrayList.add(i + defaultString);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mStorage.PlayChosenSong(getIntent().getStringExtra("category"), position);
                finish();
            }
        });
    }
}
