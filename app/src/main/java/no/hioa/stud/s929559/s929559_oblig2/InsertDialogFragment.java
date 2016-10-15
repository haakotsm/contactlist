package no.hioa.stud.s929559.s929559_oblig2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class InsertDialogFragment extends DialogFragment implements TextView.OnEditorActionListener{
    private EditText fornavn;
    private EditText etternavn;
    private EditText telefon;
    private DatePicker bursdag;

    public interface EditNameDialogListener{
        void onFinishEditDialog(String fornavn,String etternavn, String telefon, Calendar date);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_dialog,container);
        fornavn = (EditText) view.findViewById(R.id.insert_fornavn);
        etternavn = (EditText) view.findViewById(R.id.insert_etternavn);
        telefon = (EditText) view.findViewById(R.id.insert_telefon);
        //bursdag = (DatePicker) view.findViewById(R.id.insert_bursdag);
        getDialog().setTitle("Insert contact");
        fornavn.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        fornavn.setOnEditorActionListener(this);
        etternavn.setOnEditorActionListener(this);
        telefon.setOnEditorActionListener(this);
        return view;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if(EditorInfo.IME_ACTION_DONE == i){
            EditNameDialogListener activity = (EditNameDialogListener) getActivity().getSupportFragmentManager().findFragmentByTag("ContactListFragment");
            if(!(fornavn.getText().toString().equals("") || etternavn.getText().toString().equals("") || telefon.getText().toString().equals(""))) {
                Calendar bursdag = Calendar.getInstance();
                bursdag.set(1991,10,22);
                activity.onFinishEditDialog(fornavn.getText().toString(), etternavn.getText().toString(),
                        telefon.getText().toString(), bursdag);
                this.dismiss();
                Log.i("Ny bruker lagt inn " + fornavn.getText().toString() + " " + etternavn.getText().toString(), "NyBruker");
                return true;
            }
        }
        Log.d("Feil legg inn kontakt", "NyBruker");
        return false;
    }
}
