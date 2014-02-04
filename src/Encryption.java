import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.PrintWriter;


class Encryption
{
	public static void main(String[] args) 
	{
		//Embed e= new Embed();
		String s;
		try{
			long start = System.currentTimeMillis( );
	         //System.out.println(new Date( ) + "\n");
	         System.out.println("start time in milliseconds is\t"+start+ "\n");
			System.out.println("Enter your message :");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		 s=br.readLine();
		System.out.println(s);
		int len=s.length();
			System.out.println(len);
		char[] strArray;
		int[] str1=new int[len];
		int[] quoti=new int [len];
		int[] rem=new int[len];
		int[] rem1=new int[len+1];
		//int arrCombo[][]={{162,163,164},{165,166,167},{168,169,170}};(utf16)
		int arrCombo[] []={{2309,2310,2311},{2312,2313,2314},{2315,2316,2317}};
		strArray=s.toCharArray();
		System.out.println("  U  Q  R");
		int M=3;
		for(int index=0;index<strArray.length;index++)
				{
		str1[index]=strArray[index];
		System.out.print(" "+str1[index]);
		quoti[index]=str1[index]/M;
		System.out.println(" "+quoti[index]);
		rem[index]=str1[index]%M;
		System.out.print("  "+rem[index]);
		//System.out.println("rem"+rem.length);
		System.out.println();
				}
		for(int z=0;z<rem.length;z++)
		{
			rem1[z]=rem[z];
		//System.out.println("rem1 is"+rem1[z]);
		}
		
		
		if(rem.length%2==0)                             //message length is even
		{
		int [] temp=new int[len/2];	
		int x=0;
		int k=0;
		System.out.print("Generated cipher text is :");
			for(int chk=0;chk<rem.length;)
			{
			int	i=rem[chk++];
			int j=rem[chk++];
			if(k%2==1)
				{
		     temp[x++]=arrCombo[j][i];
		    System.out.print((char)temp[x-1]);
				}
		else
				{
		      temp[x++]=arrCombo[i][j];
		    System.out.print((char)temp[x-1]);
				}
		k++;
						}
		File fileexample=new File("trial.txt");
		PrintWriter pw=new PrintWriter(fileexample);
		for(int z=0;z<temp.length;z++)
		pw.println(temp[z]);
		pw.close();
		File fileexample1=new File("trial1.txt");
		PrintWriter pw1=new PrintWriter(fileexample1);
		for(int h=0;h<quoti.length;h++)
		pw1.println(quoti[h]);
		pw1.close();
		System.out.println("\n");
		System.out.print("files created");
		int pass=1;
	    do
	    {
		pass=1;
		//System.out.print("\nType 1 and  press enter: ");
		//Scanner s1 = new Scanner(System.in);
		int op = 1;
				//Integer.parseInt(s1.nextLine());
		if(op==1)
		{
			System.out.println("\nSteganography process");
		crypting();	
		}
		else
		pass=0;
	    }while(pass==0);
		}
		
		if(rem.length%2!=0)                //message length is odd
		{
			rem1[len]=0;
			/*for(int r=0;r<rem1.length;r++)
			{
		System.out.println("again rem1 IS"+rem1[r]);
			}*/
		int [] temp=new int[(len/2)+1];	
		int x=0;
		int k=0;
		System.out.print("Generated cipher text is :");
			for(int chk=0;chk<rem1.length;)
			{
			int	i=rem1[chk++];
			int j=rem1[chk++];
			if(k%2==1)
				{
		     temp[x++]=arrCombo[j][i];
		    System.out.print((char)temp[x-1]);
				}
		else
				{
		      temp[x++]=arrCombo[i][j];
		    System.out.print((char)temp[x-1]);
				}
		k++;
			
			}
				//System.out.println("jj6");
				System.out.println(temp.length);
				System.out.println(quoti.length);
		File fileexample=new File("trial.txt");     //cipher text
		PrintWriter pw=new PrintWriter(fileexample);
		for(int z=0;z<temp.length;z++)
		pw.println(temp[z]);
		pw.close();
		//pw.println(".");
		File fileexample1=new File("trial1.txt");      //quotient
		PrintWriter pw1=new PrintWriter(fileexample1);
		for(int h=0;h<quoti.length;h++)
		pw1.println(quoti[h]);
	
		pw1.close();
		System.out.println("\n");
		System.out.print("files created");
		}
		//for(int s=0;s<quoti.length;s++)
		int pass=1;
	    do
	    {
		pass=1;
		//System.out.print("\nType 1 and  press enter: ");
		//Scanner s1 = new Scanner(System.in);
		int op =1;
				//Integer.parseInt(s1.nextLine());
		if(op==1)
		{
		System.out.println("\nSteganography process");
		crypting();	
		}
		else
		pass=0;
	    }while(pass==0);
		
		}
		
		catch(Exception e1)
		{
e1.printStackTrace();
		}
		
	}
	static void crypting() throws Exception
	{
		int pass=0,value=0;
		PicSecure ps = new PicSecure();
		Scanner s = new Scanner(System.in);
	    do
	    {
		pass=0;
		System.out.print("\n\nEnter path of the file you want to hide: ");
		String source = s.nextLine();
		System.out.print("Enter path of the image file you want to hide in: ");
		String jpgdest = s.nextLine();
		System.out.print("Do you want to delete the file that you want to hide? (y or n): ");
		String ds = s.nextLine();
		Boolean delSource;
		if(ds.compareToIgnoreCase("yes")==0 || ds.compareToIgnoreCase("y")==0)
			delSource = true;
		else
			delSource = false;
	    try
	    {
		value = ps.crypt(source, jpgdest, delSource);
	    }
	    catch(java.io.IOException e)
	    {
		System.out.println("Files are not accessible. Please try again.");
		System.exit(0);
	    }
		if(value == 1)
		{
			System.out.println("Data Hidden Successfully");
			pass=1;
		}
		else if(value == -1)
			System.out.println("File to hide not found.");
		else if(value == -2)
			System.out.println("Can't read to the file to hide.");
		else if(value == -3)
			System.out.println("Image file not found.");
		else if(value == -4)
			System.out.println("Can't write to the image file");
		else if(value == 0)
		{
			System.out.println("The program can't access the files. Hiding failed!\nTry again.");
			pass=1;
		}
	    }while(pass==0);
	}
}
