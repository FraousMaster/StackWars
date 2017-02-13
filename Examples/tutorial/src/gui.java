import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class gui extends JFrame 
{
	private client c;
	private JTextPane textPane = null;
	public gui (client c)
	{
		this.c = c;
		mainFrame();
	}
	
	private void mainFrame()
	{
		setTitle("Testing");
		setSize(400,400);
		initPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void initPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(basicTextArea(), BorderLayout.CENTER);
		panel.add(sendButton(), BorderLayout.SOUTH);
		add(panel);
	}
	private JButton sendButton()
	{
		JButton button = new JButton("Send");
		button.setPreferredSize(new Dimension(60,30));
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String msgR = c.sendMessage(getText());
				if(msgR != null)
				{
					System.out.println(msgR);
				}
				
				textPane.setText(null);			}
		});
		return button;
	}
	private JTextPane basicTextArea()
	{
		textPane = new JTextPane();
		JScrollPane scrollPanel = new JScrollPane(textPane);
		textPane.setPreferredSize(new Dimension(400, 200));
		return textPane; 
	}
	
	private String getText()
	{
		String msg = textPane.getText();
		return msg;
	}
	
}
