package optimus_primus.si.mysqlcontroler;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShowAllDetailsActivity extends ActionBarActivity {

    private ListView GetAllContainerListView;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_details);
        this.GetAllContainerListView = (ListView) this.findViewById(R.id.GetAllContainerListView);
        new GetAllTask().execute(new ApiConnector());
        this.GetAllContainerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    JSONObject personClicked = jsonArray.getJSONObject(position);
                    Intent showDetails;
                    showDetails = new Intent(getApplicationContext(), DetailsActivity.class);
                    showDetails.putExtra("ID_zaposleni", personClicked.getInt("ID_zaposleni"));
                    startActivityForResult(showDetails, 1);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                finish();
                startActivity(getIntent());
            }
        }
    }


    public void setListAdapter(JSONArray jsonArray){
        this.jsonArray = jsonArray;
        this.GetAllContainerListView.setAdapter(new GetAllContainer(jsonArray, this));

    }


    private class GetAllTask extends AsyncTask<ApiConnector, Long, JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            return params[0].GetAll();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            setListAdapter(jsonArray);
        }
    }
}
