package mad.example.teamdragons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClientAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ProductModel> productList;


    public ClientAdapter(Context context, int layout, ArrayList<ProductModel> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }
    @Override
    public int getCount() {
        return productList.size();
    }
    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView txtName, txtDes, txtPrice, txtId;
        ImageView imgIcon;
        Button getNext;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtId = row.findViewById(R.id.txtIdC);
            holder.txtName = row.findViewById(R.id.txtNameC);
            holder.txtDes = row.findViewById(R.id.txtDesC);
            holder.txtPrice = row.findViewById(R.id.txtPriceC);
            holder.imgIcon = row.findViewById(R.id.imgIconC);
            holder.getNext = row.findViewById(R.id.buttonorder);
            row.setTag(holder);
        } else{
            holder = (ViewHolder) row.getTag();
        }

        ProductModel model = productList.get(i);
        holder.txtId.setText(String.valueOf(model.getId()));
        holder.txtName.setText(model.getName());
        holder.txtDes.setText(model.getDes());
        holder.txtPrice.setText(model.getPrice());
        Bitmap bmp = BitmapFactory.decodeByteArray(model.getImage(), 0, (model.getImage()).length);
        holder.imgIcon.setImageBitmap(Bitmap.createScaledBitmap(bmp, 80, 80, false));
        return row;



    }



}
