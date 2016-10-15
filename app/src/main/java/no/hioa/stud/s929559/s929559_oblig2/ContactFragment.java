package no.hioa.stud.s929559.s929559_oblig2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactFragment extends Fragment {
    TextView fornavn;
    TextView etternavn;
    Contact contact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_contact, container);
        fornavn = (TextView) getActivity().findViewById(R.id.contact_fornavn);
        etternavn = (TextView) getActivity().findViewById(R.id.contact_etternavn);
        contact = ContactHandler.getInstance().get(getArguments().getInt("index"));
        fornavn.setText(contact.getFornavn());
        etternavn.setText(contact.getEtternavn());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle args) {
        super.onViewCreated(view, args);
    }

}
