package com.northcott;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PlayActivity extends Activity {

    private int rowCount;
    private int colCount;

    private final int MARGIN_TOP = 10;
    private final int MARGIN_LEFT = 10;
    private final int CELL_COLOR = 0xffffffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();

        int rowCount = intent.getIntExtra(MainActivity.ROW_MESSAGE, 4);
        int colCount = intent.getIntExtra(MainActivity.COL_MESSAGE, 4);

        TableLayout mainLayout = (TableLayout) findViewById(R.id.main_layout);

        for (int i = 0; i < rowCount; ++i) {
            TableRow row = new TableRow(this);
            row.setMinimumWidth(mainLayout.getWidth());
            TextView[] tvs = new TextView[colCount];
            for (int j = 0; j < colCount; ++j) {
                tvs[j] = new TextView(this);
                tvs[j].setText("O");
                row.addView(tvs[j]);
            }
            mainLayout.addView(row);
            ((ViewGroup.MarginLayoutParams) row.getLayoutParams()).setMargins(0, MARGIN_TOP, 0, 0);
            for (int j = 0; j < colCount; ++j) {
                TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                params.setMargins(MARGIN_LEFT, 0, 0, 0);
                tvs[j].setLayoutParams(params);
                tvs[j].setBackgroundColor(CELL_COLOR);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play, menu);
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
