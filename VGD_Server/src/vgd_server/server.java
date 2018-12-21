/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgd_server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author PC
 */
public class server extends javax.swing.JFrame {

    //ArrayList clientOutputStreams;
    ArrayList<String> users;
    ArrayList<String> devices;
    DatagramSocket serverSock;
    //InetAddress IPAddress;
    String IPAddress;
    int port;
    boolean isStart = false;
    SimpleDateFormat timeF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date now;

    public class clientHandler implements Runnable {
//
//        BufferedReader reader;
//        Socket sock;
//        PrintWriter client;
//
//        public clientHandler(Socket clientSocket, PrintWriter user) {
//            client = user;
//            try {
//                sock = clientSocket;
//                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
//                reader = new BufferedReader(isReader);
//            } catch (Exception ex) {
//                logTxt.append("Unexpected error... \n");
//            }
//
//        }
//

        @Override
        public void run() {
//            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat";
//            String[] data;
//
//            try {
//                while ((message = reader.readLine()) != null) {
//                    logTxt.append("Received: " + message + "\n");
//                    data = message.split(":");
//
//                    for (String token : data) {
//                        logTxt.append(token + "\n");
//                    }
//
//                    if (data[2].equals(connect)) {
//                        //tellEveryone((data[0] + ":" + data[1] + ":" + chat));
//                        userAdd(data[0]);
//                    } else if (data[2].equals(disconnect)) {
//                        //tellEveryone((data[0] + ":has disconnected." + ":" + chat));
//                        userRemove(data[0]);
//                    } else if (data[2].equals(chat)) {
//                        //tellEveryone(message);
//                    } else {
//                        logTxt.append("No Conditions were met. \n");
//                    }
//                }
//            } catch (Exception ex) {
//                logTxt.append("Lost a connection. \n");
//                //ex.printStackTrace();
//                clientOutputStreams.remove(client);
//            }
        }

    }

    public server() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        logTxt = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        devicesTxt = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        usersTxt = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        deviceLbl = new javax.swing.JLabel();
        userLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VGD System");
        setResizable(false);

        logTxt.setColumns(20);
        logTxt.setRows(5);
        jScrollPane1.setViewportView(logTxt);

        devicesTxt.setEditable(false);
        devicesTxt.setColumns(20);
        devicesTxt.setLineWrap(true);
        devicesTxt.setRows(5);
        jScrollPane2.setViewportView(devicesTxt);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel1.setText("Logs");

        jLabel2.setText("Connected Devices : ");

        usersTxt.setEditable(false);
        usersTxt.setColumns(20);
        usersTxt.setLineWrap(true);
        usersTxt.setRows(5);
        jScrollPane3.setViewportView(usersTxt);

        jLabel3.setText("Connected Users : ");

        deviceLbl.setText("0");

        userLbl.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deviceLbl)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(userLbl))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(deviceLbl)
                    .addComponent(userLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop)
                    .addComponent(btnClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (!isStart) {
            Thread starter = new Thread(new ServerStart());
            starter.start();

            logTxt.append("Server started at " + new Date() + "\n");
        } else {
            logTxt.append("Server is running\n");
        }

        isStart = true;
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        try {
            Thread.sleep(1000);                 //5000 milliseconds is five second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        users = new ArrayList();
        devices = new ArrayList();
        userLbl.setText("0");
        deviceLbl.setText("0");
        logTxt.append("All clients and users has been disconnected.\nServer stopped at " + new Date() + "\n");
        usersTxt.setText("");
        devicesTxt.setText("");

    }//GEN-LAST:event_btnStopActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        logTxt.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new server().setVisible(true);
            }
        });
    }

    public class ServerStart implements Runnable {

        @Override
        public void run() {
            //clientOutputStreams = new ArrayList();
            users = new ArrayList();
            devices = new ArrayList();
            String connect = "Connect", disconnect = "Disconnect";
            try {
                serverSock = new DatagramSocket(1234);
                byte[] receiveData = new byte[1024];
                //ServerSocket serverSock = new ServerSocket(2222);

                while (true) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSock.receive(receivePacket);
                    String client = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength(), "UTF-8");
                    String[] data;
                    data = client.split(":");
                    IPAddress = receivePacket.getAddress().getHostAddress();
                    port = receivePacket.getPort();
                    now = new Date();
                    String time = timeF.format(now);
                    if (client.equals("Request Msg")) {//client.equals("1Request Msg")  ANDROID CLIENT
                        logTxt.append(time + " | INFO |: New android client connected.\n");
                        //logTxt.append("Debug@:" + IPAddress + ":" + port + "\n");
                        userAdd(IPAddress, port);
                    } else {//                                                          CAMERA
                        if (data[2].equals(connect)) {
                            //logTxt.append("Debug@:" + IPAddress + ":" + port + "\n");
                            userAdd(data[0], IPAddress, port);
                            logTxt.append(time + " | INFO |: New VGD device connected.\n");
                        } else if (data[2].equals(disconnect)) {
                            //logTxt.append("Debug@: Removed\n");
                            userRemove(data[0], IPAddress, port);
                            logTxt.append(time + " | INFO |: " + data[0] + " disconnected from the server.\n");
                        }
                    }
                }
            } catch (Exception ex) {
                now = new Date();
                String time = timeF.format(now);
                logTxt.append(time + " | ERR |: Error making a connection. \n");
                //logTxt.append(ex.toString());
            }
        }
    }

    public void userAdd(String data, String ip, int p) { // DEVICES
        String name = data + "@" + ip + ":" + p;
        //logTxt.append("Before " + name + " added. \n");
        devices.add(name);
        //logTxt.append("After " + name + " added. \n");
        String[] tempList = new String[(devices.size())];
        devices.toArray(tempList);
        devicesTxt.setText("");
        deviceLbl.setText(tempList.length + "");
        for (String token : tempList) {
            devicesTxt.append(token + "\n");
        }
        //tellEveryone(done);
    }

    public void userAdd(String ip, int p) {             // ANDROID CLIENT
        String name = "Client " + (users.size() + 1) + "@" + ip + ":" + p;
        //logTxt.append("Before " + name + " added. \n");
        users.add(name);
        //logTxt.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        usersTxt.setText("");
        userLbl.setText(tempList.length + "");
        for (String token : tempList) {
            usersTxt.append(token + "\n");
        }

    }

    public void userRemove(String data, String ip, int p) { // DEVICES
        String name = data + "@" + ip + ":" + p;
        devices.remove(name);
        String[] tempList = new String[(devices.size())];
        devices.toArray(tempList);
        devicesTxt.setText("");
        deviceLbl.setText(tempList.length + "");
        for (String token : tempList) {
            devicesTxt.append(token + "\n");
        }
        //tellEveryone(done);
    }

    public void userRemove(String ip, int p) { //               ANDROID CLIENT
        String name = "Client " + users.size() + "@" + ip + ":" + p;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        usersTxt.setText("");
        userLbl.setText(tempList.length + "");
        for (String token : tempList) {
            devicesTxt.append(token + "\n");
        }
    }

//    public void tellEveryone(String message) {
//        Iterator it = clientOutputStreams.iterator();
//
//        while (it.hasNext()) {
//            try {
//                PrintWriter writer = (PrintWriter) it.next();
//                writer.println(message);
//                logTxt.append("Sending: " + message + "\n");
//                writer.flush();
//                logTxt.setCaretPosition(logTxt.getDocument().getLength());
//
//            } catch (Exception ex) {
//                logTxt.append("Error telling everyone. \n");
//            }
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JLabel deviceLbl;
    private javax.swing.JTextArea devicesTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea logTxt;
    private javax.swing.JLabel userLbl;
    private javax.swing.JTextArea usersTxt;
    // End of variables declaration//GEN-END:variables
}
