import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ass1_5709611957 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String h;
		try {
			String fname = scan.nextLine();
			File file = new File(fname);
			BufferedReader br = new BufferedReader(new FileReader(file));
			h = br.readLine();
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			char pass[] = new char[8];
			for(int i=0; i<8; i++){
				pass[i] = '0';
			}
			System.out.print("password is: ");
			while(pass[7]!='z'){
				while(pass[6]!='z'){
					while(pass[5]!='z'){
						while(pass[4]!='z'){
							while(pass[3]!='z'){
								while(pass[2]!='z'){
									while(pass[1]!='z'){
										while(pass[0]!='z'){
											String p = new String(pass);
											byte[] bytes = md.digest(p.getBytes("UTF-8"));
											StringBuilder sb = new StringBuilder();
									        for(int i=0; i< bytes.length ;i++){
									        	sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
									        }
									        String ansString = sb.toString().toUpperCase();
											boolean result = h.equals(ansString);
											if(result){
												System.out.println(p);
												return;
											}
											pass[0]++;
											if(pass[0]==58) pass[0]=65;
											else if(pass[0]==91) pass[0]=97;
										}
										pass[0] = '0';
										pass[1]++;
										if(pass[1]==58) pass[1]=65;
										else if(pass[1]==91) pass[1]=97;
									}
									pass[1] = '0';
									pass[2]++;
									if(pass[2]==58) pass[2]=65;
									else if(pass[2]==91) pass[2]=97;
								}
								pass[2] = '0';
								pass[3]++;
								if(pass[3]==58) pass[3]=65;
								else if(pass[3]==91) pass[3]=97;
							}
							pass[3] = '0';
							pass[4]++;
							if(pass[4]==58) pass[4]=65;
							else if(pass[4]==91) pass[4]=97;
						}
						pass[4] = '0';
						pass[5]++;
						if(pass[5]==58) pass[5]=65;
						else if(pass[5]==91) pass[5]=97;
					}
					pass[5] = '0';
					pass[6]++;
					if(pass[6]==58) pass[6]=65;
					else if(pass[6]==91) pass[6]=97;
				}
				pass[6] = '0';
				pass[7]++;
				if(pass[7]==58) pass[7]=65;
				else if(pass[7]==91) pass[7]=97;
			}
		} catch (NoSuchAlgorithmException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
