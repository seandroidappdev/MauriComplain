package androidhive.info.materialdesign.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidhive.info.materialdesign.R;


public class MakeComplainFragment extends Fragment {

    public MakeComplainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_make_complain, container, false);
        final Button ivRound = (Button) rootView.findViewById(R.id.ivRound);
        ivRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(),"Round button",Toast.LENGTH_LONG).show();
            }
        });

        Button btTop = (Button) rootView.findViewById(R.id.btTopMakeComplain);
        btTop.setBackgroundResource(R.drawable.top_make_case_pressed);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
