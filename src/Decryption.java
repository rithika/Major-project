import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Decryption
{
//static int count=0;
	
	public static void main(String[] args) throws Exception
	{
		
		int [] temp=new int[30];
		int [] quoti=new int[30];
		int arrCombo[] []={{2309,2310,2311},{2312,2313,2314},{2315,2316,2317}};
		int M=3;
		int b=0,count=0,c=0,a=0;
		String s,s1;
			File fileexample=new File("trial.txt");
			Scanner scn=new Scanner(fileexample);     //cipher text scanned here
			int pass=1;
		    do
		    {
			pass=1;
			//System.out.println("Type 2 and press enter");
			//System.out.print("Choose Option: ");
			//Scanner scnn = new Scanner(System.in);
			int op = 2;
					//Integer.parseInt(scnn.nextLine());
			if(op==2)
			{
				System.out.println("Image Decryption process");
				decrypting();
			}
			else
			pass=0;
			}while(pass==0);
		    
			File fileexample1=new File("quoti.txt");
			Scanner scn1=new Scanner(fileexample1);                 //quotient scanned here
			while(scn.hasNext())
		{
 s=scn.nextLine();
 System.out.println("string is"+s);
 if(s!="\n")
 {
 temp[b]=Integer.parseInt(s);                      //cipher in ascii
 b++;
 count++;
 }
		}
		/*for(int j=0;j<count;j++)
		{
			System.out.println("temp is="+temp[j]);
			
		}*/
		System.out.println();
		while(scn1.hasNext())
		{
 s1=scn1.nextLine();
 //System.out.println(s1);
 if(s1!="\n")
 {
 quoti[a]=Integer.parseInt(s1);                       //quotient is stored in array
 a++;
 c++;
 }
		}
		/*for(int j=0;j<c;j++)
		{
			System.out.println(quoti[j]);
		}*/
		int [] rem2=new int[c+1];
		//System.out.println("len"+temp.length);
		int r=0;
		for (int i=0;i<temp.length;i++)
		{
			for (int j=0;j<arrCombo.length;j++ )
			{
				for (int n=0;n<arrCombo.length ;n++)
				{
					if(i%2==0)
					{
					if(arrCombo[j][n]==temp[i])
						{
							rem2[r++]=j;
							rem2[r++]=n;
						}
					}
					else 
					{
						if(arrCombo[n][j]==temp[i])
						{
						rem2[r++]=j;
						rem2[r++]=n;
						
						}
					}
				}
			}
		}
		
/*for(int i=0;i<rem2.length;i++)
{
System.out.println("rem2jk=="+rem2[i]);	
}*/
		System.out.println("bbbbbbbbb"+rem2.length);
		int [] u=new int[30];
		
		for (int i=0;i<rem2.length ;i++ )
		{
			System.out.println("quot=="+quoti[i]);
			u[i]=(quoti[i]*M)+rem2[i];               //message unicode received here
			System.out.println("u[i]=="+u[i]);
			System.out.println("rem2[i]=="+rem2[i]);
		}
	System.out.println("\n\nThe Message after decryption\n");
		for (int i=0;i<u.length;i++ )
		{
			System.out.print((char)u[i]);
		}
		long end = System.currentTimeMillis( );
	    System.out.println("end time in milliseconds is\t"+end+ "\n");
	}
	static void decrypting() throws Exception
	{
		int pass=0,value=0;
		PicSecure ps = new PicSecure();
		Scanner s = new Scanner(System.in);
	    do
	    {
		pass=0;
		System.out.print("\n\nEnter path of the image you want to decrypt: ");
		String source = s.nextLine();
		System.out.print("Enter path of the destination file: ");
		String dest = s.nextLine();
	    try
	    {
		value = ps.decrypt(source, dest);
	    }
	    catch(java.io.IOException e)
	    {
		System.out.println("Files are not accessible. Please try again.");
		System.exit(0);
	    }
		if(value == 1)
		{
			System.out.println("Data Decrypted Successfully");
			pass=1;
		}
		else if(value == -1)
			System.out.println("File to show not found.");
		else if(value == -2)
			System.out.println("Can't read to the file to show.");
		else if(value == -3)
			System.out.println("Destination file exists.");
		else if(value == -4)
			System.out.println("Can't create new file with the destination file name");
		else if(value == -5)
			System.out.println("This file is not encrypted by this program, so can't decrypt it.");
		else if(value == 0)
		{
			System.out.println("The program can't access the files. Hiding failed!\nTry again.");
			pass=1;
		}
	    }while(pass==0);
	    
	}
	
}
		
	
