package slangwords.views;

import java.awt.Color;
import java.awt.Dimension;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.table.JTableHeader;

import slangwords.data.History;
import slangwords.model.HistoryModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class HistoryView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HistoryModel _model;
	
	private JTable tableView;
	private JButton btnClear;
	
	/**
	 * Create the panel.
	 */
	public HistoryView(HistoryModel history) {
		this._model = history;
		
		this.initializeView();
	}

	private void initializeView() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 607, 450);
		
		ArrayList<History> history = this._model.getHistory();
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> row = new Vector<String>();
		Vector<String> headers = new Vector<String>();
		headers.add("Slang word");
		headers.add("[!] Time search");
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String time;
		
		for (History search: history) {
			row.add(search.getKeyword());
			time = search.getTime().format(myFormatObj);
			row.add(time);
			
			data.add(new Vector<String>(row));
			row.clear();
		};
		
		tableView = new JTable(data, headers);
		JTableHeader header = tableView.getTableHeader();
		
		header.setBackground(Color.black);
		header.setForeground(Color.yellow);
		
		tableView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableView.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableView.getColumnModel().getColumn(1).setPreferredWidth(450);
		
		JScrollPane sp = new JScrollPane(tableView);
		sp.setPreferredSize(new Dimension(550, 370));
		add(sp);
		
		/* Initial Button to clear History */
		btnClear = new JButton("Clear");
		btnClear.addActionListener(e -> {
			int res = JOptionPane.showConfirmDialog(new JPanel(), "Are you sure to clear history?", "Confirm",
					JOptionPane.CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE);

			if (res == 0) {
				this._model.clearHistory();
				
				data.clear();
				tableView.validate();
				tableView.repaint();
			};
		});
		add(btnClear);
	}
}
