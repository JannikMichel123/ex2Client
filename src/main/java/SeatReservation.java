import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;

import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class SeatReservation extends JDialog {
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public SeatReservation(String flightNumber,String seatClass) {
		setBounds(100, 100, 314, 465);
		
		
		//TODO get the Seat List
		ArrayList<Seat> ar = new Gson().fromJson(Client.create().resource( "http://localhost:8080/flight/seatlist/"+flightNumber +"?seatclass="+seatClass.replace(" ", "%20") ).get( String.class ) , new TypeToken<ArrayList<Seat>>(){}.getType());
		System.out.println(ar.get(0).row);
	
		Object[][] obj = new Object[ar.size()][6];
		int i = 0;
		for(i=0;i<ar.size();i++) {
				obj[i] = ar.get(i).toArray();
		}		
		String[] s = {"Row","Number","Free"};
		DefaultTableModel tableModel = new DefaultTableModel(obj,s) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		getContentPane().setLayout(null);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 404, 314, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 6, 302, 388);
		getContentPane().add(scrollPane);

	}
}
