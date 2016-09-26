package edu.buffalo.cse.cse486586.groupmessenger2;

import android.app.Activity;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * GroupMessengerActivity is the main Activity for the assignment.
 * 
 * @author SHIVA
 *
 * Reference used :
 * 1. http://java-bytes.blogspot.de/2012/04/priority-queue-in-java.html
 * 2. Sockets from Oracle website
 * 3. Android from Android developer website
 *
 */
public class GroupMessengerActivity extends Activity {

    // Counter variables

    int localSeq = 0;
    int MsgCounter = 0;
    int KeyInit = 0;

    int testcnt =0;
    int FaiureAvd = 0;

    ArrayList<String> AVDlist =
            new ArrayList<String>(Arrays.asList("11108", "11112", "11116", "11120", "11124"));

    ArrayList<Integer> ReplyList = new ArrayList<Integer>();
    ArrayList<String> FinalList = new ArrayList<String>();

    PriorityQueue<MsgEncoder> MsgList0 = new PriorityQueue<MsgEncoder>(10, new Comparator<MsgEncoder>() {public int compare(MsgEncoder M1, MsgEncoder M2){
        return (M1.getSeqNum() == M2.getSeqNum()) ? (Integer.valueOf(M2.getAvd() ).compareTo(M1.getAvd())) : Integer.valueOf(M1.getSeqNum()).compareTo(M2.getSeqNum());}});

    PriorityQueue<MsgEncoder> MsgList1 = new PriorityQueue<MsgEncoder>(10, new Comparator<MsgEncoder>() {public int compare(MsgEncoder M1, MsgEncoder M2){
        return (M1.getSeqNum() == M2.getSeqNum()) ? (Integer.valueOf(M2.getAvd() ).compareTo(M1.getAvd())) : Integer.valueOf(M1.getSeqNum()).compareTo(M2.getSeqNum());}});

    PriorityQueue<MsgEncoder> MsgList2 = new PriorityQueue<MsgEncoder>(10, new Comparator<MsgEncoder>() {public int compare(MsgEncoder M1, MsgEncoder M2){
        return (M1.getSeqNum() == M2.getSeqNum()) ? (Integer.valueOf(M2.getAvd() ).compareTo(M1.getAvd())) : Integer.valueOf(M1.getSeqNum()).compareTo(M2.getSeqNum());}});

    PriorityQueue<MsgEncoder> MsgList3 = new PriorityQueue<MsgEncoder>(10, new Comparator<MsgEncoder>() {public int compare(MsgEncoder M1, MsgEncoder M2){
        return (M1.getSeqNum() == M2.getSeqNum()) ? (Integer.valueOf(M2.getAvd() ).compareTo(M1.getAvd())) : Integer.valueOf(M1.getSeqNum()).compareTo(M2.getSeqNum());}});

    PriorityQueue<MsgEncoder> MsgList4 = new PriorityQueue<MsgEncoder>(10, new Comparator<MsgEncoder>() {public int compare(MsgEncoder M1, MsgEncoder M2){
        return (M1.getSeqNum() == M2.getSeqNum()) ? (Integer.valueOf(M2.getAvd() ).compareTo(M1.getAvd())) : Integer.valueOf(M1.getSeqNum()).compareTo(M2.getSeqNum());}});

    ArrayList<String> list0 = new ArrayList<String>();
    ArrayList<String> list1 = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    ArrayList<String> list3 = new ArrayList<String>();
    ArrayList<String> list4 = new ArrayList<String>();

    Boolean allReply = true;

    PriorityQueue<MsgEncoder> MsgQueue = new PriorityQueue<MsgEncoder>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_group_messenger, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_messenger);
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.button1).setOnClickListener(
                new OnPTestClickListener(tv, getContentResolver()));
        try {
            ServerSocket serverSocket = new ServerSocket(10000);
            new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, serverSocket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onSendClick(View view){

        EditText editText = (EditText) findViewById(R.id.editText1);
        String msg = editText.getText().toString() + "\n";
        if (!msg.trim().equals("")) {
            new QueryTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, msg);
        }
        editText.setText("");
    }

    private Uri buildUri(String scheme, String authority) {
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority(authority);
        uriBuilder.scheme(scheme);
        return uriBuilder.build();
    }

    private class ServerTask extends AsyncTask<ServerSocket, String, Void> {

        @Override
        protected Void doInBackground(ServerSocket... sockets) {

            // Key value for each message starting at 0.
            int KeyInit = 0;
            try {
                ServerSocket serverSocket = sockets[0];
                boolean Serverlistening = true;
                int Req = 0;
                TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
                String myPort = String.valueOf((Integer.parseInt(portStr) * 2));

                while (Serverlistening) {
                    testcnt += 1;

                    //Log.e("Server  ", " LISTENING");
                    Socket Ssocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(Ssocket.getInputStream()));
                    String response = "";
                    int ReplyMax = 0;
                    while ((response = in.readLine()) != null) {

                        String Rseq = response.substring(0, 3);
                        String Rtype = response.substring(3, 8);
                        String Rport = response.substring(8, 13);
                        String Rmsg = response.substring(13);

                        if (Rtype.equalsIgnoreCase("QUERY")) {
                            //Log.e("QUERY RECEIVED :", response);
                            String ReplyMsg = String.format("%03d", localSeq) + "REPLY" + myPort + Rmsg;
                            sendReply(ReplyMsg, Rport);
                        }

                        if (Rtype.equalsIgnoreCase("REPLY")) {
                            //Log.e("REPLY RECEIVED :", response);
                            if (Rport.equalsIgnoreCase("11108"))
                                list0.add(response);
                            if (Rport.equalsIgnoreCase("11112"))
                                list1.add(response);
                            if (Rport.equalsIgnoreCase("11116"))
                                list2.add(response);
                            if (Rport.equalsIgnoreCase("11120"))
                                list3.add(response);
                            if (Rport.equalsIgnoreCase("11124"))
                                list4.add(response);
                            //Log.e(Bef)
                            if (list0.isEmpty() || list1.isEmpty() || list2.isEmpty() || list3.isEmpty() || list4.isEmpty())
                                allReply = false;
                            else {
                                //Log.e("Before sort ", ReplyList.toString());
                                ReplyList.add(Integer.valueOf(list0.get(0).substring(0, 3)));
                                ReplyList.add(Integer.valueOf(list1.get(0).substring(0, 3)));
                                ReplyList.add(Integer.valueOf(list2.get(0).substring(0, 3)));
                                ReplyList.add(Integer.valueOf(list3.get(0).substring(0, 3)));
                                ReplyList.add(Integer.valueOf(list4.get(0).substring(0, 3)));
                                Collections.sort(ReplyList, Collections.reverseOrder());
                                ReplyMax = ReplyList.get(0);
                                //Log.e("ReplyList is : ", ReplyList.toString());
                                ReplyList.clear();
                                list0.remove(0);
                                list1.remove(0);
                                list2.remove(0);
                                list3.remove(0);
                                list4.remove(0);
                                String FinalMsg = String.format("%03d", ReplyMax) + "FINAL" + myPort + Rmsg;
                                postQuery(FinalMsg, AVDlist);
                            }
                        }
                        if (Rtype.equalsIgnoreCase("FINAL")) {
                            //Log.e("Final Msgs", response);
                            MsgCounter = Integer.valueOf(Rseq);

                            FinalList.add(response);
                            //MsgList0.add(new MsgEncoder(Integer.parseInt(Rseq), Rtype, Integer.parseInt(Rport), Rmsg));

                            if (Rport.equalsIgnoreCase("11108"))
                                MsgList0.add(new MsgEncoder(Integer.parseInt(Rseq), Rtype, Integer.parseInt(Rport), Rmsg));
                            if (Rport.equalsIgnoreCase("11112"))
                                MsgList1.add(new MsgEncoder(Integer.parseInt(Rseq), Rtype, Integer.parseInt(Rport), Rmsg));
                            if (Rport.equalsIgnoreCase("11116"))
                                MsgList2.add(new MsgEncoder(Integer.parseInt(Rseq), Rtype, Integer.parseInt(Rport), Rmsg));
                            if (Rport.equalsIgnoreCase("11120"))
                                MsgList3.add(new MsgEncoder(Integer.parseInt(Rseq), Rtype, Integer.parseInt(Rport), Rmsg));
                            if (Rport.equalsIgnoreCase("11124"))
                                MsgList4.add(new MsgEncoder(Integer.parseInt(Rseq), Rtype, Integer.parseInt(Rport), Rmsg));

                            localSeq += 1;

                        }

                    }

                    // Log.e("FinalList ", FinalList.toString());
                    in.close();
                    Ssocket.close();
                    while (!(MsgList0.isEmpty() || MsgList1.isEmpty() || MsgList2.isEmpty() ||
                            MsgList3.isEmpty() || MsgList4.isEmpty())) {

                        MsgEncoder Msginsert0 = MsgList0.poll();
                        insert(KeyInit, Msginsert0.getMsgText());
                        KeyInit += 1;

                        MsgEncoder Msginsert1 = MsgList1.poll();
                        insert(KeyInit, Msginsert1.getMsgText());
                        KeyInit += 1;

                        MsgEncoder Msginsert2 = MsgList2.poll();
                        insert(KeyInit, Msginsert2.getMsgText());
                        KeyInit += 1;

                        MsgEncoder Msginsert3 = MsgList3.poll();
                        insert(KeyInit, Msginsert3.getMsgText());
                        KeyInit += 1;

                        MsgEncoder Msginsert4 = MsgList4.poll();
                        insert(KeyInit, Msginsert4.getMsgText());
                        KeyInit += 1;
                    }
// Writing 2nd part
/*
                    switch (FaiureAvd) {
                        case 1:

                            break;
                        case 2:

                            break;
                        default:
                    }
*/
// Ending here
                }
                Log.e("Server Status:", serverSocket.getInetAddress().toString());
            } catch (Exception e) {
                Log.e("SERVER EXCEPTION", "Exception caught when trying to listen on port ");
                e.printStackTrace();
            }
            return null;
        }

        private void sendReply(String ReplyMsg, String Rport) {
            new ReplyTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,Rport, ReplyMsg);

        }

        protected void onProgressUpdate(String...strings) {

            String strReceived = strings[0].trim();
            TextView textView = (TextView) findViewById(R.id.textView1);
            textView.setMovementMethod(new ScrollingMovementMethod());
            textView.append(strReceived + "\n");
        }

        public void insert(int KeyInit, String response){
            ContentValues cv = new ContentValues();
            cv.put("key", KeyInit);
            cv.put("value", response.trim());
            Uri DbUri = buildUri("content", "edu.buffalo.cse.cse486586.groupmessenger2.provider");
            getContentResolver().insert(DbUri, cv);
            //Log.e("inserted Values", cv.toString());
            //publishProgress(response.trim());
            }
    }

    private class QueryTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {

            TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
            String myPort = String.valueOf((Integer.parseInt(portStr) * 2));
            String QueryMsg = String.format("%03d", KeyInit) + "QUERY"+ myPort + msgs[0];
            postQuery(QueryMsg, AVDlist);
            return null;
        }
    }



    private class ReplyTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {
            boolean notAgreed = true;
            try {
                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                Socket Csocket = new Socket(inet, Integer.parseInt(msgs[0]));
                Csocket.setSoTimeout(500);
                PrintWriter out = new PrintWriter(Csocket.getOutputStream(), true);
                String ReplyMsg = msgs[1];
                out.write(ReplyMsg.trim()+"\n");
                //Log.e("REPLY SENT :", ReplyMsg);
                out.close();
                Csocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("EXCEPTION CLIENT reply ",msgs[0] );
            }
            return null;
        }
    }

    public void postQuery(String msgToSend, List AVDlist){
        for(Object E : AVDlist){
        try {
                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                Socket Csocket = new Socket(inet, Integer.parseInt(E.toString()));
                Csocket.setSoTimeout(100);
                PrintWriter out = new PrintWriter(Csocket.getOutputStream(), true);
                out.write(msgToSend.trim()+"\n");
                out.close();
                Csocket.close();
            }catch (IOException e) {
            e.printStackTrace();
            Log.e("EXCEPTION CLIENT Query:", E.toString() );
            }
        }

    }

}
