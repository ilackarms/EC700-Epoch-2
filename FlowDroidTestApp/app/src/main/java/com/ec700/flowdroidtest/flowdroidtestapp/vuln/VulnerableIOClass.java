package com.ec700.flowdroidtest.flowdroidtestapp.vuln;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by low-poly-Life on 3/26/2015.
 */
public class VulnerableIOClass {
    public String s;

    public VulnerableIOClass(){
//        testToJsonSource();
        testFromJsonSource();
    }

    void testToJsonSource(){
        Gson gson = new Gson();
        String source = gson.toJson(1);
        processObject(source.getBytes()); //INVESTIgATE WHY GETBYTES PRESERVES TAINT

//        OutputStream outputStream = new OutputStream() {
//            @Override
//            public void write(byte[] buffer) throws IOException {
//                super.write(buffer);
//            }
//
//            @Override
//            public void write(int oneByte) throws IOException {
//
//            }
//        };
//        try{
//            outputStream.write(source.getBytes());
//        } catch (Exception e){
//            //nothing
//        }
    }

    void testFromJsonSource(){
        Gson gson = new Gson();

        JsonIP source = gson.fromJson("{'ip':'12345','url':'12345','about':'12345'}", JsonIP.class);

//        source.setIp(gson.fromJson("'1'", String.class));
//        source.s = (gson.fromJson("'1'", String.class));
        //String source2 = gson.fromJson("'1'", String.class);

        processObject(source.getIp());

//        OutputStream outputStream = new OutputStream() {
//            @Override
//            public void write(byte[] buffer) throws IOException {
//                super.write(buffer);
//            }
//
//            @Override
//            public void write(int oneByte) throws IOException {
//
//            }
//        };
//        try{
//            outputStream.write(source.toString().getBytes());
//        } catch (Exception e){
//            //nothing
//        }
    }

    public JsonIP taintObject(){
        JsonIP jsonIP = new JsonIP();
        jsonIP.setIp("55");
        jsonIP.setAbout("55");
        try {
            jsonIP.setPro(new URL("http://foo.com"));
        }
        catch(Exception e){
            //nothing
        }
        return jsonIP;
    }

    void processObject(Object o){
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(byte[] buffer) throws IOException {
                super.write(buffer);
            }

            @Override
            public void write(int oneByte) throws IOException {

            }
        };
        try{
            outputStream.write(o.toString().getBytes());
        } catch (Exception e){
            //nothing
        }
    }

}
