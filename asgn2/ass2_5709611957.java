import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ass2_5709611957 {
	public static void main(String[] args) {
		String mode = args[0];
		String fname = args[1];
		final int[] publickey = {7,33};
		final int[] privatekey = {3,33};
		try {
			if(mode.equals("-v")){
				BufferedReader br = new BufferedReader(new FileReader(args[2]));
				int[] sValue = new int[128];
				String hexAns = "";
				for(int i=0; i< sValue.length ;i++){
					sValue[i] = Integer.parseInt(br.readLine());
					sValue[i] = (int)(Math.pow(sValue[i],publickey[0])%publickey[1]);
					hexAns += String.format("%X", sValue[i]);
				}
				/*byte[] rbytes = new byte[64];
				for(int i=0; i< rbytes.length ;i++){
					rbytes[i] = (byte)Integer.parseInt(br.readLine());
					int num = rbytes[i];
					if(num<33&&num>-33)rbytes[i] = (byte)(Math.pow(num,publickey[0])%publickey[1]);
					System.out.println(rbytes[i]);
				}*/ //for use byte
				File file = new File(fname);
				br = new BufferedReader(new FileReader(file));
				String line;
				String msg = "";
				msg = br.readLine();
				while((line = br.readLine()) != null) msg += "\n"+line;
				br.close();
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				byte[] bytes = md.digest(msg.getBytes("UTF-8"));
				StringBuilder sb = new StringBuilder();
		        for(int i=0; i< bytes.length ;i++){
		        	sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		        }
		        String hexString = sb.toString().toUpperCase();
		        if(hexString.equals(hexAns)) System.out.println("Signature OK");
				else System.out.println("Wrong Signature");
				/*if(Arrays.equals(bytes,rbytes)) System.out.println("Signature OK");
				else System.out.println("Wrong Signature");*/ //for use byte
			}
			else if(mode.equals("-c")){ 
				File file = new File(fname);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				String msg = "";
				msg = br.readLine();
				while((line = br.readLine()) != null) msg += "\n"+line;
				br.close();
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				byte[] bytes = md.digest(msg.getBytes("UTF-8"));
				PrintWriter writer = new PrintWriter("file.sig", "UTF-8");
				/*for(int i=0; i< bytes.length ;i++){
					int num = bytes[i];
					System.out.println(num);
					if(num<33&&num>-33) bytes[i] = (byte)(Math.pow(num,privatekey[0])%privatekey[1]);
					writer.println(bytes[i]);
				}*/ //for use byte
				StringBuilder sb = new StringBuilder();
		        for(int i=0; i< bytes.length ;i++){
		        	sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		        }
		        String hexString = sb.toString().toUpperCase();
		        for(int i=0; i< hexString.length() ;i++){
		        	int hexValue = Character.getNumericValue(hexString.charAt(i));
		        	int sValue = (int)(Math.pow(hexValue,privatekey[0])%privatekey[1]);
		        	writer.println(sValue);
		        }
		        writer.close();
			}   
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
