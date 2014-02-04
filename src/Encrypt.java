// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Encrypt.java

import java.io.*;

class Encrypt
{

    Encrypt(String s, boolean flag, int i)
    {
        path = s;
        if(flag)
            value = i;
        else
            value = -i;
    }

    Encrypt(String s, boolean flag)
    {
        path = s;
        if(flag)
            value = 2;
        else
            value = -2;
    }

    Encrypt(String s, int i)
    {
        path = s;
        value = i;
    }

    Encrypt(String s)
    {
        path = s;
        value = 2;
    }

    boolean secure()
        throws Exception
    {
        File file = new File(path);
        if(file.exists())
        {
            int i = 0;
            FileInputStream fileinputstream = new FileInputStream(path);
            String s = "temp";
            String s1 = file.getParent();
            if(s1 != null && (new File(s1)).list() != null)
            {
                String as[] = (new File(s1)).list();
                for(int j = 0; j < as.length; j++)
                    if(as[j].equals(s))
                    {
                        i++;
                        s = (new StringBuilder()).append(s).append(Integer.toString(i)).toString();
                    }

            }
            File file1 = new File(s);
            file1.createNewFile();
            FileOutputStream fileoutputstream = new FileOutputStream(s);
            for(; fileinputstream.available() > 0; fileoutputstream.write(fileinputstream.read() + value));
            fileinputstream.close();
            fileoutputstream.close();
            file.delete();
            file1.renameTo(file);
            return true;
        } else
        {
            return false;
        }
    }

    private String path;
    private int value;
}
