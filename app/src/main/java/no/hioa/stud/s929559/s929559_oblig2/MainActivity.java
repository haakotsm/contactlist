package no.hioa.stud.s929559.s929559_oblig2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ContactListFragment contactListFragment;
    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactListFragment = new ContactListFragment();

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.activity_main, contactListFragment, "ContactListFragment").commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert:
                InsertDialogFragment insertDialogFragment = new InsertDialogFragment();
                insertDialogFragment.show(fm, "InsertDialogFragment");

                break;
            case R.id.delete:
                Toast.makeText(this, "Delete trykket", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    public void showDetails(int index) {
        ContactFragment contact = new ContactFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        contact.setArguments(args);
        fm.beginTransaction().replace(R.id.activity_main, contact).addToBackStack(null).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ContactHandler.setNull();
    }

    @Override
    public void onBackPressed() {
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
