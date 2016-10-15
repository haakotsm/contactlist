package no.hioa.stud.s929559.s929559_oblig2;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class ContactHandler {
    private static LinkedList<Contact> contacts;

    private ContactHandler() {

    }

    public static LinkedList<Contact> getInstance() {
        if (contacts == null) {
            contacts = new LinkedList<>();
        }
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact contact, Contact t1) {
                if (contact.getEtternavn().compareTo(t1.getEtternavn()) < 0)
                    return -1;
                else return 1;
            }
        });
        return contacts;
    }

    public static void setNull(){
        contacts = null;
    }
}
