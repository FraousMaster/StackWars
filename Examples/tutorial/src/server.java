import java.io.*;
import java.net.*;
public class server {
	public static void main(String[] args)
	{
		try
		{
			ServerSocket ss = new ServerSocket(1201);
			Socket s = ss.accept();
			
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String msgin = "", msgout = "Your message was received";
			
			while(!msgin.equals("end"))
			{
				msgin = din.readUTF();
				if(msgin != null || msgin != "")
					System.out.println(msgin);
	
				dout.writeUTF(msgout);
				dout.flush();
			}
			s.close();
			ss.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
