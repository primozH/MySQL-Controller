package optimus_primus.si.mysqlcontroler;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Primož on 1.4.2015.
 */
public class GetAllContainer extends BaseAdapter {
    JSONArray data;
    Activity activity;
    static LayoutInflater inflater = null;

    public GetAllContainer(JSONArray jsonArray, Activity a){
        this.data = jsonArray;
        this.activity = a;

        inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return this.data.length();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        List<NameValuePair> cell;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.get_all_container_list_view_layout, null);
            cell = new List<NameValuePair>();

            cell.Ime = (TextView) convertView.findViewById(R.id.ime_priimek);
            cell.Naslov = (TextView) convertView.findViewById(R.id.naslov);
            cell.HisnaSt = (TextView)convertView.findViewById(R.id.hisna_st);
            cell.Email = (TextView) convertView.findViewById(R.id.e_mail);

            convertView.setTag(cell);
        }
        else{
            cell= (List<NameValuePair>) convertView.getTag();
        }
        try {
            JSONObject jsonObject = this.data.getJSONObject(position);
            cell.Ime.setText(jsonObject.getString("Ime")+" "+jsonObject.getString("Priimek"));
            cell.Naslov.setText(jsonObject.getString("Naslov")+" "+jsonObject.getString("Hisna_st"));
            cell.Email.setText(jsonObject.getString("E-mail"));

        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return convertView;
    }

    private class List<N> {
        TextView Ime;
        TextView Naslov;
        TextView HisnaSt;
        TextView Email;
    }
}
