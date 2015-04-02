package com.ec700.flowdroidtest.flowdroidtestapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ec700.flowdroidtest.flowdroidtestapp.vuln.VulnerableGSONClass;
import com.ec700.flowdroidtest.flowdroidtestapp.vuln.VulnerableIOClass;

public class MainActivity extends ActionBarActivity {

    @Override
    public ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VulnerableIOClass vulnerableIOClass = new VulnerableIOClass();
        TextView t = (TextView) findViewById(R.id.my_textview);
        t.setText(vulnerableIOClass.s);

        //VulnerableGSONClass vulnerableGSONClass = new VulnerableGSONClass();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
