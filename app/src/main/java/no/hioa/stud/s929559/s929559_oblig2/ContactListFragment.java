package no.hioa.stud.s929559.s929559_oblig2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


public class ContactListFragment extends ListFragment implements InsertDialogFragment.EditNameDialogListener {
    private DBAdapter db;
    ListCursorAdapter lca;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_item, container);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ((MainActivity) getActivity()).showDetails(position);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = new DBAdapter(getActivity());
        db.open();
        Log.d("contactListFragment", "fyller listview med data");
        Cursor cur = db.finnalle();
        if (cur.getCount() == 0) {
            Calendar bursdag = Calendar.getInstance();
            bursdag.set(1991, 10, 22);
            settInn("Håkon", "Smørvik", "98844823", bursdag);
            cur = db.finnalle();
        }
        if (cur.moveToFirst()) {
            LinkedList<Contact> contacts = ContactHandler.getInstance();
            do {
                int id = cur.getInt(cur.getColumnIndexOrThrow(BaseColumns._ID));
                String fornavn = cur.getString(cur.getColumnIndexOrThrow("fornavn"));
                String etternavn = cur.getString(cur.getColumnIndexOrThrow("etternavn"));
                String telefon = cur.getString(cur.getColumnIndexOrThrow("telefon"));
                Calendar bursdag = Calendar.getInstance();
                bursdag.set(1991, 10, 22);
                @SuppressLint("SimpleDateFormat") String bdag = new SimpleDateFormat("dd/MM/yyyy").format(bursdag.getTime());
                Contact contact = new Contact(id, fornavn, etternavn, telefon, bdag);
                contacts.add(contact);
            } while (cur.moveToNext());
        }
        cur = db.finnalle();
        lca = new ListCursorAdapter(getActivity().getBaseContext(), cur);
        getListView().setDivider(getActivity().getResources().getDrawable(R.drawable.divider));
        getListView().setDividerHeight(10);
        setListAdapter(lca);
    }

   /* private void oppdater(String forNavn, String etterNavn) {
        db.oppdater(forNavn, etterNavn);
    }*/

    @SuppressLint("SimpleDateFormat")
    private void settInn(String fornavn, String etternavn, String telefon, Calendar bursdag) {
        ContentValues cv = new ContentValues(4);
        cv.put(DBAdapter.FORNAVN, fornavn);
        cv.put(DBAdapter.ETTERNAVN, etternavn);
        cv.put(DBAdapter.TELEFON, telefon);
        cv.put(DBAdapter.BURSDAG, new SimpleDateFormat("dd/MM/yyyy").format(bursdag.getTime()));
        db.insert(cv);
        ContactHandler.getInstance().add(find(fornavn, etternavn));
    }

    @SuppressLint("SimpleDateFormat")
    private Contact find(String fornavn, String etterNavn) {
        Cursor cur;
        cur = db.find(fornavn, etterNavn);
        Contact contact = null;
        if (cur.moveToFirst()) {
            try {
                Date bursdag = new SimpleDateFormat("dd/MM/yyyy").parse(cur.getString(4));
                contact = new Contact(cur.getInt(0), cur.getString(1),
                        cur.getString(2), cur.getString(3), new SimpleDateFormat("dd/MM/yyyy").format(bursdag));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cur.close();
        return contact;
    }


    @Override
    public void onResume() {
        super.onResume();
        db.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        db.close();
    }

    @Override
    public void onFinishEditDialog(String fornavn, String etternavn, String telefon, Calendar bursdag) {
        settInn(fornavn, etternavn, telefon, bursdag);
        Cursor cur = db.finnalle();
        lca.changeCursor(cur);
    }
}
