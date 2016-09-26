package edu.buffalo.cse.cse486586.groupmessenger2;

/**
 * Created by shiva on 3/19/16.
 */
public class MsgEncoder {

    // Message Structure
    private int SeqNum;
    private String type;
    private int avd;
    private String MsgText;

    // Constructor
    public MsgEncoder(int seqNum, String type, int avd, String msgText) {
        super();
        SeqNum = seqNum;
        this.type = type;
        this.avd = avd;
        MsgText = msgText;
    }

    // Getters & Setters methods
    public int getSeqNum() {
        return SeqNum;
    }
    public void setSeqNum(int seqNum) {
        SeqNum = seqNum;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getAvd() {
        return avd;
    }
    public void setAvd(int avd) {
        this.avd = avd;
    }
    public String getMsgText() {
        return MsgText;
    }
    public void setMsgText(String msgText) {
        MsgText = msgText;
    }
}
