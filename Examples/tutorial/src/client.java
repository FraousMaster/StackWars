import java.io.*;
import java.net.*;
public class client
{
	private DataInputStream din;
	private DataOutputStream dout;
	public static void main(String[] args)
	{
		//new client();
		new gui(new client());
	}
	
	private client ()
	{
		try
		{
			Socket s = new Socket("127.0.0.1", 1201);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String msgin = "", msgout = "";
			
			while(!msgin.equals("end"))
			{
				msgout = br.readLine();
				dout.writeUTF(msgout);
				msgin = din.readUTF();
				System.out.println(msgin);
				dout.flush();
			}*/
		}
		catch(Exception e)
		{
	
		}
	}
	
	public String sendMessage(String msg)
	{
		
		String msgin = "", msgout = msg;
		if(msgout == null || msgout == "")
		{
			return "Something went wrong";
		}
		try
		{
			dout.writeUTF(msgout);
			//System.out.println(msg);
			msgin = din.readUTF();
			//System.out.println(msgin);
			dout.flush();
		}
		catch(Exception e)
		{
			System.out.println("Exception: The server is not open");
		}
		return msgin;
	}
}