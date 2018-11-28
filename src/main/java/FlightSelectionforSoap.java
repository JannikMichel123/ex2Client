import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.jersey.api.client.Client;

import SoapInterface.SoapService;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.table.TableModel;
public class FlightSelectionforSoap extends JFrame {


	private JTable table;
	private JTable shoppingTable;
	private JTable seatTable;
	public SoapService soap;
	/**
	 * Create the frame.
	 */
	public FlightSelectionforSoap(SoapService soap) {
		this.soap = soap;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1063, 356);
		ArrayList<Flight> ar = new Gson().fromJson(soap.getFlightlist(), new TypeToken<ArrayList<Flight>>(){}.getType());
		System.out.println(ar.get(0).flightNumber + "this for soap");
		
		String[] s = {"Departure","Flight Number","Destination","Type"};
		Object[][] obj = new Object[ar.size()][6];
		int i = 0;
		for(i=0;i<ar.size();i++) {
				obj[i] = ar.get(i).toArray();
		}		
		DefaultTableModel tableModel = new DefaultTableModel(obj,s) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		getContentPane().setLayout(null);
		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane( table );
		scrollPane.setBounds(0, 0, 450, 264);
		getContentPane().add(scrollPane);
		

		String[] classes = {"United First Class","Economy Plus","Economy"};
		JComboBox comboBox = new JComboBox(classes);
		comboBox.setSelectedItem(classes[0]);
		comboBox.setBounds(126, 276, 200, 29);
		getContentPane().add(comboBox);
		
		
		//shopping Table
		String[] shoppingTitles = {"Flight Number","Row","Number","Price"};
		Object[][] shoppingobj = {{}};
		DefaultTableModel shoppingTableModel = new DefaultTableModel(shoppingobj,shoppingTitles) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		shoppingTable = new JTable(shoppingTableModel);
		
		JScrollPane shoppingscroll = new JScrollPane(shoppingTable);
		shoppingscroll.setBounds(770, 0, 287, 264);
		getContentPane().add(shoppingscroll);
		
		
		//Seat Table
		String[] seatTitles = {"Row","Number","Free"};
		Object[][] seatObj = {{}};
		DefaultTableModel seattableModel = new DefaultTableModel(seatObj,seatTitles) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		seatTable = new JTable(seattableModel);
		JScrollPane seatscroll = new JScrollPane(seatTable);
		seatscroll.setBounds(456, 0, 302, 264);
		getContentPane().add(seatscroll);
		
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String flightnumber = (String)tableModel.getValueAt(row, 1);
			
				String seatclass =(String)comboBox.getSelectedItem();
		//		ArrayList<Seat> ar = new Gson().fromJson(Client.create().resource( "http://localhost:8080/flight/seatlist/"+flightnumber +"?seatclass="+seatclass.replace(" ", "%20")).get( String.class ) , new TypeToken<ArrayList<Seat>>(){}.getType());
				ArrayList<Flight> arf = new Gson().fromJson(soap.getFlightlist(), new TypeToken<ArrayList<Flight>>(){}.getType());
				ArrayList<Seat> ar = null;
				String[] classes = {"United First Class","Economy Plus","Economy"};
				for(int i = 0 ; i< arf.size();i++) {
					if(arf.get(i).flightNumber.equals(flightnumber)) {
						if(seatclass.equals(classes[0])) {
							ar = arf.get(i).UFSeats;
							break;
						}else if(seatclass.equals(classes[1])) {
							ar = arf.get(i).EPSeats;
							break;
						}else if(seatclass.equals(classes[2])) {
							ar = arf.get(i).ESeats;
							break;
						}
					}
				}
				System.out.println(ar.get(0).row );
			
				Object[][] obj = new Object[ar.size()][6];
				int i = 0;
				for(i=0;i<ar.size();i++) {
						obj[i] = ar.get(i).toArray();
				}	
				
				showSeats(obj);
				table.setEnabled(false);
				
				
			}
		});
		btnNewButton.setBounds(327, 276, 117, 29);
		getContentPane().add(btnNewButton);
		


		JButton btnNewButton_1 = new JButton("Add to Shopping Cart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String flightNumber = (String)tableModel.getValueAt(row, 1);
				String seatClass =(String)comboBox.getSelectedItem();
				int seatSelectedRow = seatTable.getSelectedRow();
				String seatRow = (String) seatTable.getModel().getValueAt(seatSelectedRow, 0);
				String seatNumber = (String) seatTable.getModel().getValueAt(seatSelectedRow, 1);
				addToShoppingCart(flightNumber,seatRow,seatNumber,seatClass);
			}
		});
		btnNewButton_1.setBounds(586, 276, 172, 29);
		getContentPane().add(btnNewButton_1);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get selected item
				int row = shoppingTable.getSelectedRow();
				String flightNumber = (String)shoppingTable.getModel().getValueAt(row, 0);
				String seatRow =  (String)shoppingTable.getModel().getValueAt(row, 1);
				String seatNumber =  (String)shoppingTable.getModel().getValueAt(row, 2);
				String seatClass =  (String)shoppingTable.getModel().getValueAt(row, 3);
				
				//get the state of selected seat
				if(!FlightSelectionforSoap.this.soap.bookFlight(flightNumber, seatClass, seatRow+seatNumber)) {
					JFrame frame = new JFrame("InputDialog Example #2");
					JOptionPane.showMessageDialog(frame, "This seat is already taken. Please Choose another one.");
				}
				else {
					JFrame frame = new JFrame("InputDialog Example #2");
					JOptionPane.showMessageDialog(frame, "Book the seat sucessfully.");
				}
				
			}
		});
		btnBook.setBounds(940, 276, 117, 29);
		getContentPane().add(btnBook);
		
		JButton btnGoToFlight = new JButton("Go to Flight Selection");
		btnGoToFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSeats(new Object[0][0]);
				table.setEnabled(true);
				addToShoppingCart("","","","");
			}
		});
		btnGoToFlight.setBounds(586, 299, 172, 29);
		getContentPane().add(btnGoToFlight);
		
		
		

		//this.setVisible(true);

	}
	public void addToShoppingCart(String flightNumber,String row, String number,String seatClass) {
		
		Object[][] obj = {{flightNumber,row,number,seatClass}};
		String[] shoppingTitles = {"Flight Number","Row","Number","Class"};

		DefaultTableModel model = new DefaultTableModel(obj,shoppingTitles);
		
		shoppingTable.setModel(model);
		this.repaint();
		this.setVisible(true);
	}
	public void showSeats(Object[][] obj) {
		
		
		String[] seatTitles = {"Row","Number","Free"};

		DefaultTableModel model = new DefaultTableModel(obj,seatTitles);
		
		seatTable.setModel(model);
		this.repaint();
		this.setVisible(true);
	}
}
