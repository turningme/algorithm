package org.lkpnotice.turningme.tools;

import java.io.File;

public class Test3 {
    public static void main(String[] args){
        String[] pathList = new String[]{
                "/Users/bytedance/output-4",
                "/Users/bytedance/output-2",
                "/Users/bytedance/output-1",
                "/Users/bytedance/inputdir"
        };

        for (int i = 0; i< pathList.length ; i ++){
            File f = new File(pathList[i]);
            if (f.exists() && f.isDirectory()){
                File[] subFs = f.listFiles();
                for (File subF:subFs) {

                    if (subF.isFile()){
                        subF.delete();
                        System.out.println(subF.getAbsolutePath() + "deleted");
                    }
                }
            }
        }
    }

}
