package optimus_primus.si.mysqlcontroler;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    Button selectAll, create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectAll = (Button) findViewById(R.id.select_all);
        selectAll.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                    Intent intent = new Intent(MainActivity.this, ShowAllDetailsActivity.class);
                    startActivity(intent);

            }
        });
        create = (Button) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, CreateNewActivity.class);
                startActivity(intent);
            }
        });
    }

}
