package optimus_primus.si.mysqlcontroler;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class CreateNewActivity extends ActionBarActivity {
    Context context;
    CharSequence text;
    int toastDuration = Toast.LENGTH_SHORT;;
    Toast toast;
    private CharSequence okInsert = "Uspešno vpisano!";
    private CharSequence napaka = "Napaka!";

    EditText ime;
    EditText priimek;
    EditText naslov;
    EditText hisnaSt;
    EditText email;

    Button insert;

    String ime1, priimek1, naslov1, hisnaSt1, email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        insert = (Button) findViewById(R.id.create_btn);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertInto().execute();
            }
        });

        this.ime = (EditText)this.findViewById(R.id.ime);
        this.priimek = (EditText) this.findViewById(R.id.priimek);
        this.naslov = (EditText) this.findViewById(R.id.naslov);
        this.hisnaSt = (EditText) this.findViewById(R.id.hisna_st);
        this.email = (EditText) this.findViewById(R.id.e_mail);
    }


    private class InsertInto extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            ime1 = ime.getText().toString();
            priimek1 = priimek.getText().toString();
            naslov1 = naslov.getText().toString();
            hisnaSt1 = hisnaSt.getText().toString();
            email1 = email.getText().toString();

            ArrayList<NameValuePair> insert = new ArrayList<NameValuePair>(2);
            insert.add(new BasicNameValuePair("Ime", ime1));
            insert.add(new BasicNameValuePair("Priimek", priimek1));
            insert.add(new BasicNameValuePair("Naslov", naslov1));
            insert.add(new BasicNameValuePair("Hisna_st", hisnaSt1));
            insert.add(new BasicNameValuePair("E-mail", email1));

            String string = new ApiConnector().Insert(insert);
            return string;
        }
        @Override
        protected void onPostExecute(String odziv) {
            int i = Integer.parseInt(odziv);
            try{
                if(i==1){
                    context.getApplicationContext();
                    text = okInsert;
                }else{
                    text = napaka;
                }
                toast = Toast.makeText(context, text, toastDuration);
                Log.e("odziv:", odziv);
                toast.show();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            Intent intent = new Intent(CreateNewActivity.this, ShowAllDetailsActivity.class);
            startActivity(intent);
        }

    }
}
