package no.hioa.stud.s929559.s929559_oblig2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ListCursorAdapter extends CursorAdapter {
    private LayoutInflater mInflater;

    public ListCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return mInflater.inflate(R.layout.fragment_list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView navn = (TextView) view.findViewById(R.id.navn);
        TextView dato = (TextView) view.findViewById(R.id.dato);
        TextView telefon = (TextView) view.findViewById(R.id.telefon);

        String fornavn = cursor.getString(cursor.getColumnIndexOrThrow("fornavn"));
        String etternavn = cursor.getString(cursor.getColumnIndexOrThrow("etternavn"));
        String tlf = cursor.getString(cursor.getColumnIndexOrThrow("telefon"));
        String bursdag = cursor.getString(cursor.getColumnIndexOrThrow("bursdag"));
        navn.setText(etternavn + ", " + fornavn);
        telefon.setText(tlf);
        dato.setText(bursdag);
    }
}
