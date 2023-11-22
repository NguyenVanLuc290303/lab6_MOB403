package com.example.lab6_mob403;


import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebserviceCall {

    private static final String TAG = WebserviceCall.class.getSimpleName();
    public static String callWebServiceThreadSoap(String strURL, String strSoapAction , SoapObject soapObject){

//        try {
//            StringBuffer stringBuffer;
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            envelope.dotNet = true;
//            envelope.setOutputSoapObject(soapObject);
//            HttpTransportSE httpTransportSE = new HttpTransportSE(strURL);
//            httpTransportSE.debug = true;
//            try {
//                httpTransportSE.call(strSoapAction,envelope);
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            }
//            SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
//            stringBuffer = new StringBuffer(soapPrimitive.toString());
//            Log.i(TAG, "result: " + stringBuffer.toString());
//            return  stringBuffer.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//
//        }


        try {
            StringBuilder result = new StringBuilder();
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.encodingStyle = "utf-8";
            envelope.bodyOut = soapObject;
            envelope.dotNet = true;
            envelope.setOutputSoapObject(soapObject);
            HttpTransportSE ht = new HttpTransportSE(strURL);
            ht.debug = true;
            ht.call(strSoapAction, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            if (response != null) {
                result.append(response.toString());
            }
            Log.i(TAG, "result: " + result);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
