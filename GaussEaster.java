import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GaussEaster extends JFrame{
	int count; 
	private JButton button;
	private JLabel easterDate = new JLabel();
	private JTextField field;

	public static void main(String[] args) {
		new GaussEaster();
	}
	
	public GaussEaster(){
		this.setTitle("Beräkna påskdagens datum");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(240,260);
		//this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("Ange ett årtal fr.o.m. 2016");
		topPanel.add(label1,BorderLayout.NORTH);
		field = new JTextField();
		field.setColumns(30);
		field.setFont(new Font("Arial", Font.BOLD, 40));
		topPanel.add(field,BorderLayout.CENTER);
		button = new JButton("Beräkna");
		button.addActionListener(new ButtonHandler());
		topPanel.add(button,BorderLayout.SOUTH);
		this.add(topPanel);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		easterDate.setFont(new Font("Serif", Font.ROMAN_BASELINE,20));
		bottomPanel.add(easterDate,BorderLayout.NORTH);
		bottomPanel.add(new JLabel(" "));
		JLabel label2 = new JLabel();
		label2.setFont(new Font("Serif", Font.ITALIC, 20));
		bottomPanel.add(label2,BorderLayout.SOUTH);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		for (int i = 2017; i < 2117; i++) {
			calcEaster(i);
		}
		label2.setText("<html><body>Mellan (och inklusive) 2017 och 2116 infaller"
				+ " påsken senare än 15 april sammanlagt "
				+ count + " gånger.</body></html>");
		this.setVisible(true);
	}
	
	private String calcEaster(int year){
		String easterDate = "";
		int a, b, c, k, p, q, M, N, d, e; //deklarera årsberoende parametrar
		
		a = year % 19;
		b = year % 4;
		c = year % 7;
		k = year/100; //århundrade
		p = (13 + 8*k)/25;
		q = k/4;
		M = (15 - p + k - q) % 30;
		N = (4 + k - q) % 7;
		d = (19*a + M) % 30;
		e = (2*b + 4*c + 6*d + N) % 7;
		
		if(d+e < 10){
			easterDate = "Påsken infaller " + (22 + d + e) + " mars.";
		}
		
		else{
			if(d+e-9 > 15) count++;	// if easter is later than apr 15, increase the counter
			
			if((d == 29) && (e == 6)){
				easterDate = "Påsken infaller 19 april.";
			}
			else if((d == 28) && (e == 6) && (((11*M+11) % 30) < 19)){
				easterDate = "Påsken infaller 18 april.";
			}
			else{
				easterDate = "Påsken infaller " + (d + e - 9) + " april.";
			}
		}
		
		return easterDate;
	}
	
	// ButtonHandler class
	public class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button){
				String yearString = field.getText();
				try{
					int year = Integer.parseInt(yearString);
					if(year > 2015){
						easterDate.setText(calcEaster(year));
					}
					else{
						easterDate.setText("Ange ett giltigt årtal!");
					}
				}
				catch(Exception ex){
					easterDate.setText("Ange ett giltigt årtal!");
				}
			}
			
		}
		
	} // ButtonHandler class ends here
}
