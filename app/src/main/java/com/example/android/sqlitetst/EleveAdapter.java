package com.example.android.sqlitetst;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sqlitetst.Utils.EleveContract;


/**
 * Created by mupac_000 on 15-02-17.
 */

public class EleveAdapter extends RecyclerView.Adapter<EleveAdapter.EleveViewHolder> {

    private Cursor mCursor:
    private Context mCtx;

    public EleveAdapter( Context mCtx,Cursor mCursor) {
        this.mCursor = mCursor;
        this.mCtx = mCtx;
    }

    @Override
    public EleveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view= inflater.inflate(R.layout.eleve_list,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(EleveViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String name = mCursor.getString(mCursor.getColumnIndex(EleveContract.EleveEntry.COLUMN_NOM));
        int note = mCursor.getInt(mCursor.getColumnIndex(EleveContract.EleveEntry.COLUMN_NOTE));

        // Display the guest name
        holder.name.setText(name);
        // Display the party count
        holder.note.setText(String.valueOf(note));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        // COMPLETED (16) Inside, check if the current cursor is not null, and close it if so
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        // COMPLETED (17) Update the local mCursor to be equal to  newCursor
        mCursor = newCursor;
        // COMPLETED (18) Check if the newCursor is not null, and call this.notifyDataSetChanged() if so
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
    class EleveViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView note;

        public EleveViewHolder(View itemView)
        {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            note = (TextView)itemView.findViewById(R.id.note);
        }
    }
}
