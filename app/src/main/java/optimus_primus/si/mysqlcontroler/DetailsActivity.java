package optimus_primus.si.mysqlcontroler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class DetailsActivity extends ActionBarActivity {

    Context context;
    CharSequence text;
    int toastDuration = Toast.LENGTH_SHORT;;
    Toast toast;

    private CharSequence okDelete = "Uspešno izbrisano!";
    private CharSequence okInsert = "Uspešno vpisano!";
    private CharSequence okUpdate = "Uspešno posodobljeno!";
    private CharSequence napaka = "Napaka!";

    TextView id;

    EditText ime;
    EditText priimek;
    EditText naslov;
    EditText hisnaSt;
    EditText email;

    int zaposleniID;

    Button update;
    Button insert;
    Button delete;

    String ime1, priimek1, naslov1, hisnaSt1, email1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity1);

        update = (Button) findViewById(R.id.update_button);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Update().execute();
            }
        });
        insert = (Button) findViewById(R.id.insert_button_details);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(DetailsActivity.this, CreateNewActivity.class);
                startActivity(intent);
            }
        });
        delete = (Button) findViewById(R.id.delete_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAlert();
            }
        });

        this.id = (TextView)this.findViewById(R.id.ID_zaposleni);
        this.ime = (EditText)this.findViewById(R.id.ime);
        this.priimek = (EditText) this.findViewById(R.id.priimek);
        this.naslov = (EditText) this.findViewById(R.id.naslov);
        this.hisnaSt = (EditText) this.findViewById(R.id.hisna_st);
        this.email = (EditText) this.findViewById(R.id.e_mail);

        this.zaposleniID = getIntent().getIntExtra("ID_zaposleni",-1);
        if(this.zaposleniID > 0){
            new GetDetails().execute(new ApiConnector());
        }


    }

    private void deleteAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alertDialogTitle);
        builder.setMessage(R.string.alertDialogMessage);
        builder.setPositiveButton(R.string.alertDialogConfirmation, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Delete().execute(new ApiConnector());
            }
        });
        builder.setNegativeButton(R.string.alertDialogCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog  = builder.create();
        dialog.show();
    }


    private class GetDetails extends AsyncTask<ApiConnector, Long, JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            return params[0].GetDetails(zaposleniID);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            try{
                id.setText(Integer.toString(zaposleniID));
                JSONObject zaposleni = jsonArray.getJSONObject(0);

                ime.setText(zaposleni.getString("Ime"));
                priimek.setText(zaposleni.getString("Priimek"));
                naslov.setText(zaposleni.getString("Naslov"));
                hisnaSt.setText(zaposleni.getString("Hisna_st"));
                email.setText(zaposleni.getString("E-mail"));

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    private class Update extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params){
            ime1 = ime.getText().toString();
            priimek1 = priimek.getText().toString();
            naslov1 = naslov.getText().toString();
            hisnaSt1 = hisnaSt.getText().toString();
            email1 = email.getText().toString();
            String id = Integer.toString(zaposleniID);

            ArrayList<NameValuePair> update = new ArrayList<NameValuePair>(2);
            update.add(new BasicNameValuePair("ID_zaposleni", id));
            update.add(new BasicNameValuePair("Ime", ime1));
            update.add(new BasicNameValuePair("Priimek", priimek1));
            update.add(new BasicNameValuePair("Naslov", naslov1));
            update.add(new BasicNameValuePair("Hisna_st", hisnaSt1));
            update.add(new BasicNameValuePair("E-mail", email1));
            Log.e("tabela", update.toString());

            String odziv = new ApiConnector().Update(update);
            return odziv;
        }
        @Override
        protected void onPostExecute(String odziv) {
            int i = Integer.parseInt(odziv);
            try{
                if(i==1){
                    text = okUpdate;
                }else{
                    text = napaka;
                }
                toast = Toast.makeText(context=getApplicationContext(), text, toastDuration);
                Log.e("odziv:", odziv);
                toast.show();

            }
            catch (Exception e){
                e.printStackTrace();
            }
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }

    }

    private class Delete extends AsyncTask<ApiConnector, Long, String>
    {
        @Override
        protected String doInBackground(ApiConnector... params) {
            return params[0].Delete(zaposleniID);
        }

        @Override
        protected void onPostExecute(String odziv) {
            int i = Integer.parseInt(odziv);
            try{
                if(i==1){
                text = okDelete;
                }else{
                    text = napaka;
                }
                toast = Toast.makeText(context=getApplicationContext(), text, toastDuration);
                Log.e("odziv:", odziv);
                toast.show();

            }
            catch (Exception e){
                e.printStackTrace();
            }
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }
}
}
