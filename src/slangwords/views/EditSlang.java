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

public class EditSlang extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SlangModel<String, String> _model;
	private JTextField textSlang;
	private JTextField textDef;
	private JButton btnEdit;
	private JLabel lblJustCan;
	
	/**
	 * Create the panel.
	 */
	public EditSlang(SlangModel<String, String> model) {
		this._model = model;
		
		this.initializeView();
		this.setEventButton();
	}
	
	private void initializeView() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 607, 450);
		setLayout(null);
		
		JLabel lblEditSlang = new JLabel("EDIT SLANG");
		lblEditSlang.setForeground(Color.RED);
		lblEditSlang.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditSlang.setFont(new Font("Arial", Font.BOLD, 30));
		lblEditSlang.setBounds(59, 22, 459, 71);
		add(lblEditSlang);
		
		JLabel lblSlangWord = new JLabel("Slang word");
		lblSlangWord.setFont(new Font("Arial", Font.BOLD, 22));
		lblSlangWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlangWord.setBounds(12, 115, 138, 38);
		add(lblSlangWord);
		
		/* TextField to input Slang word */
		textSlang = new JTextField();
		textSlang.setBounds(168, 113, 427, 45);
		textSlang.setFont(new Font("Arial", Font.PLAIN, 20));
		textSlang.setColumns(10);
		textSlang.setBorder(null);
		add(textSlang);
		
		JLabel lblDefifnition = new JLabel("Defifnition");
		lblDefifnition.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefifnition.setFont(new Font("Arial", Font.BOLD, 22));
		lblDefifnition.setBounds(12, 189, 138, 38);
		add(lblDefifnition);
		
		/* TextField to input Definition */
		textDef = new JTextField();
		textDef.setColumns(10);
		textDef.setBounds(168, 187, 427, 45);
		textDef.setFont(new Font("Arial", Font.PLAIN, 20));
		textDef.setBorder(null);
		add(textDef);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(168, 334, 201, 88);
		btnEdit.setFocusPainted(false);
		add(btnEdit);
		
		lblJustCan = new JLabel("* Just can edit existed Slang in Dictionary");
		lblJustCan.setBounds(138, 264, 380, 15);
		add(lblJustCan);
	}
	
	private void setEventButton() {
		btnEdit.addActionListener(e -> {
			String slangInput = textSlang.getText();
			String defInput = textDef.getText();
			
			String value = this._model.searchSlang(slangInput).toString();
			
			if (slangInput.isBlank() || defInput.isBlank()) {
				return;
			};
			
			if (value.isBlank()) {
				JOptionPane.showMessageDialog(new JPanel(), "No slang in dictionary !", "Error",
				        JOptionPane.ERROR_MESSAGE);
				textSlang.setText("");
			} else {
				int res = JOptionPane.showConfirmDialog(new JPanel(), "Slang can be editted! Do you still want to edit it?", "Confirm",
						JOptionPane.CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				
				if (res == 0) {
					this._model.updateSlang(slangInput, defInput);
					
					textSlang.setText("");
					textDef.setText("");
				};
			}
		});
	}
}
