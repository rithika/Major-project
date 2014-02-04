import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
class PicSecure
{
	private String copyrgt = "#SecurePic v2.0#";
	int crypt(String source, String jpgdest, boolean delSource) throws Exception
	{
		File fs = new File(source);

		if(fs.isFile() || fs.isHidden());
		else return -1;
		if(fs.canRead());
		else return -2;

		File jpgd = new File(jpgdest);

		if(jpgd.isFile() || jpgd.isHidden());
		else return -3;
		if(jpgd.canWrite());
		else return -4;

		Encrypt e = new Encrypt(source,true,2);
		e.secure();

		FileInputStream jpg = new FileInputStream(jpgd);
		File f = new File("temp");
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(f);

		for(;jpg.available()>0;)
			fos.write(jpg.read());
		jpg.close();

		for(int i=0;i<copyrgt.length();i++)
			fos.write(copyrgt.charAt(i));

		FileInputStream fis = new FileInputStream(fs);
		for(;fis.available()>0;)
			fos.write(fis.read());

		fis.close();
		fos.close();

		jpgd.delete();
		f.renameTo(jpgd);

		if(delSource)
			fs.delete();
		return 1;
	}
	int decrypt(String source, String dest) throws Exception
	{
		File fs = new File(source);
		if(fs.isFile() || fs.isHidden());
		else return -1;
		if(fs.canRead());
		else return -2;
		File fd = new File(dest);
		if(fd.isFile() || fd.isHidden())
			return -3;
		else
		{
			if(fd.createNewFile());
			else return -4;
		}
		char ch;
		String copy;
		int pass=0,size=0;
		FileInputStream fis = new FileInputStream(fs);
		FileOutputStream fos = new FileOutputStream(fd);
		for(;fis.available()>0;)
		{
			ch = (char) fis.read();
			if(pass==1)
				fos.write(ch);
			else if(ch == '#' && pass==0)
			{
				copy = new String(Character.toString(ch));
				for(int i=1;i<copyrgt.length();i++)
					copy = new String(copy+(char)fis.read());
				if(copyrgt.equals(copy))
				{
					size = fis.available();
					pass=1;
				}
			}
			else;
		}

		fis.close();
		fos.close();

		Encrypt e = new Encrypt(dest,false,2);
		e.secure();

		if(pass==0)
		{
			fd.delete();
			return -5;
		}
		else
		{
			File tmp = new File("temp");
			tmp.createNewFile();
			FileInputStream fi = new FileInputStream(fs);
			FileOutputStream fo = new FileOutputStream(tmp);
			for(;fi.available()>(size+copyrgt.length());)
				fo.write((char)fi.read());
			fi.close();
			fo.close();
			fs.delete();
			tmp.renameTo(fs);
		}
		return 1;
	}
}		