package com.pstech.tutorials.menuoptions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pstech.tutorials.R;

public class MenuExampleActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        items = findViewById(R.id.item_list);
        items.setOnItemClickListener(this);

        // implement below adapter.
        ArrayAdapter adapter = null;
        items.setAdapter(adapter);

        registerForContextMenu(items);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "You pressed Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Toast.makeText(getApplicationContext(), "You pressed help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.send_feedback:
                sendFeedback();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), "You clicked [" + i + "] item", Toast.LENGTH_SHORT).show();
    }

    // Menu for list which comes up when you press the item for long time
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle("Position : " + info.position);
        String[] menuItems = getResources().getStringArray(R.array.menu);
        for (int i = 0; i<menuItems.length; i++) {
            menu.add(Menu.NONE, i, i, menuItems[i]);
        }
    }

    // to define what happens when you click on particular
    // item of menu which comes when you press item for long time
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();

        switch (menuItemIndex) {
            case 0:
                // for delete
                Toast.makeText(getApplicationContext(), "To Be Implemented", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                // for Exclude
                Toast.makeText(getApplicationContext(), "To Be Implemented", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                // for Marking Favorite
                Toast.makeText(getApplicationContext(), "Mark it favorite", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    public void sendFeedback() {
        String emailId = getResources().getString(R.string.emailId);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emailId, null));
        String[] addresses = {emailId};
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject" + getPackageName());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses); // String[] addresses

        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
