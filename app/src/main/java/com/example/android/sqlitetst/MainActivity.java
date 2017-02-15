package com.example.android.sqlitetst;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.android.sqlitetst.Utils.EleveContract;
import com.example.android.sqlitetst.Utils.EleveDbHelper;

public class MainActivity extends AppCompatActivity {
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    SQLiteDatabase mDb;
    EleveAdapter eA;
    EditText name;
    EditText note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView eleveRecyclerView;
        eleveRecyclerView = (RecyclerView)this.findViewById(R.id.all_eleves);
        name = (EditText) this.findViewById(R.id.name_EV);
        note = (EditText) this.findViewById(R.id.note_EV);

        eleveRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mDb = new EleveDbHelper(this).getWritableDatabase();
        eA = new EleveAdapter(this,getAllEleves());
        eleveRecyclerView.setAdapter(eA);


    }
    public Cursor getAllEleves()
    {
        return mDb.query(EleveContract.EleveEntry.TABLE_NAME, null,null,null,null,null, EleveContract.EleveEntry.COLUMN_TIMESTAMP);
    }

    public void addEleves(View view)
    {
        if(name.getText().length() == 0 || note.getText().length() == 0) return;

        int noteInt = 0;
        try{
            noteInt = Integer.parseInt(note.getText().toString());
        }catch (NumberFormatException ex) {
            // COMPLETED (12) Make sure you surround the Integer.parseInt with a try catch and log any exception
            Log.e(LOG_TAG, "Failed to parse party size text to number: " + ex.getMessage());
        }

        // COMPLETED (14) call addNewGuest with the guest name and party size
        // Add guest info to mDb
        addEleve(name.getText().toString(), noteInt);

        // COMPLETED (19) call mAdapter.swapCursor to update the cursor by passing in getAllGuests()
        // Update the cursor in the adapter to trigger UI to display the new list
        eA.swapCursor(getAllEleves());

        // COMPLETED (20) To make the UI look nice, call .getText().clear() on both EditTexts, also call clearFocus() on mNewPartySizeEditText
        //clear UI text fields
        note.clearFocus();
        note.getText().clear();
        name.getText().clear();

    }
    public void addEleve(String nom, int note)
    {
        ContentValues cv = new ContentValues();
        cv.put(EleveContract.EleveEntry.COLUMN_NOM,nom);
        cv.put(EleveContract.EleveEntry.COLUMN_NOTE,note);
        mDb.insert(EleveContract.EleveEntry.TABLE_NAME,null,cv);
    }
}
