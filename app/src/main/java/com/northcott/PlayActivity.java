package com.northcott;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PlayActivity extends Activity {

    private int rowCount;
    private int colCount;
    private int clickedRowIndex;
    private int clickedColIndex;
    private int playerTurn;

    private final int MARGIN_TOP = 10;
    private final int MARGIN_LEFT = 10;
    private final int FONT_SIZE = 70;
    private final int CELL_COLOR = 0xffffffff;
    private final int CHOSEN_COLOR = 0xffff00ff;

    private Logic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        clickedRowIndex = clickedColIndex = -1;
        playerTurn = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();

        rowCount = intent.getIntExtra(MainActivity.ROW_MESSAGE, 4);
        colCount = intent.getIntExtra(MainActivity.COL_MESSAGE, 4);

        logic = new Logic(rowCount, colCount);

        TableLayout mainLayout = (TableLayout) findViewById(R.id.main_layout);

        for (int i = 0; i < rowCount; ++i) {
            TableRow row = new TableRow(this);
            row.setMinimumWidth(mainLayout.getWidth());
            TextView[] tvs = new TextView[colCount];
            for (int j = 0; j < colCount; ++j) {
                tvs[j] = new TextView(this);
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
                tvs[j].setId(i * colCount + j);
                tvs[j].setTextSize(FONT_SIZE);
                tvs[j].setTypeface(Typeface.createFromAsset(getAssets(), "fonts/courier.ttf"));
                tvs[j].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        int clickRowIndex = v.getId() / colCount;
                        int clickColIndex = v.getId() % colCount;
                        if (clickedRowIndex == -1) {
                            if (logic.getPosition()[playerTurn][clickRowIndex] == clickColIndex) {
                                clickedRowIndex = clickRowIndex;
                                clickedColIndex = clickColIndex;
                                v.setBackgroundColor(CHOSEN_COLOR);
                            }
                        } else {
                            if (clickedRowIndex == clickRowIndex
                                    && logic.move(playerTurn, clickRowIndex, clickColIndex)) {
                                showBoard();
                                playerTurn = 1 - playerTurn;
                                clickedRowIndex = clickedColIndex = -1;
                            } else {
                                clickedRowIndex = clickedColIndex = -1;
                                showBoard();
                            }
                        }
                    }
                });
            }
        }

        showBoard();
    }

    protected void showBoard() {
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                TextView toChange = (TextView) findViewById(i * colCount + j);
                toChange.setText(".");
                toChange.setBackgroundColor(CELL_COLOR);
            }
        }
        int[][] position = logic.getPosition();
        for (int player = 0; player < 2; ++player) {
            for (int i = 0; i < rowCount; ++i) {
                int column = position[player][i];
                TextView toChange = (TextView) findViewById(i * colCount + column);
                toChange.setText(player == 0 ? "O" : "X");
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
