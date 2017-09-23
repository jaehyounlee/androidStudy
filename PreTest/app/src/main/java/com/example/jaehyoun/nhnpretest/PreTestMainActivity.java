package com.example.jaehyoun.nhnpretest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.HttpConnector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PreTestMainActivity extends AppCompatActivity{

    EditText search_view;
    Button search_btn;
    ListView listView;

    ArrayList<ContentsValues> mArray = new ArrayList<ContentsValues>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_test_main);

        search_view = (EditText) findViewById(R.id.search_edit);
        search_btn = (Button) findViewById(R.id.search_btn);
        listView = (ListView) findViewById(R.id.search_result_listview);

        mArray.add(new ContentsValues("title1", "content1"));
        mArray.add(new ContentsValues("title2", "content2"));
        mArray.add(new ContentsValues("title3", "content3"));
        mArray.add(new ContentsValues("title4", "content4"));

        RelatedListAdapter adapter = new RelatedListAdapter(mArray);
        listView.setAdapter(adapter);

        final View header = getLayoutInflater().inflate(R.layout.listview_header,null,false);

        listView.addHeaderView(header);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = search_view.getText().toString();

                ContentsLoader loader = new ContentsLoader(header,listView);

                loader.execute(keyword);

            }
        });
    }

}



/*

                final AsyncTask task = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] params) {
                        String keyWorkd = params[0].toString();


                        HttpConnector connector = new HttpConnector();
                        connector.setTargetUrl("https://en.wikipedia.org/api/rest_v1/page/summary/"+keyWorkd);
                        connector.setMethod("GET");
                        String response = connector.openConnenction();
                        System.out.println("datas : " + response);
                        return response;

                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        String json_string = (String)o;
                        try {
                            JSONObject jsonObject = new JSONObject(json_string);
                            String title = (String) jsonObject.get("title");
                            JSONObject thumbnail = null;
                            if(!jsonObject.isNull("thumbnail")){
                                thumbnail = jsonObject.getJSONObject("thumbnail");
                                System.out.println(thumbnail.toString());
                            }

                            ContentsLoader mContentsLoad = new ContentsLoader();



                            ImageView headerImage = (ImageView) header.findViewById(R.id.header_image);
                            TextView displayTitle = (TextView) header.findViewById(R.id.display_title);
                            TextView headerContents = (TextView) header.findViewById(R.id.header_contents);

                            ViewGroup.LayoutParams headerImageParam = headerImage.getLayoutParams();
                            int width = Integer.parseInt(thumbnail.getString("width"));
                            int height = Integer.parseInt(thumbnail.getString("height"));

                            System.out.println("width : " +width + "  height : " + height );
                            headerImageParam.height = height;
                            headerImageParam.width = width;

                            String contents = jsonObject.getString("extract_html");
                            contents = contents.replaceAll("<p>|<b>|</b>", "");


                            displayTitle.setText(jsonObject.getString("displaytitle"));
                            headerContents.setText(contents);


                            ContentsLoader loader = new ContentsLoader(thumbnail.getString("source"),headerImage);
                            loader.execute();



                            System.out.println(title);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                task.execute(search_view.getText());
            }
        });
 */