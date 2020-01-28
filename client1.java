import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class client1 extends JFrame implements ActionListener,Runnable
{
	JButton b1,b2;
	JTextField t1;
	JTextArea a1;
	Socket soc=null;
	Thread th;
	public static void main(String args[])
	{
		client1 jf=new client1("CLIENT");
		jf.setVisible(true);
		jf.setSize(300,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.components();
	}
	
	client1(String s)
	{
		super(s);
		try{
		soc=new Socket("127.0.0.1",8090);
		}catch(Exception p)
			{
			System.out.println(p.getMessage());			
			}
	}

	void components()
	{
		b1=new JButton("send");
		b2=new JButton("receive");
		a1=new JTextArea(10,20);
		t1=new JTextField(10);

		setLayout(new FlowLayout());

		add(a1);
		add(b1);
		add(t1);
		add(b2);
		

		b1.addActionListener(this);
		b2.addActionListener(this);
		th=new Thread(this);
		th.start();
	}

	public void run()
	{
		while(true)
		{
			try{
			BufferedReader br=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String s2=br.readLine();
			
			a1.append("you:"+s2+"\n");
			}catch(Exception q)
				{
				System.out.println(q.getMessage());
				}	
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			try{
			String s1=t1.getText();
			a1.append("me:"+s1+"\n");
			t1.setText("");
			PrintWriter pw=new PrintWriter(soc.getOutputStream(),true);
			pw.println(s1);
			}catch(Exception q)
				{
				System.out.println(q.getMessage());
				}
		}

		if(e.getSource()==b2)
		{
			try{
			BufferedReader br=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String s2=br.readLine();
			
			a1.append("you:"+s2+"\n");
			}catch(Exception q)
				{
				System.out.println(q.getMessage());
				}			
		}
	}
}
