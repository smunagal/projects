package edu.buffalo.cse.cse486586.simpledht;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 *  References:
 *  Cursors:
 *  1. http://developer.android.com/reference/android/database/MatrixCursor.html
 *  2. http://developer.android.com/reference/android/database/Cursor.html
 *  3. http://stackoverflow.com/questions/2810615/how-to-retrieve-data-from-cursor-class
 */

public class SimpleDhtProvider extends ContentProvider {

    // My Declarations
    
    public static final String DATABASE_NAME = "GROUPTABLE.db";
    public static final String TABLE_NAME = "GROUP_TABLE";

    public static String succ = "";
    public static String pred = "";
    public static String Node_ID = "";
    public static String zero_node = "";
    public static String Last_node = "";
    public static Map<String,String> chord=new HashMap<String,String>();
    public static LinkedList<Map.Entry<String,String>> Chordlist = new LinkedList<Map.Entry<String, String>>(chord.entrySet());
    public static ArrayList<String> AVDlist =new ArrayList<String>();
    public static final Uri mUri = buildUri("content", "edu.buffalo.cse.cse486586.simpledht.provider");

    public static boolean wait_Qreply = true;
    public static String TempResponse =  "";

    public static String[] Starcolumns = new String[]{"key", "value"};
    public static MatrixCursor StarCursor = new MatrixCursor(Starcolumns);
    public static int star_cpunt = 0;
    public static boolean wait_starReply = true;


    @Override
    public boolean onCreate() {
        
        try {

            TelephonyManager tel = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
            String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
	    Log.e("Emulator Number : ", portStr);
	    Node_ID = portStr;
        // Only for 5554
	    if(Node_ID.equalsIgnoreCase("5554")) {
            Chordlist = ManageChord("5554");
            AVDlist.add(Node_ID);
        }
	    // Start Server
	    new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ServerSocket(10000));

	    // if Emulator id is not 5554 Send Join Request to 5554
	    if (!portStr.equalsIgnoreCase("5554")) {
                String Send_Msg = "JOIN" + portStr;
                new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, Send_Msg , "5554");
            } // End- If block

	} // End- try block
	catch (Exception e) {
            Log.e("Exception :", "NoSuchAlgorithmException");
            e.printStackTrace();
	} // End catch block 

        return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        Log.e("in Main insert ", values.toString());
        // For Single Node scenario
        if(Chordlist.size() < 2) {
            try {
                DatabaseHelper dh = new DatabaseHelper(getContext());
                SQLiteDatabase db = dh.getWritableDatabase();
                long rowId = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                if (rowId != -1) {
                    Log.e("INSERTED ", values.toString());
                }
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // For Multi Node scenario
        else {
            try {
                DatabaseHelper dh = new DatabaseHelper(getContext());
                SQLiteDatabase db = dh.getWritableDatabase();
                String key = getKey(values);
                String value = getValue(values);
                // If it is not last avd, send to succ
                if (genHash(key).compareTo(genHash(Node_ID)) < 0 && genHash(key).compareTo(genHash(pred)) > 0) {
                    long rowId = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                    Log.e("INSERTED Direct : ", Node_ID + " :: " + values.toString());
                }
                else {
                    String forward_Msg = "INST" + key + value;
                    Log.e("Forwading to ", succ +"  : " +forward_Msg);
                    new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, forward_Msg , succ);
                }
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //@Overload
    public Uri insert_final(Uri uri, ContentValues values) {
        try {
            DatabaseHelper dh = new DatabaseHelper(getContext());
            SQLiteDatabase db = dh.getWritableDatabase();
            long rowId = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            Log.e("INSERTED Final : ", Node_ID + " :: " + values.toString());
            db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();
        String key = selection;
        Log.e("Query args :", selection);
        // For Single Node Query
        if(Chordlist.size() < 2) {

            if (selection.equalsIgnoreCase("*") || selection.equalsIgnoreCase("@")){
                Cursor  cursor = db.rawQuery("select * from GROUP_TABLE",null);
                cursor.moveToFirst();
                db.close();
                return cursor;
            }else{

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
                return  cursor;

            }
        }
        // For MultiNode DHT Query
        else {
            try {
                if (selection.equalsIgnoreCase("@")){
                    Cursor  cursor = db.rawQuery("select * from GROUP_TABLE",null);
                    cursor.moveToFirst();
                    db.close();
                    return cursor;
                }
                // STAR CODE
                else if (selection.equalsIgnoreCase("*")){
                    Cursor  cursor = db.rawQuery("select * from GROUP_TABLE",null);
                    cursor.moveToFirst();
                    db.close();



                    //Insert self <k,v> to Star Cursor ;
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        String k = cursor.getString(cursor.getColumnIndex("key"));
                        String v = cursor.getString(cursor.getColumnIndex("value"));
                        StarCursor.addRow(new String[]{k, v});
                    }

                    // send message to succ
                    String star_msg = "STAR" + Node_ID;
                    new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, star_msg, succ);

                    //wait until flag is set i.e until u recieve Empty from all other active chords (Chordlist.size() -1)

                    while(wait_starReply){
                        // waiting until all other messages reply back their local dump
                    }

                    // Re-initalize values before exit
                    star_cpunt =0;
                    wait_starReply = true;

                    db.close();

                    return  StarCursor;

                }
                else if (genHash(key).compareTo(genHash(Node_ID)) < 0 && genHash(key).compareTo(genHash(pred)) > 0) {

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
                    return cursor;
                }
                else{
                    TempResponse = "";
                    String Qforward_Msg = "QUER" + Node_ID + key;
                    Log.e("Q forward to ", succ + "  : " + Qforward_Msg);
                    new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, Qforward_Msg, succ);


                    while(wait_Qreply){
                        // Waiting for reply
                    }
                    Log.e("Recieved RESULT :", " "+ TempResponse);

                    String[] columns = new String[]{"key", "value"};
                    MatrixCursor matrixCursor = new MatrixCursor(columns);
                    String Qkey = TempResponse.substring(8, 40);
                    String value = TempResponse.substring(40);
                    matrixCursor.addRow(new String[]{Qkey, value});

                    // reinitialize temp res and flags

                    TempResponse = "";
                    wait_Qreply = true;

                    db.close();
                    return matrixCursor;

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private void query_final(Uri mUri, String queryKey,String Main_node) {

        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();
        String[] Projection = {"key", "value"};
        String Selection = "key" + "=?";
        String SelectionArgs[] = new String[]{queryKey};
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
            String Q_r_msg = "CURS" + Main_node + QKey + Qvalue;

            // Send to Main Query AVD
            new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, Q_r_msg , Main_node);
            Log.e("Q Repl to ", Main_node  + "  : " + Q_r_msg);
        }else
            Log.e("No Key : ", queryKey);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        int rows_effected =0;
        String dkey = selection;
        Log.e("DELETE selection:", " "+selection);
        DatabaseHelper dh = new DatabaseHelper(getContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        if(Chordlist.size() < 2) {
            // For single node
            db.delete("GROUP_TABLE", "key=?" , new String[]{dkey});

        }
        else {
            try {
                if (selection.equalsIgnoreCase("*")) {
                    db.delete("GROUP_TABLE", null, null);
                    String Del_Msg = "DELA" + Node_ID;
                    new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, Del_Msg, succ);
                }
                if (selection.equalsIgnoreCase("@")) {
                    db.delete("GROUP_TABLE", null, null);
                    Log.e("DELETED LOCAL:", String.valueOf(rows_effected));
                } else {

                    // does key belong to this avd ?, if so delete. Else pass to next
                    if (genHash(dkey).compareTo(genHash(Node_ID)) < 0 && genHash(dkey).compareTo(genHash(pred)) > 0) {
                        Log.e(" MINE : ",dkey);
                        db.delete("GROUP_TABLE", "key=?" , new String[]{dkey});
                    }
                    else { // Key doesnot belong to this node, forward to succ
                            String forward_Msg = "DEL1" + dkey;
                            Log.e("Del forward to ", succ +"  : " +forward_Msg);
                            new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, forward_Msg , succ);
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return rows_effected;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    private static  Uri buildUri(String scheme, String authority) {
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority(authority);
        uriBuilder.scheme(scheme);
        return uriBuilder.build();
    }

    private static LinkedList<Map.Entry<String,String>> ManageChord(String Node) {
        try {
            chord.put(Node, genHash(Node));
        }catch (Exception e){
            e.printStackTrace();
        }

        LinkedList<Map.Entry<String,String>> list = new LinkedList<Map.Entry<String, String>>(chord.entrySet() );

        // Sorting as per comparator
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        return list;

    }

    private static String genHash(String input) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        byte[] sha1Hash = sha1.digest(input.getBytes());
        Formatter formatter = new Formatter();
        for (byte b : sha1Hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    /**
     * Other Methods
     */

    public static String getSuccessor(int Node_place, int DHTSize){
        if (DHTSize >1){
            Map.Entry<String, String> Succ = Chordlist.get((Node_place)%DHTSize);
            return Succ.getKey();
        }else{
            String pred=Node_ID;
            return pred;
        }
    }

    public static String getPredecessor(int Node_place, int DHTSize){
        if (DHTSize >1){

            int temp = (Node_place-2)%DHTSize;
            if(temp<0){
                temp = DHTSize-Math.abs(temp);
            }
            Map.Entry<String, String> pred = Chordlist.get(temp);
            return pred.getKey();
        }else{
            String pred=Node_ID;
            return pred;
        }
    }

    private static int getNodePos() {
        int count = 1;
        int Node_place = 0;
        for(Map.Entry<String,String> E : Chordlist){
            if(E.getKey().equalsIgnoreCase(Node_ID))
                Node_place = count;

            count +=1;
        }
        return Node_place;

    }

    private String getValue(ContentValues values) {
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

    private String getKey(ContentValues values) {
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

    private String getZeroNode() {
        Map.Entry<String, String> zero = Chordlist.get(0);
        return zero.getKey().toString();
    }

    private String getLastNode() {
        Map.Entry<String, String> last = Chordlist.get(Chordlist.size() - 1);
        return last.getKey().toString();

    }

    /**
     * Server Methods Start Here
     * @param response
     *
     * Only executes on 5554 and Notify's other avds about new Node
     */

    private void AddNode(String response) {
        String new_Node = response.substring(4, 8);
        //Chordlist = ManageChord("5554");
        Chordlist = ManageChord(new_Node);
        AVDlist.add(new_Node);

        // Displaying Chordlist
        Log.e("ADDNode :", Chordlist.toString());

        // Update Successors and Predecessor Nodes
        Log.e("AddNode :", "Updating succ and pred");
        int node_Pos = getNodePos();
        succ = getSuccessor(node_Pos,Chordlist.size());
        pred = getPredecessor(node_Pos, Chordlist.size());

        // Displaying Succ and pred
        Log.e("AddNode node_Pos :", String.valueOf(node_Pos));
        Log.e("AddNode succ :", succ);
        Log.e("AddNode pred :", pred);

        // Create send_Msg to broadcast to other Avds
        StringBuilder sb = new StringBuilder();
        sb.append(AVDlist.size());
        for(Object avds : AVDlist){
            sb.append(avds.toString());
        }
        // MSg Format = DHTU - number - avds ...
        String send_Msg = "DHTU" + sb.toString();
        for(Object avd: AVDlist){
            if(!avd.toString().equalsIgnoreCase("5554"))
                new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, send_Msg , avd.toString());
        }
    }

    private void DHTupdate(String response) {
        // MSg Format = DHTU - number - avds ...
        int numOfAvds = Integer.parseInt(response.substring(4,5));
        int start_index = 5;
        for(int i =0; i< numOfAvds ; i++) {
            Chordlist = ManageChord(response.substring(start_index,start_index+4));
            start_index +=4;
        }
        // Displaying chord
        Log.e("DHTupdate :", Chordlist.toString());

        // Update Successors and Predecessor Nodes
        Log.e("DHTupdate :", "Updating succ and pred");
        int node_Pos = getNodePos();
        succ = getSuccessor(node_Pos,Chordlist.size());
        pred = getPredecessor(node_Pos, Chordlist.size());

        // Displaying Succ and pred
        Log.e("DHTupdate node_Pos :", String.valueOf(node_Pos));
        Log.e("DHTupdate succ :", succ);
        Log.e("DHTupdate pred :", pred);
        zero_node = getZeroNode();
        Last_node = getLastNode();
    }

    private void InstInsert(String response) {

        String key =   response.substring(4, 36);
        String value =   response.substring(36);

        // Some where here it is failing fix in morning
        try {
            //Log.e("Zero Node ", zero_node);
            if(!Node_ID.equalsIgnoreCase(zero_node)) {
                if ((genHash(key).compareTo(genHash(Node_ID)) < 0) && (genHash(key).compareTo(genHash(pred)) > 0)) {
                    ContentValues cv = new ContentValues();
                    cv.put("key", key);
                    cv.put("value", value);
                    insert_final(mUri, cv);
                }else{
                    String forward_Msg = "INST" + key + value;
                    new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, forward_Msg, succ);
                }
            }
            else{
                if((genHash(key).compareTo(genHash(pred)) > 0)  || (genHash(key).compareTo(genHash(Node_ID)) < 0)){
                    ContentValues cv = new ContentValues();
                    cv.put("key", key);
                    cv.put("value", value);
                    insert_final(mUri, cv);
                } else{
                String forward_Msg = "INST" + key + value;
                new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, forward_Msg, succ);
                }
            }
        }catch (Exception e){
            Log.e("WoooHoooo Exception" , ": exception");
            e.printStackTrace();
        }

    }

    private void QuerReply(String response) {

        String Main_node = response.substring(4,8);
        String QueryKey =   response.substring(8, 40);
        try {
            // If zero Node
            if (!Node_ID.equalsIgnoreCase(zero_node)) {
                if ((genHash(QueryKey).compareTo(genHash(Node_ID)) < 0) && (genHash(QueryKey).compareTo(genHash(pred)) > 0)){
                    query_final(mUri, QueryKey, Main_node);
                }else{
                    String Qforward_Msg = "QUER" + Main_node + QueryKey;
                    Log.e("Q forward to ", succ + "  : " + Qforward_Msg);
                    new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, Qforward_Msg , succ);
                }
            }
            // If not Zero Node
            else {
                if((genHash(QueryKey).compareTo(genHash(pred)) > 0)  || (genHash(QueryKey).compareTo(genHash(Node_ID)) < 0)){
                    query_final(mUri, QueryKey, Main_node);
                }else{
                    String Qforward_Msg = "QUER" + Main_node + QueryKey;
                    Log.e("Q forward to ", succ + "  : " + Qforward_Msg);
                    new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, Qforward_Msg , succ);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // Final DISPLAY Method
    private void Reply_Main(String response) {

        TempResponse = response;
        wait_Qreply = false;
    }

    private void Reply_Star(String response) {
        String Msg_node = response.substring(4,8);

        // Identify end of loop and do nothing if msg circles back
        if(!Msg_node.equalsIgnoreCase(Node_ID)){
            // get all msgs to Msg_node
            DatabaseHelper dh = new DatabaseHelper(getContext());
            SQLiteDatabase db = dh.getReadableDatabase();
            Cursor  cursor = db.rawQuery("select * from GROUP_TABLE", null);


            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                String k = cursor.getString(cursor.getColumnIndex("key"));
                String v = cursor.getString(cursor.getColumnIndex("value"));
                String Star_msgs = "SMSG" + k + v;
                new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, Star_msgs , Msg_node);
            }

            // Send all messages
            String Star_msgs = "SMSG" + "EMPTY";
            new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, Star_msgs , Msg_node);

            // Forward Responce to Successor
            new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, response , succ);
        }
    }


    private void Star_Main(String response) {

        String CheckEMPTY = response.substring(4,9);

        if(CheckEMPTY.equalsIgnoreCase("EMPTY")){
            star_cpunt += 1;
            Log.e("Count incremented " , String.valueOf(star_cpunt));
        }else{
            String k =   response.substring(4, 36);
            String v =   response.substring(36);
            StarCursor.addRow(new String[]{k, v});

        }
        if(star_cpunt == Chordlist.size()-1){
            wait_starReply = false;
        }
    }

    private void Delete_all(String response) {
        String Msg_node = response.substring(4,8);
        if(!Msg_node.equalsIgnoreCase(Node_ID)){
            delete(mUri, "@", null);
            String Del_Msg = "DELA"+Msg_node;
            new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, Del_Msg , succ);
        }
    }


    private void Delete_one(String response) {
        String dkey = response.substring(4);

        try {
            if(!Node_ID.equalsIgnoreCase(zero_node)) {
                if ((genHash(dkey).compareTo(genHash(Node_ID)) < 0) && (genHash(dkey).compareTo(genHash(pred)) > 0)) {
                    DatabaseHelper dh = new DatabaseHelper(getContext());
                    SQLiteDatabase db = dh.getReadableDatabase();
                    db.delete("GROUP_TABLE", "key=?" , new String[]{dkey});

                }else{
                    new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, response, succ);
                }
            }
            else{
                if((genHash(dkey).compareTo(genHash(pred)) > 0)  || (genHash(dkey).compareTo(genHash(Node_ID)) < 0)){
                    DatabaseHelper dh = new DatabaseHelper(getContext());
                    SQLiteDatabase db = dh.getReadableDatabase();
                    db.delete("GROUP_TABLE", "key=?" , new String[]{dkey});
                }else{
                    new SendMessage().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, response, succ);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     *  Server Task starts here
     *
     */

    private class ServerTask extends AsyncTask<ServerSocket, String, Void> {
        protected Void doInBackground(ServerSocket... sockets) {

            ServerSocket serverSocket = sockets[0];

            while(true) {
                try {
                    Socket Ssocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(Ssocket.getInputStream()));
                    String response = "" ;
                    while ((response = in.readLine()) != null){
                        Log.e("ReceivedMsg :",response);
			            String Msg_type = response.substring(0, 4);
			            if(Msg_type.equalsIgnoreCase("JOIN"))
                            AddNode(response);
                        if(Msg_type.equalsIgnoreCase("DHTU"))
                            DHTupdate(response);
                        if(Msg_type.equalsIgnoreCase("INST"))
                            InstInsert(response);
                        if(Msg_type.equalsIgnoreCase("QUER"))
                            QuerReply(response);
                        if(Msg_type.equalsIgnoreCase("CURS"))
                            Reply_Main(response);
                        if(Msg_type.equalsIgnoreCase("STAR"))
                            Reply_Star(response);
                        if(Msg_type.equalsIgnoreCase("SMSG"))
                            Star_Main(response);
                        if(Msg_type.equalsIgnoreCase("DELA"))
                            Delete_all(response);
                        if(Msg_type.equalsIgnoreCase("DEL1"))
                            Delete_one(response);


                   	} // End While block
                    in.close();
                    Ssocket.close();
                } // End Try block
		catch (IOException e) {
                    e.printStackTrace();
                } // End Catch block
            } // End infinite loop
        }
    }// End Server Task

    /**
     *  Send Message Starts Here
     *
     */
    private class SendMessage extends AsyncTask<String, String, Void> {
        protected Void doInBackground(String... msg) {
            try{
                Log.e("SendMessage to ", msg[1]+ "  :"+ msg[0]);
                InetAddress inet = InetAddress.getByAddress(new byte[]{10, 0, 2, 2});
                Socket socket = new Socket(inet, Integer.parseInt(msg[1])*2);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.write(msg[0]+"\n");
                out.close();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }



}// End program














