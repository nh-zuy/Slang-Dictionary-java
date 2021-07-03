package slangwords.views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import slangwords.model.SlangModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DeleteSlang extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SlangModel<String, String> _model;
	private JTextField textField;
	private JButton btnDelete;
	private JLabel lblJustCan;
	
	/**
	 * Create the panel.
	 */
	public DeleteSlang(SlangModel<String, String> model) {
		this._model = model;
				
		this.initializeView();
		this.setEventButton();
	}

	private void initializeView() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 607, 450);
		setLayout(null);

		JLabel lblDeleteSlang = new JLabel("DELETE SLANG");
		lblDeleteSlang.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteSlang.setForeground(Color.RED);
		lblDeleteSlang.setFont(new Font("Arial", Font.BOLD, 30));
		lblDeleteSlang.setBounds(119, 27, 348, 71);
		add(lblDeleteSlang);

		JLabel lblSlangWord = new JLabel("Slang word");
		lblSlangWord.setFont(new Font("Arial", Font.BOLD, 20));
		lblSlangWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlangWord.setBounds(12, 158, 127, 43);
		add(lblSlangWord);

		textField = new JTextField();
		textField.setBounds(157, 159, 403, 43);
		textField.setFont(new Font("Arial", Font.BOLD, 30));
		add(textField);
		textField.setColumns(10);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(161, 303, 238, 71);
		btnDelete.setFocusPainted(false);
		add(btnDelete);
		
		lblJustCan = new JLabel("* Just can delete existed Slang in Dictionary");
		lblJustCan.setBounds(119, 252, 393, 15);
		add(lblJustCan);

	}
	private void setEventButton() {
		btnDelete.addActionListener(e -> {
			String slangInput = textField.getText();
			String value = this._model.searchSlang(slangInput).toString();
			
			if (value.isBlank()) {
				JOptionPane.showMessageDialog(new JPanel(), "No slang in dictionary !", "Error",
				        JOptionPane.ERROR_MESSAGE);
			} else {
				int res = JOptionPane.showConfirmDialog(new JPanel(), "Are you sure to delete slang?", "Confirm",
						JOptionPane.CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				
				if (res == 0) {
					this._model.deleteSlang(slangInput);
				};
			}
			
			textField.setText("");
		});
	}
}
