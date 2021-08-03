package in.partner.loanchacha;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// adapter class fro custom menu list
class AdapterDownloadsList extends BaseAdapter {

    private Activity activity;
    public ImageLoader imageLoader;


    public AdapterDownloadsList(Activity act) {
        this.activity = act;
        imageLoader = new ImageLoader(act);

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ActivityDownloadsList.Menu_ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.downloads_list_item, null);
            holder = new ViewHolder();

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtText = (TextView) convertView.findViewById(R.id.txtText);
        holder.txtSubText = (TextView) convertView.findViewById(R.id.txtSubText);
    //    holder.txtSubTextDate = (TextView) convertView.findViewById(R.id.txtSubTextDate);
        holder.imgThumb = (ImageView) convertView.findViewById(R.id.imgThumb);

        holder.txtText.setText(ActivityDownloadsList.Menu_name.get(position));
        holder.txtSubText.setText(ActivityDownloadsList.Menu_patient.get(position));
        if(ActivityDownloadsList.Menu_patient.get(position).equals("PROCESSING")) {
            holder.txtSubText.setTextColor(Color.parseColor("#0000A8"));

        } else if(ActivityDownloadsList.Menu_patient.get(position).equals("APPROVED")) {
            holder.txtSubText.setTextColor(Color.parseColor("#00A800"));
        } else {
            holder.txtSubText.setTextColor(Color.parseColor("#A80000"));
        }

     //   holder.txtSubTextDate.setText(ActivityDownloadsList.Menu_Date.get(position)+" / "+ActivityDownloadsList.Menu_price.get(position));


       // imageLoader.DisplayImage(Constant.AdminPageURL+ActivityDownloadsList.Menu_image.get(position), holder.imgThumb);

        return convertView;
    }

    static class ViewHolder {
        TextView txtText, txtSubText, txtSubTextDate;
        ImageView imgThumb;
    }


}