package com.northcott;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    public static final String ROW_MESSAGE = "com.northcott.GAME_ROW";
    public static final String COL_MESSAGE = "com.northcott.GAME_COL";

    /**
     * Call play activity
     * @param view view
     */
    public void goPlay(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        EditText rowCountEditText = (EditText) findViewById(R.id.row_count);
        EditText colCountEditText = (EditText) findViewById(R.id.col_count);
        Integer rowCount = Integer.parseInt(rowCountEditText.getText().toString());
        Integer colCount = Integer.parseInt(colCountEditText.getText().toString());
        intent.putExtra(ROW_MESSAGE, rowCount);
        intent.putExtra(COL_MESSAGE, colCount);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
