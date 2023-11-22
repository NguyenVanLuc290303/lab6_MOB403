package com.example.lab6_mob403;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.serialization.SoapObject;

public class ConvertTemperatureTask extends AsyncTask<String , Void ,String> {
    ProgressDialog dialog;
    MainActivity mainActivity;

    String soapAction;
    String httpURL;
    String methodName;
    String paramsName;

    public ConvertTemperatureTask(MainActivity mainActivity, String soapAction, String methodName, String paramsName) {
        this.mainActivity = mainActivity;
        this.soapAction = soapAction;
        this.methodName = methodName;
        this.paramsName = paramsName;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(mainActivity);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        SoapObject soapObject = new SoapObject(Constants.NAME_SPACE,methodName);

        //add properties for soap object
        soapObject.addProperty(paramsName, strings[0]);

        return WebserviceCall.callWebServiceThreadSoap(Constants.URL,soapAction,soapObject);



    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if (s == null) {
            Log.i("check", "cannot get result");
        } else {
            Log.i("check", s);
            mainActivity.callBackDataFromAsyncTask(s);
        }
    }
}
