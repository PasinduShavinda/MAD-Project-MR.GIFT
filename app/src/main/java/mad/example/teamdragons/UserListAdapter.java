package mad.example.teamdragons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<User> userList;

    public UserListAdapter(Context context, int layout, ArrayList<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    private class ViewHolder{
        ImageView imageView;
        TextView name, age, contact, email, nic, address;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.name = (TextView) row.findViewById(R.id.tv_nameUL);
            holder.age = (TextView) row.findViewById(R.id.tv_ageUL);
            holder.contact = (TextView) row.findViewById(R.id.tv_contactUL);
            holder.email = (TextView) row.findViewById(R.id.tv_emailUL);
            holder.nic = (TextView) row.findViewById(R.id.tv_nicUL);
            holder.address = (TextView) row.findViewById(R.id.tv_addressUL);
            holder.imageView = (ImageView) row.findViewById(R.id.iv_userPicUL);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        User user = userList.get(position);

        holder.name.setText(user.getName());
        holder.age.setText(String.valueOf(user.getAge()));
        holder.contact.setText(user.getContact());
        holder.email.setText(user.getEmail());
        holder.nic.setText(user.getNic());
        holder.address.setText(user.getAddress());

        byte[] userImage = user.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(userImage, 0, userImage.length);
        holder.imageView.setImageBitmap(bitmap);



        return row;
    }
}
