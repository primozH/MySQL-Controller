package optimus_primus.si.mysqlcontroler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Primož on 30.3.2015.
 */
public class ApiConnector {

    private String ip = "http://192.168.2.101";

    public JSONArray GetAll(){
        String url = ip+"/android/select_all.php";

        HttpEntity httpEntity = null;

        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
        }
        catch (ClientProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        JSONArray jsonArray = null;
        if(httpEntity!=null){
            try{
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("odziv: ",entityResponse);
                jsonArray = new JSONArray(entityResponse);
            }catch (JSONException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public JSONArray GetDetails(int zaposleniID){
        String url = ip+"/android/details.php?ID_zaposleni="+zaposleniID;

        HttpEntity httpEntity = null;

        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
        }
        catch (ClientProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        JSONArray jsonArray = null;
        if(httpEntity!=null){
            try{
                String entityResponse = EntityUtils.toString(httpEntity);
                Log.e("odziv: ",entityResponse);
                jsonArray = new JSONArray(entityResponse);
            }catch (JSONException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public String Update(ArrayList<NameValuePair> params){
        String url = ip+"/android/update.php";

        HttpEntity httpEntity = null;
        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();

        } catch (ClientProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        String entityResponse = null;
        if(httpEntity!=null) {
            try {
                entityResponse = EntityUtils.toString(httpEntity);
                Log.e("odziv: " , entityResponse);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return entityResponse;
    }

    public String Insert(ArrayList<NameValuePair> params){
        String url = ip+"/android/insert.php";

        HttpEntity httpEntity = null;

        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
        }
         catch (ClientProtocolException e){
         e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        String entityResponse = null;
        if(httpEntity!=null) {
            try {
                entityResponse = EntityUtils.toString(httpEntity);
                Log.e("odziv: " , entityResponse);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return entityResponse;
    }

    public String Delete(int zaposleniID){
        String url = ip+"/android/delete.php?ID_zaposleni="+zaposleniID;

        HttpEntity httpEntity = null;

        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
        }
        catch (ClientProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        String entityResponse = null;
        if(httpEntity!=null){
            try{
                entityResponse = EntityUtils.toString(httpEntity);
                Log.e("odziv: ",entityResponse);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        return entityResponse;

    }
}