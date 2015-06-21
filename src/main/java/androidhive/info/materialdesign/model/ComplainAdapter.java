package androidhive.info.materialdesign.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidhive.info.materialdesign.R;

/**
 * Created by Fazley on 09/06/2015.
 */
public class ComplainAdapter extends ArrayAdapter<Complain> {

    public ComplainAdapter(Context context, int resource, List<Complain> objects) {
        super(context, resource, objects);
    }

    public static class ViewHolder{
        private TextView tvTel;
        private TextView tvDetails;
        private TextView tvOtherDetails;
        private TextView tvPlace;
        private TextView tvOtherPlace;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_complain,parent,false);
            viewHolder.tvTel = (TextView) convertView.findViewById(R.id.tvTel);
            viewHolder.tvDetails = (TextView) convertView.findViewById(R.id.tvDetails);
            viewHolder.tvOtherDetails = (TextView) convertView.findViewById(R.id.tvOtherDetails);
            viewHolder.tvPlace = (TextView) convertView.findViewById(R.id.tvPlace);
            viewHolder.tvOtherPlace = (TextView) convertView.findViewById(R.id.tvOtherPlace);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();
        bindDetails(position,viewHolder);
        return convertView;
    }

    private void bindDetails(int position,ViewHolder viewHolder) {
        bindTel(position,viewHolder.tvTel);
        bindDetails(position, viewHolder.tvDetails);
        bindOtherDetails(position, viewHolder.tvOtherDetails);
        bindPLace(position, viewHolder.tvPlace);
        bindOtherPlace(position, viewHolder.tvOtherPlace);
    }

    private void bindOtherDetails(int position, TextView tvOtherDetails) {
        if(tvOtherDetails != null)
            tvOtherDetails.setText(getItem(position).getOtherDetails());
    }

    private void bindOtherPlace(int position, TextView tvOtherPlace) {
        if(tvOtherPlace != null)
            tvOtherPlace.setText(getItem(position).getAdditionalPlace());
    }

    private void bindPLace(int position, TextView tvPlace) {
        if(tvPlace != null)
            tvPlace.setText(getItem(position).getPlace());
    }

    private void bindDetails(int position, TextView tvDetails) {
        if(tvDetails != null)
            tvDetails.setText(getItem(position).getDetails());
    }

    private void bindTel(int position, TextView tvTel) {
        if (tvTel != null)
            tvTel.setText(getItem(position).getAuthor());
    }

}
