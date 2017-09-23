package oxkyle.timelydiary.mainList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oxkyle.timelydiary.R;


/**
 * Created by ijaehyeon on 2017. 8. 13..
 */

public class MainListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_list_fragment, container, false);

        return rootView;
    }
}
