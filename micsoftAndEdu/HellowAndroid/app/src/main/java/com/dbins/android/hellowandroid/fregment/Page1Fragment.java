package com.dbins.android.hellowandroid.fregment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dbins.android.hellowandroid.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by SKILLSUPPORT on 2017-11-16.
 */

public class Page1Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_page1, container, false);
        try {
            loadImage(view);
        } catch( Exception e){
            e.printStackTrace();
        }
        return view;
    }
    private void loadImage(final View view) throws ExecutionException, InterruptedException {
//        AssetManager asm = getActivity().getAssets();
//        InputStream is = asm.open("seoul.jpg");
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.seoul);
//        Bitmap bitmap = BitmapFactory.decodeStream(is);
        ImageView iv = view.findViewById(R.id.imageview);
        Glide.with(getActivity()).load("http://cfile25.uf.tistory.com/image/2550AC3855A1FEBB268954").asBitmap().into(iv);

    }
}
