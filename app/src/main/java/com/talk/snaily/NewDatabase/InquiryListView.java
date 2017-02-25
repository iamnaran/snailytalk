package com.talk.snaily.NewDatabase;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.talk.snaily.MainActivity;
import com.talk.snaily.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 22/09/2016.
 */
public class InquiryListView extends AppCompatActivity {
    ListView listView;
    Inquiry inquiry;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);


        listView = (ListView) findViewById(R.id.inquiry_list_view);
        dbHandler = new DBHandler(this);

        final List<Inquiry> inquiries = dbHandler.getAllInquiries();

        final DatabaseAdapter databaseAdapter = new DatabaseAdapter(InquiryListView.this,R.layout.adapter_inquiry_list_single,inquiries);
        listView.setAdapter(databaseAdapter);
        listView.deferNotifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                inquiry = (Inquiry) parent.getItemAtPosition(position);

                Intent intent = new Intent(InquiryListView.this, DisplayDetailDatabase.class);

                intent.putExtra("database_data", inquiry);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.count_database, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_count) {
            int count = dbHandler.getMyInquiryCount();
            Toast.makeText(InquiryListView.this, "Total no of enteries is : "+count, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
