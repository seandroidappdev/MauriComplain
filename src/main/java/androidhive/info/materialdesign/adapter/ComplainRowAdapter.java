package androidhive.info.materialdesign.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidhive.info.materialdesign.R;
import androidhive.info.materialdesign.model.Complain;
import androidhive.info.materialdesign.model.ComplainAppController;

/**
 * Created by Fazley on 09/06/2015.
 */
public class ComplainRowAdapter extends ArrayAdapter<Complain> {

    public ComplainRowAdapter(Context context, int resource, List<Complain> objects) {
        super(context, resource, objects);
    }

    public static class ViewHolder{
        private TextView tvDate;
        private TextView tvDetails;
        private TextView tvPlace;
        private ImageView ivImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_track_case,parent,false);

            viewHolder.tvDetails = (TextView) convertView.findViewById(R.id.tvDetails);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            viewHolder.tvPlace = (TextView) convertView.findViewById(R.id.tvPlace);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();
        bindDetails(position,viewHolder);
        return convertView;
    }

    private void bindDetails(int position,ViewHolder viewHolder) {

        bindDetails(position, viewHolder.tvDetails);
        bindDate(position, viewHolder.tvDate);
        bindPLace(position, viewHolder.tvPlace);
        bindImage(position,viewHolder.ivImage);
    }

    private void bindDate(int position, TextView tvDate) {
        if(tvDate != null)
            tvDate.setText(getItem(position).getDate());
    }

    private void bindPLace(int position, TextView tvPlace) {
        if(tvPlace != null)
            tvPlace.setText(getItem(position).getPlace());
    }

    private void bindDetails(int position, TextView tvDetails) {
        if(tvDetails != null)
            tvDetails.setText(getItem(position).getDetails());
    }

    public void bindImage(int position, ImageView ivImage) {
        String dept = getItem(position).getDepartment();
        if(dept.equals(ComplainAppController.DEPT_INFRASTRUCTURE))
            ivImage.setImageResource(R.drawable.r_infrastructure);
        else if(dept.equals(ComplainAppController.DEPT_LEGAL))
            ivImage.setImageResource(R.drawable.r_lrgal);
        else
            ivImage.setImageResource(R.drawable.icn_consumer);
    }

}
