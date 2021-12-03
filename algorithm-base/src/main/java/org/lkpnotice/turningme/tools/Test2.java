package org.lkpnotice.turningme.tools;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        String normalFileInput = "/Users/bytedance/output-2";
        File dir = new File(normalFileInput);
        if (!dir.isDirectory()){
            System.out.println("Not a dir , exit");
        }

        System.out.println("size is " + dir.listFiles().length);
        Arrays.asList(dir.listFiles()).stream().map(f1->{
            System.out.println("process file " + f1.getAbsolutePath());
            if (f1.isFile()){
                try {
                    tototo(f1.getAbsolutePath());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            return 1;
        }).collect(Collectors.toList());
    }


    public static void tototo(String from) throws IOException {
//        String host = "8.210.239.89";
//        String host = "192.168.10.103";
        String host = "192.168.10.104";
        SSHClient ssh = new SSHClient();
        ssh.loadKnownHosts();
        ssh.addHostKeyVerifier(new PromiscuousVerifier());
        ssh.connect(host);
        try {
//            ssh.authPublickey("root");
//            ssh.authPublickey(System.getProperty("user.name"));
            ssh.authPassword("","");

            // Present here to demo algorithm renegotiation - could have just put this before connect()
            // Make sure JZlib is in classpath for this to work
            ssh.useCompression();

//            final String src = System.getProperty("user.home") + File.separator + "test_file";
            String src = from;
            ssh.newSCPFileTransfer().upload(new FileSystemFile(src), "/Users/lkpnotice/tto");
        } finally {
            ssh.disconnect();
        }
    }
}
