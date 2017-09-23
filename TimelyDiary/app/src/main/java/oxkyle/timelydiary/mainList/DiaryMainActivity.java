package oxkyle.timelydiary.mainList;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import oxkyle.timelydiary.R;

public class DiaryMainActivity extends AppCompatActivity {

    RecyclerView reView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_main);

    }
}
