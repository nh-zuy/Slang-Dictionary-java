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

public class AddSlang extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SlangModel<String, String> _model;
	private JButton btnAdd;
	
	private JTextField textSlang;
	private JTextField textDef;
	private JLabel lblNewLabel;
	private JLabel lblIfNot;
	
	/**
	 * Create the panel.
	 */
	public AddSlang(SlangModel<String, String> model) {
		this._model = model;
		
		this.initializeView();
		this.setEventButton();
	}
	
	private void initializeView() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 607, 450);
		setLayout(null);
		
		JLabel lblSlang = new JLabel("Add New Slang");
		lblSlang.setForeground(Color.RED);
		lblSlang.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlang.setFont(new Font("Dialog", Font.BOLD, 30));
		lblSlang.setBounds(122, 12, 354, 60);
		add(lblSlang);
		
		textSlang = new JTextField();
		textSlang.setFont(new Font("Arial", Font.PLAIN, 24));
		textSlang.setBounds(152, 124, 414, 48);
		add(textSlang);
		textSlang.setColumns(10);
		
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(194, 337, 201, 80);
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(null);
		add(btnAdd);
		
		JLabel lblSlangWord = new JLabel("Slang Word");
		lblSlangWord.setFont(new Font("Arial", Font.BOLD, 18));
		lblSlangWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlangWord.setBounds(12, 124, 122, 48);
		add(lblSlangWord);
		
		JLabel lblDefinition = new JLabel("Definition\n");
		lblDefinition.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefinition.setFont(new Font("Arial", Font.BOLD, 18));
		lblDefinition.setBounds(12, 184, 122, 48);
		add(lblDefinition);
		
		textDef = new JTextField();
		textDef.setFont(new Font("Arial", Font.PLAIN, 24));
		textDef.setColumns(10);
		textDef.setBounds(152, 184, 414, 48);
		add(textDef);
		
		textSlang.setBorder(null);
		textDef.setBorder(null);
		
		lblNewLabel = new JLabel("* If Slang has not existed yet, it can be add !");
		lblNewLabel.setBounds(122, 273, 362, 15);
		add(lblNewLabel);
		
		lblIfNot = new JLabel("* If not, you can OVERRIDE or DUPLICATE it !");
		lblIfNot.setBounds(122, 300, 362, 15);
		add(lblIfNot);
	}
	private void setEventButton() {
		btnAdd.addActionListener(e -> {
			String slangInput = textSlang.getText();
			String defInput = textDef.getText();
			
			/* Check if input field is blank */
			if (slangInput.isBlank() || defInput.isBlank()) {
				return;
			};
			
			/* Get the value if the key exist in dictionary */
			String value = this._model.searchSlang(slangInput);
			
			if (value.isBlank()) {
				String[] options = {"Yes", "No"};
				
				int res = JOptionPane.showOptionDialog(
						new JPanel(),
						"Are you sure about Add Slang?",
						"Add Slang",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[0]
				);
				
				if(res == 0) {
					this._model.addSlang(slangInput, defInput);
					
					textSlang.setText("");
					textDef.setText("");
					validate();
					repaint();
				};
			} else {
				String[] options = {"Override", "Duplicate", "Cancel"};
				
				int res = JOptionPane.showOptionDialog(
						new JPanel(),
						"You want to OVERRIDE or DUPLICATE slang?",
						"Slang Existed In Dictionary !",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[0]
				);
				
				/* Manipulating user choose */
				if(res == 0) {
					this._model.updateSlang(slangInput, defInput);
					textSlang.setText("");
					textDef.setText("");
					validate();
					repaint();
				} else if (res == 1) {
					this._model.duplicateSlang(slangInput, defInput);
					textSlang.setText("");
					textDef.setText("");
					validate();
					repaint();
				}
			}
		});
	}
}
