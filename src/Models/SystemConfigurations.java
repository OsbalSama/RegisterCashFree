
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author ARTEMISA
 */
public class SystemConfigurations {

    //ClientInfo
    String client;
    String serial;
    boolean activated;
    boolean reset = true;
    boolean newInstall = false;

    //StoreInfo
    boolean norfc;
    boolean nophone;

    String storename;
    String rfc;
    String adress;
    String phone;

    //TicketInfo
    String printer;
    int papersize;
    String terms;
    
    //inCashData
    boolean upCash;
    double inCash;

    public boolean isUpCash() {
        return upCash;
    }

    public void setUpCash(boolean upCash) {
        this.upCash = upCash;
    }

    public double getInCash() {
        return inCash;
    }

    public void setInCash(double inCash) {
        this.inCash = inCash;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public boolean isNewInstall() {
        return newInstall;
    }

    public void setNewInstall(boolean newInstall) {
        this.newInstall = newInstall;
    }

    public boolean isNorfc() {
        return norfc;
    }

    public void setNorfc(boolean norfc) {
        this.norfc = norfc;
    }

    public boolean isNophone() {
        return nophone;
    }

    public void setNophone(boolean nophone) {
        this.nophone = nophone;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrinter() {
        return printer;
    }

    public void setPrinter(String printer) {
        this.printer = printer;
    }

    public int getPapersize() {
        return papersize;
    }

    public void setPapersize(int papersize) {
        this.papersize = papersize;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

//    public SystemConfigurations() {
//        
//    }

    
    
    @Override
    public String toString() {
        return "SystemConfigurations{" + "client=" + client + ", serial=" + serial + ", activated=" + activated + ", reset=" + reset + ", storename=" + storename + ", rfc=" + rfc + ", adress=" + adress + ", phone=" + phone + ", printer=" + printer + ", papersize=" + papersize + ", terms=" + terms + '}';
    }

}
