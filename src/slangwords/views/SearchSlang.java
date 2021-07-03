package slangwords.views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.BevelBorder;

import slangwords.data.History;
import slangwords.model.HistoryModel;
import slangwords.model.SlangModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.util.Random;
import java.util.Set;

/**
 * View
 * Search the slang input 
 * 
 * @author zuy
 *
 */
public class SearchSlang extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SlangModel<String, String> _model;
	private HistoryModel _history;

	private JTextField textSlangWord;
	private JTextField textDef;
	
	private JButton btnSearch;
	private JButton btnRandom;
	private JLabel lblSlang;
	private JLabel lblDefinition;


	/**
	 * Create the panel.
	 */
	public SearchSlang(SlangModel<String, String> model, HistoryModel history) {
		this._model = model;
		this._history = history;
		
		this.initializeView();
		this.setEventButton();
	}

	public JTextField getTextSlangWord() {
		return textSlangWord;
	}

	public void setTextSlangWord(JTextField textSlangWord) {
		this.textSlangWord = textSlangWord;
	}
	
	/*
	 * Initialize default View
	 */
	private void initializeView() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 607, 450);
		setLayout(null);
		
		JLabel lblSlangWord = new JLabel("Search slang word");
		lblSlangWord.setFont(new Font("Abyssinica SIL", Font.BOLD | Font.ITALIC, 35));
		lblSlangWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlangWord.setForeground(Color.RED);
		lblSlangWord.setBounds(113, 26, 357, 50);
		add(lblSlangWord);
		
		textSlangWord = new JTextField();
		textSlangWord.setFont(new Font("Arial", Font.PLAIN, 24));
		textSlangWord.setHorizontalAlignment(SwingConstants.LEFT);
		textSlangWord.setBounds(146, 134, 409, 50);
		textSlangWord.setBorder(null);
		add(textSlangWord);
		textSlangWord.setColumns(10);
		
		btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(113, 333, 196, 61);
		btnSearch.setBorder(null);
		add(btnSearch);
		
		btnSearch.setFocusPainted(false);
		
		lblSlang = new JLabel("Slang");
		lblSlang.setForeground(Color.BLACK);
		lblSlang.setFont(new Font("Arial", Font.BOLD, 24));
		lblSlang.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlang.setBounds(12, 134, 116, 56);
		add(lblSlang);
		
		lblDefinition = new JLabel("Definition");
		lblDefinition.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefinition.setForeground(Color.RED);
		lblDefinition.setFont(new Font("Arial", Font.BOLD, 24));
		lblDefinition.setBounds(12, 215, 129, 56);
		add(lblDefinition);
		
		textDef = new JTextField();
		textDef.setBackground(Color.LIGHT_GRAY);
		textDef.setEditable(false);
		textDef.setHorizontalAlignment(SwingConstants.LEFT);
		textDef.setFont(new Font("Arial", Font.BOLD, 20));
		textDef.setColumns(10);
		textDef.setBorder(null);
		textDef.setBounds(146, 220, 409, 50);
		add(textDef);
		
		JLabel lblEnterThe = new JLabel("* Enter the Slang and it's definition appear below !");
		lblEnterThe.setBounds(123, 88, 422, 15);
		add(lblEnterThe);
		
		btnRandom = new JButton("Random");
		btnRandom.setFocusPainted(false);
		btnRandom.setBorder(null);
		btnRandom.setBounds(321, 333, 196, 61);
		add(btnRandom);
	}
	
	/*
	 * Set event for click button
	 * When user click the button, the definition appears and write to the history
	 */
	private void setEventButton() {
		btnSearch.addActionListener(e -> {
			String key = textSlangWord.getText();
			String value = this._model.searchSlang(key).toString();
			
			if (!value.isBlank()) {
				String display = value;
				textDef.setText(display);
				this._history.getHistory().add(new History(key));
			} else {
				textDef.setText("[!] Slang not found");
			}
			
		});
		
		btnRandom.addActionListener(e -> {
			Set<String> slangs = this._model.slangs();
			String anySlang = this.randomSlang(slangs);
			String definition = this._model.searchSlang(anySlang).toString();
			
			textSlangWord.setText(anySlang);
			textDef.setText(definition);
		});
	}
	
	/*
	 * Return an random element in Slang 
	 */
	private String randomSlang(Set<String> slangs) {
		int numSlangs = slangs.size();
		int randomItem = new Random().nextInt(numSlangs);
		int i = 0;
		
		for (String slang: slangs) {
			if (i == randomItem) {
				return slang;
			};
			++i;
		};
		
		return null;
	}
}
