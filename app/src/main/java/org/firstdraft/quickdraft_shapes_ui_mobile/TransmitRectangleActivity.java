package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;

import org.firstdraft.draw_transmit_shapes.R;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TransmitRectangleActivity extends AppCompatActivity {

    String SERVER_IP;
    String DJANGO_PORT;

    private ProgressDialog pDialog;
    int url_timeout;//milliseconds

    String user_name;
    String file_name_office;
    String file_name_xml;

    String request_url;

    String file_content = null;

    final int DEFAULT_TIMEOUT = 20;//20 seconds

    NetworkThread_SendShapeDocument background_task_ssd;
    NetworkThread_NotifyShapeXml background_task_nsx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shape_list);

        /*Log.d("SHAPES","Width - " + TransmitRectangleUtility.base_width);
        Log.d("SHAPES","Height - " + TransmitRectangleUtility.base_height);
        Log.d("SHAPES","Deviation - " + TransmitRectangleUtility.horizontal_deviation);

        Log.d("SHAPES","Text - " + TransmitRectangleUtility.shape_text);*/

        String xml = TransmitRectangleUtility.get_xml();
        Log.d("SHAPES","XML - " + TransmitRectangleUtility.get_xml());

        file_content = xml;
        //user_name = "shape_master";
        user_name = TransmitRectangleUtility.user_name;

        launch_sendImport_background_task();
    }

    public void launch_sendImport_background_task()
    {
        SERVER_IP = "52.15.242.131";
        DJANGO_PORT = "8000";

        //file_name = "pretty_shape_file_final.docx";
        file_name_office = TransmitRectangleUtility.file_name;

        this.request_url = "http://" + SERVER_IP  + ":" + DJANGO_PORT
                + "/document_with_shape/";

        url_timeout = DEFAULT_TIMEOUT * 1000;

        start_dialog();

        network_call_sendShapeDocument();
    }

    public void launch_notifyShape_background_task()
    {
        SERVER_IP = "52.15.242.131";
        DJANGO_PORT = "8000";

        //user_name = "shape_master";
        //file_name = "pretty_shape_file_final.docx";
        //file_name_xml = TransmitRectangleUtility.file_name;

        this.request_url = "http://" + SERVER_IP  + ":" + DJANGO_PORT
                + "/document_with_shape/";

        url_timeout = DEFAULT_TIMEOUT * 1000;

        start_dialog();

        network_call_notify_shape_xml();

    }

    private void start_dialog() {

        pDialog = new ProgressDialog(this);

        pDialog.setMessage("Sending Shape Request....");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(true);
        pDialog.show();

    }

    public void network_call_sendShapeDocument()
    {
        background_task_ssd = new NetworkThread_SendShapeDocument();
        background_task_ssd.execute("");
    }

    public void network_call_notify_shape_xml()
    {
        background_task_nsx = new NetworkThread_NotifyShapeXml();
        background_task_nsx.execute("");
    }

    class NetworkThread_SendShapeDocument extends AsyncTask<String, String, String> {
        final int RESPONSE_BUFFER_SIZE = 100;

        private String boundary = "===" + System.currentTimeMillis() + "===";
        ;
        private static final String LINE_FEED = "\r\n";

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setMessage("Sending Shape Request....");
        }

        protected void onProgressUpdate(String... progress) {
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        public String multiFormAddKeyValuePair(String original_string,String key,String value)
        {
            original_string +=  ("--" + boundary + LINE_FEED);
            original_string +=  ("Content-Disposition: form-data; name=\"" + key + "\"" + LINE_FEED);
            original_string += LINE_FEED;
            original_string +=  (value);
            return original_string;
        }

        public void multiFormInit(HttpURLConnection url_connection) throws Exception
        {
            url_connection.setRequestMethod("POST");
            url_connection.setUseCaches(false);
            url_connection.setDoOutput(true);    // indicates POST method
            url_connection.setDoInput(true);
            url_connection.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);
        }

        private void send_file_content(String request_body,OutputStream outputStream) throws Exception
        {
            if(file_content != null) {
                request_body += multiFormAddKeyValuePair(request_body, "filecontent", file_content);
                outputStream.write(request_body.getBytes());
                outputStream.flush();
            }
        }

        String sendUrlOnWire(String request_url)
        {
            String response = "No response";
            try {

                URL local_url = new URL(request_url);

                publishProgress("" + (int)(20 * 100)/(40));

                HttpURLConnection url_connection = (HttpURLConnection) local_url.openConnection();//Open Url Connection
                    multiFormInit(url_connection);
                url_connection.setConnectTimeout(url_timeout);
                url_connection.connect();//connect the URL Connection

                String request_body = "";

                OutputStream outputStream = url_connection.getOutputStream();
                outputStream.write(request_body.getBytes());

                request_body = "";

                request_body +=
                        multiFormAddKeyValuePair
                                (request_body,"operation","create_shape_xml");

                request_body +=
                        multiFormAddKeyValuePair(request_body,"username",user_name);
                outputStream.write(request_body.getBytes());
                outputStream.flush();

                if(file_name_office != null) {
                    request_body +=
                            multiFormAddKeyValuePair
                                    (request_body, "filename", file_name_office);
                    outputStream.write(request_body.getBytes());
                    outputStream.flush();
                }

                send_file_content(request_body,outputStream);

                if (url_connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    //For debugging
                    int a = 2;
                }

                InputStream url_stream = url_connection.getInputStream();//Get InputStream for connection

                byte[] url_buffer = new byte[RESPONSE_BUFFER_SIZE];//Set buffer type
                int response_len = 0;//init length
                response_len = url_stream.read(url_buffer);
                response = new String(url_buffer,0,response_len);

                return response;
            }
            catch (Exception e){
                return "FAILURE";
            }

            finally {
                return response;
            }

        }

        public void loop_for_configured_time() throws Exception {
            try {
                Thread.sleep((int)(3000.0));
            }
            catch (Exception e){
                throw new Exception("ConnectionError");
            }
        }

        public String doInBackground(String... file_url_array) {

            try {
                    loop_for_configured_time();

                    String response_string = sendUrlOnWire(request_url);
                    file_name_xml = response_string;

            }
            catch (Exception e){
                return "ConnectionError";
            }
            return null;

        }

        @Override
        protected void onPostExecute(String file_url) {
            //pDialog.dismiss();
            //TransmitRectangleActivity.super.finish();

            launch_notifyShape_background_task();
        }

    }

    class NetworkThread_NotifyShapeXml extends AsyncTask<String, String, String> {
        final int RESPONSE_BUFFER_SIZE = 100;

        private String boundary = "===" + System.currentTimeMillis() + "===";
        ;
        private static final String LINE_FEED = "\r\n";

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setMessage("Notifying Shape XML....");
        }

        protected void onProgressUpdate(String... progress) {
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        public String multiFormAddKeyValuePair(String original_string,String key,String value)
        {
            original_string +=  ("--" + boundary + LINE_FEED);
            original_string +=  ("Content-Disposition: form-data; name=\"" + key + "\"" + LINE_FEED);
            original_string += LINE_FEED;
            original_string +=  (value);
            return original_string;
        }

        public void multiFormInit(HttpURLConnection url_connection) throws Exception
        {
            url_connection.setRequestMethod("POST");
            url_connection.setUseCaches(false);
            url_connection.setDoOutput(true);    // indicates POST method
            url_connection.setDoInput(true);
            url_connection.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);
        }

        String sendUrlOnWire(String request_url)
        {
            String response = "No response";
            try {

                URL local_url = new URL(request_url);

                publishProgress("" + (int)(20 * 100)/(40));

                HttpURLConnection url_connection = (HttpURLConnection) local_url.openConnection();//Open Url Connection
                multiFormInit(url_connection);
                url_connection.setConnectTimeout(url_timeout);
                url_connection.connect();//connect the URL Connection

                String request_body = "";

                request_body +=
                        multiFormAddKeyValuePair
                                (request_body,"operation","shape_from_mobile_notify");

                OutputStream outputStream = url_connection.getOutputStream();
                outputStream.write(request_body.getBytes());

                request_body = "";
                request_body +=
                        multiFormAddKeyValuePair(request_body,"username",user_name);
                outputStream.write(request_body.getBytes());
                outputStream.flush();

                if(file_name_xml != null) {
                    request_body +=
                            multiFormAddKeyValuePair
                                    (request_body, "filename", file_name_xml);
                    outputStream.write(request_body.getBytes());
                    outputStream.flush();
                }

                if (url_connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    //For debugging
                    int a = 2;
                }

                InputStream url_stream = url_connection.getInputStream();//Get InputStream for connection

                byte[] url_buffer = new byte[RESPONSE_BUFFER_SIZE];//Set buffer type
                int response_len = 0;//init length
                response_len = url_stream.read(url_buffer);
                response = new String(url_buffer,0,response_len);

                return response;
            }
            catch (Exception e){
                return "FAILURE";
            }

            finally {
                return response;
            }

        }

        public void loop_for_configured_time() throws Exception {
            try {
                Thread.sleep((int)(3000.0));
            }
            catch (Exception e){
                throw new Exception("ConnectionError");
            }
        }

        public String doInBackground(String... file_url_array) {

            try {
                //Remove it after end to end logic
                loop_for_configured_time();

                //response_string = sendUrlOnWire(request_url);
                sendUrlOnWire(request_url);

            }
            catch (Exception e){
                return "ConnectionError";
            }
            return null;

        }

        @Override
        protected void onPostExecute(String file_url) {

            pDialog.dismiss();
            TransmitRectangleActivity.super.finish();

        }
    }

}