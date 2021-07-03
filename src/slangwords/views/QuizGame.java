package slangwords.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import slangwords.model.SlangModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

public class QuizGame extends JFrame {

	private SlangModel<String, String> _model;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField questionField;
	private JLabel lblQuestion;
	
	private String question;
	private String answer;
	
	private JButton btnA;
	private JButton btnB;
	private JButton btnC;
	private JButton btnD;
	private JButton btnRestart;
	
	private String mode;
	private int point;
	private JTextField pointField;
	private JButton btnExit;

	/**
	 * Create the frame.
	 */
	public QuizGame(SlangModel<String, String> model) {
		this._model = model;
		point = 0;
		
		this.initialView();
		this.prepareQuiz();
		
		setVisible(true);
	}
	
	/*
	 * Init default view
	 */
	private void initialView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 150, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuizGame = new JLabel("Quiz Game");
		lblQuizGame.setForeground(new Color(255, 0, 0));
		lblQuizGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuizGame.setFont(new Font("Arial", Font.BOLD, 35));
		lblQuizGame.setBounds(180, 12, 223, 81);
		contentPane.add(lblQuizGame);
		
		lblQuestion = new JLabel();
		lblQuestion.setFont(new Font("Arial", Font.BOLD, 16));
		lblQuestion.setBounds(39, 94, 536, 36);
		contentPane.add(lblQuestion);
		
		questionField = new JTextField();
		questionField.setBounds(16, 130, 559, 47);
		questionField.setFont(new Font("Arial", Font.BOLD, 30));
		questionField.setEditable(false);
		contentPane.add(questionField);
		questionField.setColumns(10);
		
		btnA = new JButton("");
		btnA.setBounds(324, 206, 251, 73);
		contentPane.add(btnA);
		
		btnB = new JButton("");
		btnB.setBounds(324, 291, 251, 71);
		contentPane.add(btnB);
		
		btnC = new JButton("");
		btnC.setBounds(16, 206, 251, 73);
		contentPane.add(btnC);
		
		btnD = new JButton("");
		btnD.setBounds(16, 291, 251, 71);
		contentPane.add(btnD);
		
		btnA.setFocusPainted(false);
		btnB.setFocusPainted(false);
		btnC.setFocusPainted(false);
		btnD.setFocusPainted(false);
		
		btnRestart = new JButton("Restart");
		btnRestart.setBounds(458, 47, 117, 25);
		contentPane.add(btnRestart);
		
		JLabel lblPoint = new JLabel("Point");
		lblPoint.setForeground(new Color(0, 128, 0));
		lblPoint.setFont(new Font("Arial", Font.BOLD, 20));
		lblPoint.setBounds(415, 12, 61, 23);
		contentPane.add(lblPoint);
		
		pointField = new JTextField();
		pointField.setEditable(false);
		pointField.setText(String.valueOf(point));
		pointField.setFont(new Font("Arial", Font.BOLD, 15));
		pointField.setBounds(478, 12, 94, 23);
		contentPane.add(pointField);
		pointField.setColumns(10);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(458, 84, 117, 25);
		contentPane.add(btnExit);
		
		setResizable(false);
	}
	
	private void prepareQuiz() {
		/* Random question */
		Set<String> slangs = this._model.slangs();
		Collection<String> definitions = this._model.definitions();
		
		String[] options = {"Slang", "Definition"};
		
		int res = JOptionPane.showOptionDialog(
				new JPanel(),
				"Play quiz with Slang or Definition?",
				"Select Mode",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				options,
				options[0]
		);
		
		/* Manipulating user choose */
		if(res == 0) {
			this.mode = "slang";
			
			lblQuestion.setText("# Question: Which's true definition?");
			question = randomSlang(slangs);
			answer = this._model.searchSlang(question).toString();
			
			btnA.setText(answer);
			btnB.setText(randomDef(definitions));
			btnC.setText(randomDef(definitions));
			btnD.setText(randomDef(definitions));
			
		} else if (res == 1) {
			this.mode = "definition";
			
			lblQuestion.setText("# Question: Which's true slang word?");
			answer = randomSlang(slangs);
			question = this._model.searchSlang(answer).toString();
			
			btnA.setText(answer);
			btnB.setText(randomSlang(slangs));
			btnC.setText(randomSlang(slangs));
			btnD.setText(randomSlang(slangs));

		} else {
			return;
		}
		
		questionField.setText(question);
		
		this.setEventButton();
		
		contentPane.validate();
		contentPane.repaint();
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
	
	/*
	 * Return an random element in Definition
	 */
	private String randomDef(Collection<String> definitions) {
		int numDefs = definitions.size();
		int randomItem = new Random().nextInt(numDefs);
		int i = 0;
		
		for (String definition: definitions) {
			if (i == randomItem) {
				return definition;
			};
			++i;
		};
		
		return null;
	}
	
	/*
	 * Init event for button
	 * Click to choose the answer
	 */
	private void setEventButton() {
		ArrayList<JButton> buttonSet = new ArrayList<JButton>();
		buttonSet.add(btnA);
		buttonSet.add(btnB);
		buttonSet.add(btnC);
		buttonSet.add(btnD);
		
		btnExit.addActionListener(e -> {
			String[] options = {"Yes", "No"};
			
			int res = JOptionPane.showOptionDialog(
					new JPanel(),
					"Are you sure?",
					"Exit",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[0]
			);
			
			if(res == 0) {
				this.dispose();
			};
		});
		
		btnRestart.addActionListener(e -> {
			point = 0;
			pointField.setText(String.valueOf(point));
			
			if (mode == "slang") {
				question = randomSlang(this._model.slangs());
				answer = this._model.searchSlang(question).toString();
				
				for (JButton newBtn: buttonSet) {
					newBtn.setText(randomDef(this._model.definitions()));
				};
			}
			else {
				answer = randomSlang(this._model.slangs());
				question = this._model.searchSlang(answer).toString();
				
				for (JButton newBtn: buttonSet) {
					newBtn.setText(randomSlang(this._model.slangs()));
				};
			}
			
			questionField.setText(question);
			
			Collections.shuffle(buttonSet);
			buttonSet.get(0).setText(answer);
		});
		
		for (JButton btn: buttonSet) {
			btn.addActionListener(e -> {
				if (e.getSource() == btn) {
					if (btn.getText() == answer) {
						point = point + 1;
						pointField.setText(String.valueOf(point));
						
						JOptionPane.showMessageDialog(new JPanel(), "Cool! Great choice !", "Correct",
						        JOptionPane.INFORMATION_MESSAGE);
						
						if (mode == "slang") {
							question = randomSlang(this._model.slangs());
							answer = this._model.searchSlang(question).toString();
							
							for (JButton newBtn: buttonSet) {
								newBtn.setText(randomDef(this._model.definitions()));
							};
						}
						else {
							answer = randomSlang(this._model.slangs());
							question = this._model.searchSlang(answer).toString();
							
							for (JButton newBtn: buttonSet) {
								newBtn.setText(randomSlang(this._model.slangs()));
							};
						}
						
						questionField.setText(question);
						
						Collections.shuffle(buttonSet);
						buttonSet.get(0).setText(answer);
						
					} else {
						String[] options = {"Play Again", "Exit"};
						int res = JOptionPane.showOptionDialog(
								new JPanel(),
								"Continue play?",
								"Wrong Answer",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.ERROR_MESSAGE,
								null,
								options,
								options[0]
						);
						
						if(res != 0) {
							this.dispose();
						} else {
							point = 0;
							pointField.setText(String.valueOf(point));
							
							if (mode == "slang") {
								question = randomSlang(this._model.slangs());
								answer = this._model.searchSlang(question).toString();

								for (JButton newBtn: buttonSet) {
									newBtn.setText(randomDef(this._model.definitions()));
								};
							}
							else {
								answer = randomSlang(this._model.slangs());
								question = this._model.searchSlang(answer).toString();

								for (JButton newBtn: buttonSet) {
									newBtn.setText(randomSlang(this._model.slangs()));
								};
							}

							questionField.setText(question);

							Collections.shuffle(buttonSet);
							buttonSet.get(0).setText(answer);
						}
					}
				}
			});
		}
		
	}
}
