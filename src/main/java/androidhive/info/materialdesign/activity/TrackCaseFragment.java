package androidhive.info.materialdesign.activity;

import android.content.Context;
import android.graphics.LinearGradient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import androidhive.info.materialdesign.R;
import androidhive.info.materialdesign.adapter.ComplainRowAdapter;
import androidhive.info.materialdesign.model.Complain;
import androidhive.info.materialdesign.model.ComplainAdapter;
import androidhive.info.materialdesign.model.ComplainAppController;
import androidhive.info.materialdesign.model.JSONParser;
import androidhive.info.materialdesign.model.WS_CONFIG;


public class TrackCaseFragment extends Fragment {

    private Button btToggleMenu;
    private LinearLayout llTopMenu;
    private boolean menu_flag = true;
    private static int roundButtonYloc = 0;

    // Creating JSON Parser object
    private JSONParser jParser = new JSONParser();
    private List<Complain> complainList = new ArrayList<>();
    private HashMap<Integer,Complain> complains = new HashMap<>();
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_COMPLAIN = "complain";
    private static final String TAG_ID = "complainId";
    private static final String TAG_DEPT = "department";
    private static final String TAG_DESC = "complainDetails";
    private static final String TAG_OTHER_DESC = "complainOtherDetails";
    private static final String TAG_DATE = "complainDate";
    private static final String TAG_CURRENT_TIMESATMP = "dateStamp";
    private static final String TAG_PLACE = "place";
    private static final String TAG_OTHER_PLACE = "additionalPlace";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_STATUS = "status";

    // products JSONArray
    JSONArray complainJSONArray = null;


    private ListView lvComplains;
    private ProgressBar progressBar;

    public TrackCaseFragment() {
        // Required empty public constructor
    }

    private View fragmentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_track_case, container, false);
        Button btTop = (Button) fragmentView.findViewById(R.id.btTopTrackCase);
        btTop.setBackgroundResource(R.drawable.top_track_case_pressed);
        init();
        getComplainFromWS();

        btToggleMenu = (Button) fragmentView.findViewById(R.id.btToggleMenu);
        llTopMenu = (LinearLayout) fragmentView.findViewById(R.id.llTopMenu);
        roundButtonYloc = btToggleMenu.getTop();

        btToggleMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menu_flag) {
                    llTopMenu.setVisibility(View.GONE);
                    menu_flag = false;
                }
                else {
                    llTopMenu.setVisibility(View.VISIBLE);
                    menu_flag = true;
                }
            }
        });


        return fragmentView;
    }

    private void getComplainFromWS() {
        new LoadAllProducts().execute();
    }

    public void bindDetailsToList() {
        ComplainRowAdapter adapter = new ComplainRowAdapter(fragmentView.getContext(),0,complainList);
        lvComplains.setAdapter(adapter);
    }

    private void init() {
        lvComplains = (ListView) fragmentView.findViewById(R.id.lvComplains);
        progressBar = (ProgressBar) fragmentView.findViewById(R.id.progressBar);
    }



    /**
     * Background Async Task to Load all complains by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<Context, HashMap<Integer,Complain>, HashMap<Integer,Complain>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        protected HashMap<Integer,Complain> doInBackground(Context... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(WS_CONFIG.allComplains_url, "GET", params);
            Log.d("All complains trace: ", json.toString());
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    complainJSONArray = json.getJSONArray(TAG_COMPLAIN);
                    for (int i = 0; i <complainJSONArray.length(); i++) {
                        JSONObject c = complainJSONArray.getJSONObject(i);
                        // Storing each json item in variable
                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_AUTHOR);
                        String description = c.getString(TAG_DESC);
                        String Otherdescription = c.getString(TAG_OTHER_DESC);
                        String place = c.getString(TAG_PLACE);
                        String otherPlace = c.getString(TAG_OTHER_PLACE);
                        String date = c.getString(TAG_DATE);
                        String dateStamp = c.getString(TAG_CURRENT_TIMESATMP);
                        String status = c.getString(TAG_STATUS);
                        String department = c.getString(TAG_DEPT);


                        Complain complain = new Complain();
                        complain
                                .setId(Integer.parseInt(id))
                                .setAuthor(name)
                                .setDetails(description)
                                .setOtherDetails(Otherdescription)
                                .setPlace(place)
                                .setAdditionalPlace(otherPlace)
                                .setDate(date)
                                .setDateStamp(dateStamp)
                                .setStatus(status)
                                .setDepartment(department);
                        complains.put(complain.getId(), complain);
                        complainList.add(complain);
                    }
                }
                else {
                    return null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return complains;
        }

        @Override
        protected void onPostExecute(HashMap<Integer, Complain> complainHm) {
            super.onPostExecute(complainHm);
            ComplainAppController.complains = complains;
            Iterator<Integer> itr = complains.keySet().iterator();
            while(itr.hasNext()) {
                Complain c = complains.get(itr.next());
            }
            bindDetailsToList();
            progressBar.setVisibility(View.GONE);
        }
    }

}
