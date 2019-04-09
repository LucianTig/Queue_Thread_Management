import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Magazin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pane3 = new JPanel(new GridBagLayout());
	private JPanel pane1 = new JPanel(new GridBagLayout());
	private JPanel pane2 = new JPanel(new GridBagLayout());
	private JPanel mainPane = new JPanel(new GridBagLayout());
	
	GridBagConstraints c = new GridBagConstraints();

	private JButton button1 = new JButton("Start");

	private JTextField text1 = new JTextField(3);  //min arrTime
	private JTextField text2 = new JTextField(3);  //max arrTime
	private JTextField text3 = new JTextField(3);  //min SerTime 
	private JTextField text4 = new JTextField(3);  //max SerTime
	private JTextField text5 = new JTextField(3);  //nr queue
	private JTextField text6 = new JTextField(3);  //nr clients
	private JTextField text7 = new JTextField(3);  //Sim time

	private JLabel label1 = new JLabel("Arriving time");
	private JLabel label2 = new JLabel("Service time");
	private JLabel label3 = new JLabel("Nr queue");
	private JLabel label4 = new JLabel("Nr clients");
	private JLabel label5 = new JLabel("Sim. Time");
	
	JTextArea l = new JTextArea("Evolutia cozilor \n",15,30);
	JTextArea logEvents = new JTextArea("Log of events: \n",15,40);
	JTextArea simResult = new JTextArea("Simulation Results: \n",15,30);
	JScrollPane sp = new JScrollPane(logEvents); 
	

	public Magazin(String name) {
		super(name);
		
		c.gridx = 0;
		c.gridy = 0;
		pane1.add(label1, c);

		c.gridx = 1;
		c.gridy = 0;
		pane1.add(text1, c);

		c.gridx = 2;
		c.gridy = 0;
		pane1.add(text2, c);
		
		c.gridx = 0;
		c.gridy = 1;
		pane1.add(label2, c);			

		c.gridx = 1;
		c.gridy = 1;
		pane1.add(text3, c);

		c.gridx = 2;
		c.gridy = 1;
		pane1.add(text4, c);

		c.gridx = 0;
		c.gridy = 2;
		pane1.add(label3, c);

		c.gridx = 1;
		c.gridy = 2;
		pane1.add(text5, c);
		
		c.gridx = 0;
		c.gridy = 3;
		pane1.add(label4, c);
	
		c.gridx = 1;
		c.gridy = 3;
		pane1.add(text6, c);

		c.gridx = 1;
		c.gridy = 4;
		pane1.add(text7, c);
		
		c.gridx = 0;
		c.gridy = 4;
		pane1.add(label5, c);
		
		c.gridx = 1;
		c.gridy = 6;
		pane1.add(button1, c);
		button1.addActionListener(this);

		c.gridx = 0;
		c.gridy = 0;
        mainPane.add(pane1,c);
        
        c.gridx = 0;
		c.gridy = 0;
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pane2.add(l,c);
        
        c.gridx = 1;
		c.gridy = 0;
        mainPane.add(pane2,c);
        
        c.gridx = 0;
		c.gridy = 0;
        mainPane.add(pane3,c);
        
        c.gridx = 0;
		c.gridy = 1;
		logEvents.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainPane.add(sp,c); 
        
        c.gridx = 1;
		c.gridy = 1;
		simResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPane.add(simResult,c);
        
		this.add(mainPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();


		if(source == button1){
			SimulatorManager m=new SimulatorManager();
			m.setRef(this.l,this.simResult,this.logEvents);
			logEvents.setText("Log of events");
			m.generareClient(Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()),Integer.parseInt(text3.getText()), Integer.parseInt(text4.getText()), Integer.parseInt(text6.getText()), Integer.parseInt(text5.getText()),Integer.parseInt(text7.getText()));
			m.start();
			//this.repaint();
			
		} 
	}


	public static void main(String args[]) {
		JFrame frame = new Magazin("Magazin");
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}