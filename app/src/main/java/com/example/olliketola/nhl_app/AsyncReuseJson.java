package com.example.olliketola.nhl_app;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class AsyncReuseJson extends AsyncTask<Void, Void, Void> {

    public GetResponse getResponse = null;
    String response = "{\"status\":\"0\",\"msg\":\"Sorry something went wrong try again\",\"type\":\"asyncError\"}";
    JSONObject jsonObject;
    String URLs;
    boolean dialogE = true;
    Activity activity;
    // Dialog builder
    private ProgressDialog Dialog;

    public AsyncReuseJson(String url, boolean dialog, Activity activity1) {
        URLs = url;
        dialogE = dialog;
        activity = activity1;
    }

    public AsyncReuseJson(String url, boolean dialog) {
        URLs = url;
        dialogE = dialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (dialogE) {
            if (Dialog == null) {
                Dialog = new ProgressDialog(activity);
                Dialog.setMessage("Please Wait..");
                Dialog.setCancelable(false);
                Dialog.show();
            }
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL(URLs);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer responseData = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                        responseData.append(line);
                        responseData.append('\r');
                    }
                    rd.close();
                    response = responseData.toString();

            closeDialog(dialogE);
        } catch (UnsupportedEncodingException e) {
            closeDialog(dialogE);
            e.printStackTrace();
        } catch (MalformedURLException e) {
            closeDialog(dialogE);
            e.printStackTrace();
        } catch (ProtocolException e) {
            closeDialog(dialogE);
            e.printStackTrace();
        } catch (IOException e) {
            closeDialog(dialogE);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
        if (Dialog != null) {
            Dialog.dismiss();
        }
        Log.e("Response", "response" + response);
        if (response.equals("null")) {
            response = "{\"status\":\"0\",\"msg\":\"No data to show.\"}";
            getResponse.getData(response);
        } else
            getResponse.getData(response);

    }

    private void closeDialog(boolean flag) {
        if (flag) {
            Dialog.dismiss();
        } else {
            /* Nothing to do*/
        }
    }
}