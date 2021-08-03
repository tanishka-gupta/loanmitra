package in.partner.loanchacha;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// adapter class fro custom menu list
class AdapterEventList extends BaseAdapter {

    private Activity activity;
    public ImageLoader imageLoader;


    public AdapterEventList(Activity act) {
        this.activity = act;
        imageLoader = new ImageLoader(act);

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ActivityEventList.Menu_ID.size();
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
            convertView = inflater.inflate(R.layout.blog_list_item, null);
            holder = new ViewHolder();

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtText = (TextView) convertView.findViewById(R.id.txtText);
        holder.txtSubText = (TextView) convertView.findViewById(R.id.txtSubText);
        holder.txtSubTextmax = (TextView) convertView.findViewById(R.id.txtSubTextmax);
        holder.txtPer = (TextView) convertView.findViewById(R.id.txtSubTextPer);


        float per = 0;

        holder.imgThumb = (ImageView) convertView.findViewById(R.id.imgThumb);

        holder.txtText.setText(ActivityEventList.Menu_name.get(position));

        String str="";
        holder.txtSubText.setText(ActivityEventList.Menu_date.get(position));
        holder.txtSubText.setVisibility(View.VISIBLE);
       /* if(ActivityEventList.Menu_price.get(position)==0L) {
            holder.txtSubText.setVisibility(View.VISIBLE);
            holder.txtSubText.setText("");
            str =  "<font color='#A80000' >Enquire Now</font>"  ;
            holder.txtSubTextmax.setText(Html.fromHtml(str));
            //  holder.txtSubTextmax.setPaintFlags(holder.txtSubTextmax.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            if (ActivityEventList.Menu_price.get(position) < ActivityEventList.Menu_max_price.get(position)) {
                holder.txtSubText.setVisibility(View.VISIBLE);
                str = "<font color='#A80000' >" + ActivityEventList.Currency + ActivityEventList.Menu_max_price.get(position);
                holder.txtSubTextmax.setText(Html.fromHtml(str));

                holder.txtSubTextmax.setPaintFlags(holder.txtSubTextmax.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            } else {

                holder.txtSubTextmax.setVisibility(View.GONE);
            }


            float max_price = Float.parseFloat(ActivityEventList.Menu_max_price.get(position).toString());
            float price = Float.parseFloat(ActivityEventList.Menu_price.get(position).toString());
            float disc = max_price - price;

            try {

                per = disc / max_price * 100;
            } catch (Exception ex) {

            }

            if (per == 100) {
                per = 0;
            }

            if (per == 0) {
                holder.txtPer.setVisibility(View.GONE);
            } else {
                holder.txtPer.setVisibility(View.GONE);
                holder.txtPer.setText(Html.fromHtml("<b>(" + String.format("%.0f", per) + "% OFF" + ")</b>"));
            }

            str = ActivityEventList.Currency + " " + ActivityEventList.Menu_price.get(position);
            holder.txtSubText.setText(Html.fromHtml(str));
        } */


        imageLoader.DisplayImage(Constant.AdminPageURL+ActivityEventList.Menu_image.get(position), holder.imgThumb);

        return convertView;
    }

    static class ViewHolder {
        TextView txtText, txtSubText, txtSubTextmax, txtPer;
        ImageView imgThumb;
    }


}