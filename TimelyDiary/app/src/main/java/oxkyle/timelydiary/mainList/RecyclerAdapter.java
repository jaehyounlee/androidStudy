package oxkyle.timelydiary.mainList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import oxkyle.timelydiary.R;

/**
 * Created by ijaehyeon on 2017. 8. 13..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<cardViewItem> items;
    int item_layout;



    /**
     * 생성자
     * @param pContext context
     * @param pItems CardViewItem
     * @param pItem_layout Cardview_Layout
     */
    public RecyclerAdapter(Context pContext, ArrayList<cardViewItem> pItems, int pItem_layout) {
        context = pContext;
        items = pItems;
        item_layout = pItem_layout;
    }


    /**
     * 레이아웃을 만들어서 Holder에 저장
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(mView);
    }


    /**
     * ListView의 getView같은거
     * 넘겨받은 데이터를 화면에 출
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final cardViewItem mItem = items.get(position);
        Drawable mDrawable = ContextCompat.getDrawable(context, mItem.getImage());
        holder.image.setBackground(mDrawable);
        holder.title.setText(mItem.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, mItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * 뷰 재활용을 위한 뷰 홀더
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  image;
        TextView title;
        CardView cardView;

        public ViewHolder(View pView){
            super(pView);

            image = (ImageView)pView.findViewById(R.id.card_img);
            title = (TextView)pView.findViewById(R.id.card_txt);
            cardView = (CardView)pView.findViewById(R.id.cardView);
        }
    }
}
