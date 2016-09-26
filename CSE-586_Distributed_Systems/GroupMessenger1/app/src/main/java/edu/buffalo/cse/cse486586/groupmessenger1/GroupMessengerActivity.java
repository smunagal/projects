package edu.buffalo.cse.cse486586.groupmessenger1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * GroupMessengerActivity is the main Activity for the assignment.
 * 
 * @author stevko
 *
 */
public class GroupMessengerActivity extends Activity {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_group_messenger, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_messenger);

        Log.e("Server creation", "CREATING");
        /*
         * TODO: Use the TextView to display your messages. Though there is no grading component
         * on how you display the messages, if you implement it, it'll make your debugging easier.
         */
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setMovementMethod(new ScrollingMovementMethod());
        /*
         * Registers OnPTestClickListener for "button1" in the layout, which is the "PTest" button.
         * OnPTestClickListener demonstrates how to access a ContentProvider.
         */
        findViewById(R.id.button1).setOnClickListener(
                new OnPTestClickListener(tv, getContentResolver()));

        /*
         * TODO: You need to register and implement an OnClickListener for the "Send" button.
         * In your implementation you need to get the message from the input box (EditText)
         * and send it to other AVDs.
         */
        try {
            ServerSocket serverSocket = new ServerSocket(10000);
            new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, serverSocket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * Registered onClick method in layout/activity_group_messenger.xml  file.
     *
     * android:onClick="onSendClick"
     *
     * This method will be called when Send button android:id ="button4" is called
     *
     * @param view
     */
    public void onSendClick(View view){

        // reading value from editable field. And reseting it for next message.

        EditText editText = (EditText) findViewById(R.id.editText1);
        String msg = editText.getText().toString() + "\n";
        editText.setText("");

        // Performing client task in below method.
        postMessage(msg);

    }

    public void postMessage(String msg){
        // If messeage not empty
        if (!msg.trim().equals(""))
        new ClientTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, msg);

    }

    private class ServerTask extends AsyncTask<ServerSocket, String, Void> {

        @Override
        protected Void doInBackground(ServerSocket... sockets) {

            // Key value for each message starting at 0.
            int KeyInit = 0;
            try {

                TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                Log.e("tel.getLine1Number() : ", tel.getLine1Number());
                String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
                Log.e("portStr : ", portStr);
                String myPort = String.valueOf((Integer.parseInt(portStr) * 2));
                Log.e("myPort : ", myPort);

                ServerSocket serverSocket = sockets[0];
                /**
                 * Since there is only one ServerTask created when app is start , we use infinite
                 * while loop to keep the Server socket to listen to client.
                 * and every time when the server recieves a message we publish it to OnProgressUpdate
                 * method
                 *
                 * Code References: "https://docs.oracle.com/javase/tutorial/networking/sockets/"
                 */
                boolean Serverlistening = true;

                while(Serverlistening) {
                    Socket Ssocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(Ssocket.getInputStream()));
                    String response = "";
                    while ((response = in.readLine()) != null) {

                        //Log.e("SERVER Recieved msg:", response);

                        // Inserting into Table;
                        ContentValues cv = new ContentValues();
                        cv.put("key", KeyInit);
                        cv.put("value", response.trim());

                        // Executing Insert method of ContentProvider by calling ContentResolver
                        Uri DbUri = buildUri("content", "edu.buffalo.cse.cse486586.groupmessenger1.provider");
                        //Log.e("INSERTING RESPONSE", cv.toString());
                        getContentResolver().insert(DbUri,cv);
                        publishProgress(response.trim());
                        KeyInit += 1;
                    }
                    in.close();
                    Ssocket.close();
                }
                Log.e("Server Status:", serverSocket.getInetAddress().toString());
            } catch (IOException e) {
                Log.e("SERVER EXCEPTION", "Exception caught when trying to listen on port ");
            }
            return null;
        }

        // This Code is from PA1 Template. USED TO PUBLISH PROGRESS
        // The Textview1 is the defined layout id for displaying messages in the given xml.


        protected void onProgressUpdate(String...strings) {

            String strReceived = strings[0].trim();
            TextView textView = (TextView) findViewById(R.id.textView1);
            textView.setMovementMethod(new ScrollingMovementMethod());
            textView.append(strReceived + "\n");

        }
    }

    private class ClientTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {
            String REMOTE_PORT0 = "11108";
            String REMOTE_PORT1 = "11112";
            String REMOTE_PORT2 = "11116";
            String REMOTE_PORT3 = "11120";
            String REMOTE_PORT4 = "11124";
            try {

                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                String msgToSend = msgs[0];

                // Getting messages and casting it to all avds including itself
                // Ports are hardCoded.

                // Msg avd0
                Socket Csocket0 = new Socket(inet, Integer.parseInt(REMOTE_PORT0));
                PrintWriter out0 = new PrintWriter(Csocket0.getOutputStream(), true);
                out0.write(msgToSend.trim()+"\n");
                //Log.e("CLIENT msg sent 0 :", msgToSend);
                out0.close();
                Csocket0.close();

                // Msg avd1
                Socket Csocket1 = new Socket(inet, Integer.parseInt(REMOTE_PORT1));
                PrintWriter out1 = new PrintWriter(Csocket1.getOutputStream(), true);
                out1.write(msgToSend.trim()+"\n");
                // Log.e("CLIENT msg sent Self:", msgToSend);
                out1.close();
                Csocket1.close();

                // Msg avd2
                Socket Csocket2 = new Socket(inet, Integer.parseInt(REMOTE_PORT2));
                PrintWriter out2 = new PrintWriter(Csocket2.getOutputStream(), true);
                out2.write(msgToSend.trim()+"\n");
                //Log.e("CLIENT msg sent Self:", msgToSend);
                out2.close();
                Csocket2.close();


                // Msg avd3
                Socket Csocket3 = new Socket(inet, Integer.parseInt(REMOTE_PORT3));
                PrintWriter out3 = new PrintWriter(Csocket3.getOutputStream(), true);
                out3.write(msgToSend.trim()+"\n");
                //Log.e("CLIENT msg sent Self:", msgToSend);
                out3.close();
                Csocket3.close();

                // Msg avd4
                Socket Csocket4 = new Socket(inet, Integer.parseInt(REMOTE_PORT4));
                PrintWriter out4 = new PrintWriter(Csocket4.getOutputStream(), true);
                out4.write(msgToSend.trim()+"\n");
                //Log.e("CLIENT msg sent Self:", msgToSend);
                out4.close();
                Csocket4.close();


            } catch (UnknownHostException e) {
                Log.e("EXCEPTION CLIENT TAST ", "ClientTask UnknownHostException");
            } catch (IOException e) {
                Log.e("EXCEPTION CLIENT TAST ", "ClientTask socket IOException");
            }

            return null;
        }
    }
    private Uri buildUri(String scheme, String authority) {
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority(authority);
        uriBuilder.scheme(scheme);
        return uriBuilder.build();
    }
}
