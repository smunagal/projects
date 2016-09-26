package edu.buffalo.cse.cse486586.simpledynamo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.cookie.CookieAttributeHandler;

/**
 *  References:
 *  Cursors:
 *  1. http://developer.android.com/reference/android/database/MatrixCursor.html
 *  2. http://developer.android.com/reference/android/database/Cursor.html
 *  3. http://stackoverflow.com/questions/2810615/how-to-retrieve-data-from-cursor-class
 */


public class SimpleDynamoProvider extends ContentProvider {

    public static final String TABLE_NAME = "GROUP_TABLE";

    public static int NODE_ID = 0;
    public static int NODE_S1 = 0;
    public static int NODE_S2 = 0;
    public static int NODE_P1 = 0;
    public static int NODE_P2 = 0;
    public static int FIRST_NODE = 0;
    public static int LAST_NODE = 0;

    public static String[] Starcolumns = new String[]{"key", "value"};
    public static MatrixCursor StarCursor = new MatrixCursor(Starcolumns);
    public static int star_cpunt = 0;
    public static boolean wait_starReply = true;
    public static boolean wait_Qreply = true;
    public int star_reply_cpunt =0;

    public static String QRESPONSE ="";

//    public static Set<String> S1 = new HashSet<String>();
//    public static Set<String> S2 = new HashSet<String>();
//    public static Set<String> P1 = new HashSet<String>();
//    public static Set<String> P2 = new HashSet<String>();


    public static ArrayList<Integer> Dynamo_Nodes = new ArrayList<Integer>(Arrays.asList(5562, 5556, 5554, 5558, 5560));
    @Override
    public boolean onCreate() {

        try {
            getContext().deleteDatabase("GROUPTABLE.db");
//            Log.e("DB :" , "DELETED");
            TelephonyManager tel = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
            String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
            Log.e("Emulator Number : ", portStr);
            NODE_ID = Integer.parseInt(portStr);
            FIRST_NODE = Dynamo_Nodes.get(0);
            LAST_NODE = Dynamo_Nodes.get(4);
            // Find out 2 successors for replication and 1 predecessor


            int index = Dynamo_Nodes.indexOf(NODE_ID);
            NODE_S1 = Dynamo_Nodes.get((index+1)%Dynamo_Nodes.size());
            NODE_S2 = Dynamo_Nodes.get((index+2)%Dynamo_Nodes.size());
            if(index-2 < 0) {
                NODE_P1 = (Dynamo_Nodes.get((5-1+index) % Dynamo_Nodes.size()));
            }else{
                NODE_P1 =(Dynamo_Nodes.get((index - 1) % Dynamo_Nodes.size()));
            }
            for(int nodes : Dynamo_Nodes){
                if(nodes != NODE_ID && nodes != NODE_P1 && nodes != NODE_S1 && nodes !=NODE_S2)
                    NODE_P2 = nodes;
            }
//            Log.e("ON_CREATE", "Successor1 :" + NODE_S1 + "  Successor2 :" + NODE_S2 + "  Predecessor 1  :" + NODE_P1 + "  Predecessor 2  :" + NODE_P2);
            new WakeUpTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
            new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ServerSocket(10000));


        } // End- try block
        catch (Exception e) {
            Log.e("Exception :", "exception");
            e.printStackTrace();
        } // End catch block
        return false;
    }

    @Override
    public synchronized Uri insert(Uri uri, ContentValues values) {
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getWritableDatabase();
        String key = getKey(values);
        String value = getValue(values);

        int target_node = getTargerNode(key);
        if(target_node == NODE_ID){
            // Insert and send to NODE_S1 & NODE_S2
            long rowId = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

			Log.e("INSERT TARGET:" , values.toString());
            // Forward to S1 and S2
            try {
                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                // Write S1
                Socket socket1 = new Socket(inet, (NODE_S1) * 2);
                PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
                out1.write("INSERT"+"_"+key+"_"+value);
                out1.close();
                socket1.close();
                // Write S2
                Socket socket2 = new Socket(inet, (NODE_S2) * 2);
                PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
                out2.write("INSERT"+"_"+key+"_"+value);
                out2.close();
                socket2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //verify  existence of target AVD and send to Target avd
            try{
                // TO Target AVD
                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                Socket socket = new Socket(inet, (target_node) * 2);
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.write("FINSERT" + "_" + key + "_" + value);
                out.flush();
                out.close();
                socket.close();

                int target_index = Dynamo_Nodes.indexOf(target_node);
                int target_S1 = Dynamo_Nodes.get((target_index + 1) % Dynamo_Nodes.size());
                //	int target_S2 = Dynamo_Nodes.get((target_index + 2) % Dynamo_Nodes.size());

                // To Target AVD _S1 and S1 will forward to S2
                Socket socket1 = new Socket(inet, (target_S1) * 2);
                PrintWriter out1 = new PrintWriter(socket1.getOutputStream());
                out1.write("F1INSERT" + "_" + key + "_" + value);
                out1.flush();
                out1.close();
                socket1.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        db.close();
        return null;
    }

    @Override
    public synchronized Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                                     String sortOrder) {
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();
        String key = selection;
        Log.e("QUERY ARGS :", selection);
        try {
            // Local Dump
            if (selection.equalsIgnoreCase("@")) {
//                Log.e("Query ARGS  :", key);
                Cursor cursor = db.rawQuery("select * from GROUP_TABLE", null);
                cursor.moveToFirst();
                db.close();
                return cursor;
            }
            // Global Dump
            else if (selection.equalsIgnoreCase("*")) {
                Cursor cursor = db.rawQuery("select * from GROUP_TABLE", null);
                cursor.moveToFirst();
                db.close();

                //Insert self <k,v> to Star Cursor ;
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    String k = cursor.getString(cursor.getColumnIndex("key"));
                    String v = cursor.getString(cursor.getColumnIndex("value"));
                    StarCursor.addRow(new String[]{k, v});
                }

                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});

                // Check star reply count : i.e active avd's.
                for (Integer node : Dynamo_Nodes) {
                    if (node != NODE_ID) {
                        Socket socket = new Socket(inet, node * 2);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//						Log.e("STAR", " PING --->");
                        out.write("PING" + "_" + NODE_ID + "_" + key + "\n");
                        out.flush();
                        String reply = in.readLine();
                        if (reply != null) {
//							AVD IS ACTIVE
                            in.close();
                            out.close();
                            socket.close();
                            // ASK FOR LOCAL DUMP
                            Socket socketQ = new Socket(inet, node * 2);
                            PrintWriter outQ = new PrintWriter(socketQ.getOutputStream());
                            BufferedReader inQ = new BufferedReader(new InputStreamReader(socketQ.getInputStream()));
                            outQ.write("STARNEW" + "_" + NODE_ID + "\n");
                            outQ.flush();
                            String[] STAR_REPLY = inQ.readLine().split("_");
                            while (!STAR_REPLY[1].equalsIgnoreCase("EMPTY")) {
                                StarCursor.addRow(new String[]{STAR_REPLY[1], STAR_REPLY[2]});
                                STAR_REPLY = inQ.readLine().split("_");
                            }
                            inQ.close();
                            outQ.close();
                            socketQ.close();
                        }
                        in.close();
                        out.close();
                        socket.close();
                    }
                }


//				// send message to all other avds except self
//				String star_msg = "QSTAR" + "_" + NODE_ID;
//				for(Integer node : Dynamo_Nodes) {
//					if (node != NODE_ID) {
//						Socket socket = new Socket(inet, node * 2);
//						PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//						out.write(star_msg + "\n");
//						out.close();
//						socket.close();
//					}
//				}
//
//
//					// Wait for their reply
//				while(wait_starReply){
//
//				}
                // Re-initialize values before exit
                db.close();
                return StarCursor;

            } else {
                int target_avd = getTargerNode(key);
                if (target_avd == NODE_ID) {
                    String[] Projection = {"key", "value"};
                    String Selection = "key" + "=?";
                    String SelectionArgs[] = new String[]{selection};
                    Cursor cursor = db.query(
                            TABLE_NAME,     // Table name = group_table
                            Projection,     // key , value columns are requested. null would also return both.
                            Selection,      // SQL SYNTAX : WHERE key =
                            SelectionArgs,  // argument for key column
                            null,
                            null,
                            sortOrder);
                    cursor.moveToFirst();
                    db.close();
//                    Log.e("cursor  : ", DatabaseUtils.dumpCursorToString(cursor));
                    if(cursor.getCount() == 0){
                        InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                            // Query replica if data not ready
                            Socket socket2 = new Socket(inet, NODE_S1 * 2);
                            BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
                            PrintWriter out2 = new PrintWriter(socket2.getOutputStream());
                            out2.write("QUERYNEW" + "_" + NODE_ID + "_" + key + "\n");
                            out2.flush();
                            String reply2 = in2.readLine();
                            MatrixCursor matrixCursor = new MatrixCursor(Starcolumns);
                            String[] res = reply2.split("_");
                            matrixCursor.addRow(new String[]{res[1], res[2]});
                            in2.close();
                            out2.close();
                            socket2.close();
                            db.close();
                            Log.e(" TARGET NODE_S1: ", DatabaseUtils.dumpCursorToString(matrixCursor));
                            return matrixCursor;

//                        else{
//
//                            in.close();
//                            out.close();
//                            socket.close();
//
//                            // Query Target Replica
//
//                            Socket socket2 = new Socket(inet, NODE_S1 * 2);
//                            BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
//                            PrintWriter out2 = new PrintWriter(socket2.getOutputStream());
//                            out2.write("QUERYNEW" + "_" + NODE_ID + "_" + key + "\n");
//                            out2.flush();
//                            String reply2 = in2.readLine();
//                            MatrixCursor matrixCursor = new MatrixCursor(Starcolumns);
//                            String[] res = reply2.split("_");
//                            matrixCursor.addRow(new String[]{res[1], res[2]});
//                            in2.close();
//                            out2.close();
//                            socket2.close();
//                            db.close();
//                            Log.e("Target NODE_S1  : ", DatabaseUtils.dumpCursorToString(matrixCursor));
//                            return matrixCursor;
//
//                        }
                    }
                    return cursor;

                } else {
                    InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                    // check if target is active
                    Socket socket = new Socket(inet, target_avd * 2);
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out.write("PING" + "_" + NODE_ID + "_" + key + "\n");
                    out.flush();
                    String reply = in.readLine();

                    if (reply != null) {
                        in.close();
                        out.close();
                        socket.close();

                        // Target AVD Active

                        // OPEN NEW CONNECTION

                        Socket socket2 = new Socket(inet, target_avd * 2);
                        BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
                        PrintWriter out2 = new PrintWriter(socket2.getOutputStream());
                        out2.write("QUERYNEW" + "_" + NODE_ID + "_" + key + "\n");
                        out2.flush();
                        String reply2 = in2.readLine();
                        if(reply2 != null) {
                            MatrixCursor matrixCursor = new MatrixCursor(Starcolumns);
                            String[] res = reply2.split("_");
                            matrixCursor.addRow(new String[]{res[1], res[2]});
                            in2.close();
                            out2.close();
                            socket2.close();
                            db.close();
                            Log.e(" TARGET matrixCursor: ", DatabaseUtils.dumpCursorToString(matrixCursor));
                            return matrixCursor;
                        } else{
                            in2.close();
                            out2.close();
                            socket2.close();

                            in.close();
                            out.close();
                            socket.close();

                            // Target AVD Failed

                            // Query Target Replica
                            int target_index = Dynamo_Nodes.indexOf(target_avd);
                            int target_S1 = Dynamo_Nodes.get((target_index + 1) % Dynamo_Nodes.size());

                            Socket socketr = new Socket(inet, target_S1 * 2);
                            BufferedReader inr = new BufferedReader(new InputStreamReader(socketr.getInputStream()));
                            PrintWriter outr = new PrintWriter(socketr.getOutputStream());
                            outr.write("QUERYNEW" + "_" + NODE_ID + "_" + key + "\n");
                            outr.flush();
                            String replyr = inr.readLine();
                            MatrixCursor matrixCursor = new MatrixCursor(Starcolumns);
                            String[] res = replyr.split("_");
                            matrixCursor.addRow(new String[]{res[1], res[2]});
                            inr.close();
                            outr.close();
                            socketr.close();
                            db.close();
//                            Log.e("S1 matrixCursor  : ", DatabaseUtils.dumpCursorToString(matrixCursor));
                            return matrixCursor;
                        }
                    }
                    else{

                        in.close();
                        out.close();
                        socket.close();

                        // Target AVD Failed

                        // Query Target Replica
                        int target_index = Dynamo_Nodes.indexOf(target_avd);
                        int target_S1 = Dynamo_Nodes.get((target_index + 1) % Dynamo_Nodes.size());

                        Socket socket2 = new Socket(inet, target_S1 * 2);
                        BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
                        PrintWriter out2 = new PrintWriter(socket2.getOutputStream());
                        out2.write("QUERYNEW" + "_" + NODE_ID + "_" + key + "\n");
                        out2.flush();
                        String reply2 = in2.readLine();
                        MatrixCursor matrixCursor = new MatrixCursor(Starcolumns);
                        String[] res = reply2.split("_");
                        matrixCursor.addRow(new String[]{res[1], res[2]});
                        in2.close();
                        out2.close();
                        socket2.close();
                        db.close();
                        Log.e("S1 matrixCursor  : ", DatabaseUtils.dumpCursorToString(matrixCursor));
                        return matrixCursor;

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        db.close();
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public synchronized int delete(Uri uri, String selection, String[] selectionArgs) {
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();
        String dkey = selection;
        try {
            if(dkey.equalsIgnoreCase("*")) {

            } else if (dkey.equalsIgnoreCase("@")){

            } else{

                int target_delete = getTargerNode(dkey);
                if(target_delete == NODE_ID) {

                    // Delete self and replicates
                    db.delete("GROUP_TABLE", "key=?" , new String[]{dkey});
                    //Delete s1
                    InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                    Socket socket1 = new Socket(inet, NODE_S1 * 2);
                    PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
                    out1.write("DELETE" + "_" + NODE_ID + "_" + dkey + "\n");
                    out1.close();
                    socket1.close();

                    // Delete s2
                    Socket socket2 = new Socket(inet, NODE_S2 * 2);
                    PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
                    out2.write("DELETE" + "_" + NODE_ID + "_" + dkey + "\n");
                    out2.close();
                    socket2.close();

                }else{
                    // Forward to target ID
                    InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                    Socket socket = new Socket(inet, target_delete * 2);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.write("DELETE" + "_" + NODE_ID + "_" + dkey + "\n");
                    out.close();
                    socket.close();


                    int target_index = Dynamo_Nodes.indexOf(target_delete);
                    int target_S1 = Dynamo_Nodes.get((target_index + 1) % Dynamo_Nodes.size());
                    int target_S2 = Dynamo_Nodes.get((target_index + 2) % Dynamo_Nodes.size());

                    // To Target AVD _S1 and S1 will forward to S2
                    Socket socket1 = new Socket(inet, (target_S1) * 2);
                    PrintWriter out1 = new PrintWriter(socket1.getOutputStream());
                    out.write("DELETE" + "_" + NODE_ID + "_" + dkey + "\n");
                    out1.flush();
                    out1.close();
                    socket1.close();


                    // S2 delete

                    Socket socket2 = new Socket(inet, (target_S2) * 2);
                    PrintWriter out2 = new PrintWriter(socket2.getOutputStream());
                    out2.write("DELETE" + "_" + NODE_ID + "_" + dkey + "\n");
                    out2.flush();
                    out2.close();
                    socket2.close();


                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        db.close();
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    private synchronized int getTargerNode(String key) {
        try {
            // check boundary conditions first
            String k = genHash(key);
            if((genHash(key).compareTo(genHash(String.valueOf(FIRST_NODE))) < 0) || (genHash(key).compareTo(genHash(String.valueOf(LAST_NODE))) > 0)){
                // Insert 5562
                return FIRST_NODE;

            }else {
                for(int i=0 ; i <Dynamo_Nodes.size()-1; i++){
                    String in = String.valueOf(Dynamo_Nodes.get(i));
                    String in_1 =  String.valueOf(Dynamo_Nodes.get(i+1));

                    if(k.compareTo(genHash(in))>0  && k.compareTo(genHash(in_1)) <0){
                        return  Dynamo_Nodes.get(i+1);
                    }
                }
            }

        }catch (NoSuchAlgorithmException e){e.printStackTrace();}

        return  LAST_NODE;
    }

    private synchronized String getValue(ContentValues values) {
        Set<Map.Entry<String, Object>> kv =values.valueSet();
        Iterator itr = kv.iterator();
        String key = "";
        String value = "";
        while(itr.hasNext())
        {
            Map.Entry me = (Map.Entry)itr.next();
            if(me.getKey().toString().equalsIgnoreCase("key"))
                key = me.getValue().toString();
            if(me.getKey().toString().equalsIgnoreCase("value"))
                value = me.getValue().toString();
        }
        //Log.e("getKey ", "Key:" + key + ", values:" + value);
        return value;
    }

    private synchronized String getKey(ContentValues values) {
        Set<Map.Entry<String, Object>> kv =values.valueSet();
        Iterator itr = kv.iterator();
        String key = "";
        String value = "";
        while(itr.hasNext())
        {
            Map.Entry me = (Map.Entry)itr.next();
            if(me.getKey().toString().equalsIgnoreCase("key"))
                key = me.getValue().toString();
            if(me.getKey().toString().equalsIgnoreCase("value"))
                value = me.getValue().toString();
        }
        //Log.e("getKey ", "Key:" + key + ", values:" + value);
        return key;
    }

    private synchronized static String genHash(String input) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        byte[] sha1Hash = sha1.digest(input.getBytes());
        Formatter formatter = new Formatter();
        for (byte b : sha1Hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    private class ServerTask extends AsyncTask<ServerSocket, String, Void> {
        protected synchronized Void doInBackground(ServerSocket... sockets) {
            ServerSocket serverSocket = sockets[0];
            while(true) {
                try {
                    Socket Ssocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(Ssocket.getInputStream()));

                    String response = in.readLine();
                    if (response != null) {
                        String[] Msg_type = response.split("_");
                        if (Msg_type[0].equalsIgnoreCase("PING")) {
//							Log.e("ReceivedMsg :", response);
                            PrintWriter out = new PrintWriter(Ssocket.getOutputStream());
                            out.write("APING_" + NODE_ID + "\n");
                            out.flush();
                            out.close();
                            Ssocket.close();
                        }
                        if (Msg_type[0].equalsIgnoreCase("INSERT")) {
//							Log.e("ReceivedMsg :",response);
                            insert_final(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("FINSERT")) {
//							Log.e("ReceivedMsg :",response);
                            insert_forward(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("F1INSERT")) {
//							Log.e("ReceivedMsg :",response);
                            insert_fail_forward(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("QSTAR")) {
                            Log.e("ReceivedMsg :", response);
                            star_query(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("QSREPLY")) {
                            Log.e("ReceivedMsg :", response);
                            star_main(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("QUERY")) {
//							Log.e("ReceivedMsg :", response);
                            query_reply(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("QREPLY")) {
//							Log.e("ReceivedMsg :", response);
                            query_reply_recieve(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("DELETE")) {
//                            Log.e("ReceivedMsg :", response);
                            delete_recieve(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("FDELETE")) {
//                            Log.e("ReceivedMsg :", response);
                            delete_forward(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("DBUPDATE")) {
                            Log.e("ReceivedMsg :", response);
                            send_localdump(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("DBREPLY")) {
//                            Log.e("ReceivedMsg :", response);
                            update_localdump(response);
                        }
                        if (Msg_type[0].equalsIgnoreCase("STARNEW")) {
//                            Log.e("ReceivedMsg :", response);
                            DatabaseHelper dh = new DatabaseHelper(getContext());
                            SQLiteDatabase db = dh.getReadableDatabase();
                            Cursor cursor = db.rawQuery("select * from GROUP_TABLE", null);
                            PrintWriter out = new PrintWriter(Ssocket.getOutputStream());
                            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                                String k = cursor.getString(cursor.getColumnIndex("key"));
                                String v = cursor.getString(cursor.getColumnIndex("value"));
                                out.write("QNEWREPLY" + "_" + k + "_" + v + "\n");
                                out.flush();
                            }
                            out.write("QNEWREPLY" + "_" + "EMPTY" + "_" + String.valueOf(NODE_ID) + "\n");
                            out.flush();
                            out.close();
//                        Ssocket.close();
                        }
                        if (Msg_type[0].equalsIgnoreCase("QUERYNEW")) {
                            String[] res = response.split("_");
                            DatabaseHelper dh = new DatabaseHelper(getContext());
                            SQLiteDatabase db = dh.getReadableDatabase();
                            String[] Projection = {"key", "value"};
                            String Selection = "key" + "=?";
                            String SelectionArgs[] = new String[]{res[2]};
                            Cursor cursor = db.query(
                                    TABLE_NAME,     // Table name = group_table
                                    Projection,     // key , value columns are requested. null would also return both.
                                    Selection,      // SQL SYNTAX : WHERE key =
                                    SelectionArgs,  // argument for key column
                                    null,
                                    null,
                                    null);
                            if(cursor.moveToFirst() &&  cursor.getCount()>0){
                                String QKey = cursor.getString(cursor.getColumnIndex("key"));
                                String Qvalue = cursor.getString(cursor.getColumnIndex("value"));
                                String Q_r_msg = "QREPLY" + "_" + QKey + "_" + Qvalue;
                                PrintWriter out = new PrintWriter(Ssocket.getOutputStream());
                                out.write(Q_r_msg+ "\n");
                                out.flush();
                                db.close();
//                            Ssocket.close();
                            } else{
                                Log.e(" FAILED   ", "FAILED ---------------------------------->");
                            }
                        }
                    }

                    in.close();
                    Ssocket.close();

                } // End Try block
                catch (Exception e) {
                    e.printStackTrace();
                } // End Catch block
            } // End infinite loop
        }
    }// End Server Task

    private void insert_fail_forward(String response) {
        String[] res = response.split("_");
        insert_final(response);
        try {
            InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
            // write s1
            Socket socket1 = new Socket(inet, (NODE_S1) * 2);
            PrintWriter out1 = new PrintWriter(socket1.getOutputStream());
            out1.write("INSERT" + "_" + res[1] + "_" + res[2]);
            out1.flush();
            out1.close();
            socket1.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void update_localdump(String response) {
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();
        String[] resp =response.split("_");
        ContentValues cv = new ContentValues();
        cv.put("key", resp[2]);
        cv.put("value", resp[3]);
        db.insertWithOnConflict(TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }

    private void send_localdump(String response) {
        try{
            String[] res = response.split("_");
            String recieved_avd = res[2];
            String selfavdType = res[1];
            InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
            if(selfavdType.equalsIgnoreCase("NODES1")){
                DatabaseHelper dh = new DatabaseHelper(getContext());
                SQLiteDatabase db = dh.getReadableDatabase();
                Cursor  cursor = db.rawQuery("select * from GROUP_TABLE", null);

                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    String k = cursor.getString(cursor.getColumnIndex("key"));
                    String v = cursor.getString(cursor.getColumnIndex("value"));
                    int tnode = getTargerNode(k);
                    if(tnode == Integer.parseInt(recieved_avd)){
                        Socket socket = new Socket(inet, Integer.parseInt(res[2]) * 2);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        out.write("DBREPLY" +"_"+ NODE_ID + "_" + k + "_" + v + "\n");
                        out.flush();
                        out.close();
                        socket.close();
                    }

                }
                db.close();
            }

            if(selfavdType.equalsIgnoreCase("NODEP1")){
                DatabaseHelper dh = new DatabaseHelper(getContext());
                SQLiteDatabase db = dh.getReadableDatabase();
                Cursor  cursor = db.rawQuery("select * from GROUP_TABLE", null);
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    String k = cursor.getString(cursor.getColumnIndex("key"));
                    String v = cursor.getString(cursor.getColumnIndex("value"));
                    int tnode = getTargerNode(k);
                    if(tnode == NODE_ID){
                        Socket socket = new Socket(inet, Integer.parseInt(res[2]) * 2);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        out.write("DBREPLY" +"_"+ NODE_ID + "_" + k + "_" + v + "\n");
                        out.flush();
                        out.close();
                        socket.close();
                    }

                }
                db.close();
            }
            if(selfavdType.equalsIgnoreCase("NODEP2")){
                DatabaseHelper dh = new DatabaseHelper(getContext());
                SQLiteDatabase db = dh.getReadableDatabase();
                Cursor  cursor = db.rawQuery("select * from GROUP_TABLE", null);
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    String k = cursor.getString(cursor.getColumnIndex("key"));
                    String v = cursor.getString(cursor.getColumnIndex("value"));
                    int tnode = getTargerNode(k);
                    if(tnode == NODE_ID){
                        Socket socket = new Socket(inet, Integer.parseInt(res[2]) * 2);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        out.write("DBREPLY" +"_"+ NODE_ID + "_" + k + "_" + v + "\n");
                        out.flush();
                        out.close();
                        socket.close();
                    }
                }
                db.close();
            }


        }catch ( Exception e){e.printStackTrace();}

    }

    private void delete_forward(String response) {
        String[] res = response.split("_");
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();
        String dkey = res[2];
        try {
            // Delete self and replicates
            db.delete("GROUP_TABLE", "key=?", new String[]{dkey});
            //Delete s1
            InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
            Socket socket1 = new Socket(inet, NODE_S1 * 2);
            PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
            out1.write("DELETE" + "_" + NODE_ID + "_" + dkey + "\n");
            out1.close();
            socket1.close();

            // Delete s2
            Socket socket2 = new Socket(inet, NODE_S2 * 2);
            PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
            out2.write("DELETE" + "_" + NODE_ID + "_" + dkey + "\n");
            out2.close();
            socket2.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void delete_recieve(String response) {
        String[] res = response.split("_");
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();
        String dkey = res[2];
        db.delete("GROUP_TABLE", "key=?" , new String[]{dkey});
        db.close();
    }

    private void query_reply_recieve(String response) {
//		Log.e("QUERY_REPLY_RECIEVE :", "QREPLY");
        QRESPONSE = response;
        wait_Qreply = false;
    }

    private void query_reply(String response) {
        String[] res = response.split("_");
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();
        String[] Projection = {"key", "value"};
        String Selection = "key" + "=?";
        String SelectionArgs[] = new String[]{res[2]};
        Cursor cursor = db.query(
                TABLE_NAME,     // Table name = group_table
                Projection,     // key , value columns are requested. null would also return both.
                Selection,      // SQL SYNTAX : WHERE key =
                SelectionArgs,  // argument for key column
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            String QKey = cursor.getString(cursor.getColumnIndex("key"));
            String Qvalue = cursor.getString(cursor.getColumnIndex("value"));
            String Q_r_msg = "QREPLY" + "_" + QKey + "_" + Qvalue;

            try{

                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                Socket socket = new Socket(inet, Integer.parseInt(res[1])*2);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.write(Q_r_msg+ "\n");
                out.close();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            db.close();
        }
        db.close();
    }

    private void star_main(String response) {
        String[] res = response.split("_");
        Log.e("STAR MAIN", "  ENTRY");
        if(res[1].equalsIgnoreCase("EMPTY"))
            star_cpunt += 1;
        else
            StarCursor.addRow(new String[]{res[1], res[2]});

        // check if all sent reply
        if(star_cpunt == star_reply_cpunt)
            wait_starReply = false;
        Log.e("STAR MAIN", "  EXIT");
    }

    private void star_query(String response) {
        try {
            String[] res = response.split("_");
            DatabaseHelper dh = new DatabaseHelper(getContext());
            SQLiteDatabase db = dh.getReadableDatabase();
            Cursor  cursor = db.rawQuery("select * from GROUP_TABLE", null);

            // Send all messages
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                String k = cursor.getString(cursor.getColumnIndex("key"));
                String v = cursor.getString(cursor.getColumnIndex("value"));
                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                Socket socket = new Socket(inet, Integer.parseInt(res[1]) * 2);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.write("QSREPLY"+ "_" + k + "_" + v + "\n");
                out.close();
                socket.close();
            }
            // Send Empty
            InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
            Socket socket = new Socket(inet, Integer.parseInt(res[1]) * 2);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Log.e("STAR", " SENDING EMPTY");
            out.write("QSREPLY" + "_" + "EMPTY" + "_" + String.valueOf(NODE_ID) + "\n");
            out.close();
            socket.close();

            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void insert_final(String response) {
        String[] res = response.split("_");
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("key", res[1]);
        cv.put("value", res[2]);
        long rowId = db.insertWithOnConflict(TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
//        Log.e("INSERT FINAL :", cv.toString());
    }

    private void insert_forward(String response) {
        // Insert self and forward to s1 and s2
        String[] res = response.split("_");
        insert_final(response);
        try {
            InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});

            // write s1
            Socket socket1 = new Socket(inet, (NODE_S1) * 2);
            PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
            out1.write("INSERT" + "_" + res[1] + "_" + res[2]);
            out1.close();
            socket1.close();

            // Write S2
            Socket socket2 = new Socket(inet, (NODE_S2) * 2);
            PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
            out2.write("INSERT" + "_" + res[1] + "_" + res[2]);
            out2.close();
            socket2.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private class WakeUpTask extends AsyncTask<ServerSocket, String, Void>  {
        @Override
        protected synchronized Void doInBackground(ServerSocket... params) {
            try {
                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                // ASK S1
                Socket socket = new Socket(inet, NODE_S1 * 2);
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.write("DBUPDATE" +"_"+"NODES1" +"_"+ NODE_ID);
                out.flush();
                out.close();
                socket.close();
//				 ASK S2
//				Socket socket1 = new Socket(inet, NODE_S2 * 2);
//				PrintWriter out1 = new PrintWriter(socket1.getOutputStream());
//				out1.write("DBUPDATE" +"_"+"NODES2" +"_"+ NODE_ID);
//				out1.flush();
//				out1.close();
//				socket1.close();

                // ASK P1
                Socket socket2 = new Socket(inet, NODE_P1 * 2);
                PrintWriter out2 = new PrintWriter(socket2.getOutputStream());
                out2.write("DBUPDATE" +"_"+"NODEP1" +"_"+ NODE_ID);
                out2.flush();
                out2.close();
                socket2.close();
                // ASK P2
                Socket socket3 = new Socket(inet, NODE_P2 * 2);
                PrintWriter out3 = new PrintWriter(socket3.getOutputStream());
                out3.write("DBUPDATE" +"_"+"NODEP2" +"_"+ NODE_ID);
                out3.flush();
                out3.close();
                socket3.close();

            }catch(Exception e){
                e.printStackTrace();

            }
            Log.e("WAKE UP", " UPDATE COMPLETE ");
            return null;
        }
    }
}