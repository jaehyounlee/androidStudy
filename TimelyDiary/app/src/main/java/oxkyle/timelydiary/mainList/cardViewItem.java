package oxkyle.timelydiary.mainList;

/**
 * Created by ijaehyeon on 2017. 8. 13..
 */

public class cardViewItem {

    private int image;
    private String title = "";

    int getImage(){
        return image;
    }
    String getTitle(){
        return title;
    }

    public cardViewItem(int img, String tit){
        image = img;
        title = tit;
    }
}
