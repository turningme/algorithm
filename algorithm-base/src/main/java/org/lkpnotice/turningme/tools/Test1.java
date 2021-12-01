package org.lkpnotice.turningme.tools;

import com.github.luben.zstd.ZstdInputStream;
import com.github.luben.zstd.ZstdOutputStream;
import org.lkpnotice.turningme.utils.Utils;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Test1 {
    static final Logger logger = Logger.getLogger(Test1.class.getName());

    public static void main(String[] args) {
//        testDecompressOneFolderItems();


        testGzipOneFolderItems();
        /*testZstdCompress4NormalFile();
        testZstdDecompress4NormalFile()*/;
    }


    /**
     * read compressed f file , then
     *
     * @param f
     * @param output
     * @throws IOException
     */
    static void readCompress(String f, String output) throws IOException {
        readCompress(new File(f), new File(output));
    }

    /**
     * read compressed file and convert to normal ones
     *
     * @param f
     * @param output
     * @throws IOException
     */
    static void readCompress(File f, File output) throws IOException {
        if (f == null || !f.exists()) {
            throw new RuntimeException("not exist f = ");
        }

        if (output == null) {
            throw new RuntimeException("not exist output = ");
        }
        if (output.exists()) {
            output.delete();

        }
        output.createNewFile();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
        ZstdInputStream zis = new ZstdInputStream(bis);
        logger.info("Zstd InputStream initialized ");

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output));

        byte[] buf = new byte[1024 * 8];
        int bytesRead = -1;
        while ((bytesRead = zis.read(buf)) > 0) {
    /*        for (int i=0; i< bytesRead ;i ++){
                buf = Utils.decrypt(buf);
            }*/
            bos.write(buf, 0, bytesRead);
        }

        bos.close();
        zis.close();
    }


    /**
     * @param f
     * @param output
     * @throws IOException
     */
    static void compress(String f, String output) throws IOException {
        compress(new File(f), new File(output));
    }

    /**
     * compress the f to output as tstd
     *
     * @param f
     * @param output
     */
    static void compress(File f, File output) throws IOException {
        if (f == null || !f.exists()) {
            throw new RuntimeException("not exist f = ");
        }

        if (output == null) {
            throw new RuntimeException("not exist output = ");
        }
        if (output.exists()) {
            output.delete();

        }
        output.createNewFile();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));


        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output));
        ZstdOutputStream zos = new ZstdOutputStream(bos);
        logger.info("Zstd OutputStream initialized ");


        byte[] buf = new byte[1024 * 8];
        int bytesRead = -1;
        while ((bytesRead = bis.read(buf)) > 0) {
        /*    for (int i=0; i< bytesRead ;i ++){
                buf = Utils.encrypt(buf);
            }*/
            zos.write(buf, 0, bytesRead);
        }

        bis.close();
        zos.close();
    }

    public static void testZstdDecompress() {
        String normalFileInput = "";
        String zstdFileOutputDir;


    }


    public static void testZstdCompress4NormalFile() {
        String normalFileInput = "/Users/bytedance/myDownload/1234.numbers";
        String fname = new File(normalFileInput).getName();
        logger.info("fname source " + fname);
        String outputBaseDir = "/Users/bytedance/output";
        String zstdFileOutputDir = outputBaseDir + "/" + fname;
        logger.info("output source " + zstdFileOutputDir);

        try {
            compress(normalFileInput, zstdFileOutputDir);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }


    public static void testZstdDecompress4NormalFile(){
        String normalFileInput = "/Users/bytedance/output/1234.numbers";
        String fname = new File(normalFileInput).getName();
        logger.info("fname source " + fname);
        String outputBaseDir = "/Users/bytedance/output-1";
        String zstdFileOutputDir = outputBaseDir + "/" + fname;
        logger.info("output source " + zstdFileOutputDir);

        try {
            readCompress(normalFileInput, zstdFileOutputDir);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }


    public static void testGzipOneFolderItems(){
        String normalFileInput = "/Users/bytedance/inputdir";
        File dir = new File(normalFileInput);
        if (!dir.isDirectory()){
            System.out.println("Not a dir , exit");
        }
        String outputBaseDir = "/Users/bytedance/output-2";
        System.out.println("size is " + dir.listFiles().length);
        Arrays.asList(dir.listFiles()).stream().map(f1->{
            System.out.println("process file " + f1.getAbsolutePath());
            if (f1.isFile()){
                String fname =f1.getName();
//                fname = new String(Utils.encrypt(fname.getBytes()));
                String zstdFileOutputDir = outputBaseDir + "/" + fname;
                logger.info("process fname source " + fname);
                logger.info("output source " + zstdFileOutputDir);

                Runnable runnable = new Runnable(){
                    @Override
                    public void run() {
                        try {
                            compress(f1.getAbsolutePath(), zstdFileOutputDir);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println(e);
                        }
                    }
                };

                new Thread(runnable, "name : " + f1.getName()).start();

            }

            return 1;
        }).collect(Collectors.toList());

    }


    public static void testDecompressOneFolderItems(){
        String normalFileInput = "/Users/bytedance/output-2";
        String outputBaseDir = "/Users/bytedance/output-4";
        File dir = new File(normalFileInput);
        if (!dir.isDirectory()){
            System.out.println("Not a dir , exit");
        }

        System.out.println("size is " + dir.listFiles().length);
        Arrays.asList(dir.listFiles()).stream().map(f1->{
            System.out.println("process file " + f1.getAbsolutePath());
            if (f1.isFile()){
                String fname =f1.getName();
                fname = new String(Utils.decrypt(fname.getBytes()));
                String zstdFileOutputDir = outputBaseDir + "/" + fname;
                logger.info("process fname source " + fname);
                logger.info("output source " + zstdFileOutputDir);

                try {
                    readCompress(f1.getAbsolutePath(), zstdFileOutputDir);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            }

            return 1;
        }).collect(Collectors.toList());
    }



}
