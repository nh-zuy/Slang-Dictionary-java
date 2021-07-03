package slangwords.views;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Vector;

import slangwords.model.SlangModel;

/*
 * View form Search Definition
 */
public class SearchDef extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SlangModel<String, String> _model;
	private JTable tableView;
	private JTextField textField;
	private JButton btnSearch;
	private JPanel panelData;
	
	/**
	 * Create the panel.
	 */
	public SearchDef(SlangModel<String, String> model) {
		this._model = model;
		
		this.initializeView();	
		this.setEventButton();
	}
	
	/*
	 * Initializing default view of Search Definition
	 */
	private void initializeView() {
		/* Set up panel */
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 607, 450);
		setLayout(null);
		/* Big lebal */
		JLabel lblSearchDefinition = new JLabel("Search Definition");
		lblSearchDefinition.setForeground(Color.RED);
		lblSearchDefinition.setFont(new Font("Arial", Font.BOLD, 25));
		lblSearchDefinition.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchDefinition.setBounds(71, 12, 449, 50);
		add(lblSearchDefinition);
		
		JLabel lblDefinition = new JLabel("Keyword");
		lblDefinition.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefinition.setFont(new Font("Arial", Font.BOLD, 20));
		lblDefinition.setBounds(28, 67, 143, 33);
		add(lblDefinition);
		
		/* Enter the definition */
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 25));
		textField.setBounds(172, 65, 392, 41);
		textField.setBorder(null);
		add(textField);
		textField.setColumns(10);
		/* Button search */
		btnSearch = new JButton("Search");
		btnSearch.setBounds(189, 118, 117, 25);
		btnSearch.setFocusPainted(false);
		btnSearch.setBorder(null);
		add(btnSearch);
		/* Table showing data */
		panelData = new JPanel();
		panelData.setBackground(Color.LIGHT_GRAY);
		panelData.setBounds(28, 159, 556, 279);
		add(panelData);
	}
	
	/*
	 * Setting up event for button
	 */
	private void setEventButton() {
		btnSearch.addActionListener(e -> {
			/* Get data from user input */
			String def = textField.getText();
			/* Search all relevant keys about input */
			ArrayList<String> relevantKeys = this._model.searchDef(def);
			
			panelData.removeAll();
			
			/* Check if any key found */
			if (relevantKeys != null) {
				Vector<Vector<String>> data = new Vector<Vector<String>>();
				Vector<String> row = new Vector<String>();
				String key, value;
			
				/* Add data into row */
				for (int i = 0; i < relevantKeys.size(); ++i) {
					key = relevantKeys.get(i);
					value = this._model.searchSlang(relevantKeys.get(i));
					
					row.add(key);
					row.add(value);
					data.add(new Vector<String>(row));
					row.clear();
				};
				
				Vector<String> headers = new Vector<String>();
				headers.add("[*] SLANG");
				headers.add("[!] DEFINITION");
				
				tableView = new JTable(data, headers);
				JTableHeader header = tableView.getTableHeader();
				
				header.setBackground(Color.black);
				header.setForeground(Color.yellow);
				
				tableView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tableView.getColumnModel().getColumn(0).setPreferredWidth(100);
				tableView.getColumnModel().getColumn(1).setPreferredWidth(500);
				
				JScrollPane sp = new JScrollPane(tableView);
				sp.setPreferredSize(new Dimension(550, 270));
				panelData.add(sp);
			}
			
			/* Repainting panel */
			validate();
			repaint();
		});
	}
}
