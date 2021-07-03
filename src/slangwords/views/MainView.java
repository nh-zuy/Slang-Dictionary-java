package slangwords.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import slangwords.model.HistoryModel;
import slangwords.model.SlangModel;


public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SlangModel<String, String> _model;
	private HistoryModel _history;

	private JPanel _mainView;

	private JPanel toolbar;
	private JPanel workspace;
	
	private JButton btnSearchSlang;
	private JButton btnSearchDef;
	private JButton btnAddSlang;
	private JButton btnEditSlang;
	private JButton btnDeleteSlang;
	private JButton btnReset;
	private JButton btnQuiz;
	private JButton btnHistory;
	private JLabel lblApplicationBuiltOn;
	private JLabel lblEnvJdk;
	private JLabel lblEnviroment;
	private JLabel lblUserMvc;
	private JLabel lblDescribe;
	private JLabel lblHistoryBrowsing;
	
	
	/**
	 * Create the frame.
	 */
	public MainView(SlangModel<String, String> model) { 
		this._model = model;
		this._history = new HistoryModel();
		this.initializeGUI();
	}
	
	private void initializeGUI() {
		/* Set up main frame */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 800, 500);
		_mainView = new JPanel();
		_mainView.setBackground(Color.WHITE);
		_mainView.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(_mainView);
		_mainView.setLayout(null);
		setResizable(false);
		
		/* Set up left Toolbar */
		toolbar = new JPanel();
		toolbar.setBorder(null);
		toolbar.setBackground(Color.DARK_GRAY);
		toolbar.setBounds(0, 0, 169, 474);
		_mainView.add(toolbar);
		toolbar.setLayout(null);
		
		/* Header application */
		JPanel header = new JPanel();
		header.setBorder(null);
		header.setBackground(SystemColor.desktop);
		header.setBounds(0, 0, 180, 106);
		toolbar.add(header);
		header.setLayout(null);
		
		JLabel lblSlang = new JLabel("SLANG");
		lblSlang.setForeground(Color.WHITE);
		lblSlang.setFont(new Font("Andale Mono", Font.BOLD | Font.ITALIC, 20));
		lblSlang.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlang.setBounds(0, 12, 168, 43);
		header.add(lblSlang);
		
		JLabel lblApplication = new JLabel("APPLICATION");
		lblApplication.setHorizontalAlignment(SwingConstants.CENTER);
		lblApplication.setForeground(Color.WHITE);
		lblApplication.setFont(new Font("Andale Mono", Font.BOLD | Font.ITALIC, 20));
		lblApplication.setBounds(0, 51, 168, 43);
		header.add(lblApplication);
		
		/* Button search slang */
		btnSearchSlang = new JButton("Search Slang");
		btnSearchSlang.setFont(new Font("Bitstream Charter", Font.BOLD, 15));
		btnSearchSlang.setForeground(Color.BLACK);
		btnSearchSlang.setBackground(Color.ORANGE);
		btnSearchSlang.setBounds(10, 167, 145, 37);
		btnSearchSlang.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toolbar.add(btnSearchSlang);
		
		/* Button search definition */
		btnSearchDef = new JButton("Search Definition");
		btnSearchDef.setForeground(Color.WHITE);
		btnSearchDef.setFont(new Font("Bitstream Charter", Font.BOLD, 15));
		btnSearchDef.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSearchDef.setBackground(Color.GREEN);
		btnSearchDef.setBounds(10, 118, 145, 37);
		toolbar.add(btnSearchDef);
		
		/* Button add slang */
		btnAddSlang = new JButton("Add Slang");
		btnAddSlang.setForeground(Color.BLACK);
		btnAddSlang.setFont(new Font("Bitstream Charter", Font.BOLD, 15));
		btnAddSlang.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAddSlang.setBackground(Color.ORANGE);
		btnAddSlang.setBounds(10, 216, 145, 37);
		toolbar.add(btnAddSlang);
		
		/* Button edit slang */
		btnEditSlang = new JButton("Edit Slang");
		btnEditSlang.setForeground(Color.BLACK);
		btnEditSlang.setFont(new Font("Bitstream Charter", Font.BOLD, 15));
		btnEditSlang.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEditSlang.setBackground(Color.ORANGE);
		btnEditSlang.setBounds(10, 265, 145, 37);
		toolbar.add(btnEditSlang);
		
		/* Button delete slang */
		btnDeleteSlang = new JButton("Delete Slang");
		btnDeleteSlang.setForeground(Color.BLACK);
		btnDeleteSlang.setFont(new Font("Bitstream Charter", Font.BOLD, 15));
		btnDeleteSlang.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDeleteSlang.setBackground(Color.ORANGE);
		btnDeleteSlang.setBounds(10, 315, 145, 37);
		toolbar.add(btnDeleteSlang);
		
		/* Button reset all the dictionary */
		btnReset = new JButton("Reset");
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Bitstream Charter", Font.BOLD, 15));
		btnReset.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnReset.setBackground(Color.RED);
		btnReset.setBounds(10, 363, 69, 37);
		toolbar.add(btnReset);
		
		/* Button quiz */
		btnQuiz = new JButton("Quiz");
		btnQuiz.setForeground(Color.WHITE);
		btnQuiz.setFont(new Font("Bitstream Charter", Font.BOLD, 15));
		btnQuiz.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnQuiz.setBackground(Color.GREEN);
		btnQuiz.setBounds(10, 412, 145, 37);
		toolbar.add(btnQuiz);
		
		btnSearchSlang.setFocusPainted(false);
		btnSearchDef.setFocusPainted(false);
		btnAddSlang.setFocusPainted(false);
		btnEditSlang.setFocusPainted(false);
		btnDeleteSlang.setFocusPainted(false);
		btnReset.setFocusPainted(false);
		btnQuiz.setFocusPainted(false);
		/* Button to get in History */
		btnHistory = new JButton("History");
		btnHistory.setForeground(Color.WHITE);
		btnHistory.setFont(new Font("Bitstream Charter", Font.BOLD, 15));
		btnHistory.setFocusPainted(false);
		btnHistory.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnHistory.setBackground(Color.RED);
		btnHistory.setBounds(86, 364, 69, 37);
		toolbar.add(btnHistory);
		
		/* Set up Workspace */
		workspace = new JPanel();
		workspace.setBounds(181, 12, 607, 450);
		workspace.setLayout(null);
		_mainView.add(workspace);
		
		this.defaultView();
		
		
		this.setEventButton();
	}
	
	private void defaultView() {
		JLabel lblSlangDictionary = new JLabel("SLANG DICTIONARY");
		lblSlangDictionary.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlangDictionary.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
		lblSlangDictionary.setForeground(Color.RED);
		lblSlangDictionary.setBackground(Color.WHITE);
		lblSlangDictionary.setBounds(79, 12, 455, 67);
		workspace.add(lblSlangDictionary);
		
		JLabel lblJavaApplicationProgramming = new JLabel("Java Application programming");
		lblJavaApplicationProgramming.setFont(new Font("Dialog", Font.BOLD, 15));
		lblJavaApplicationProgramming.setHorizontalAlignment(SwingConstants.CENTER);
		lblJavaApplicationProgramming.setBounds(107, 63, 398, 32);
		workspace.add(lblJavaApplicationProgramming);
		
		JLabel lblNameNguyenNhat = new JLabel("[$] Name: Nguyen Nhat Duy");
		lblNameNguyenNhat.setBounds(79, 124, 275, 15);
		workspace.add(lblNameNguyenNhat);
		
		JLabel lblMssv = new JLabel("[$] MSSV: 19120495");
		lblMssv.setBounds(79, 151, 198, 15);
		workspace.add(lblMssv);
		
		JLabel lblAppCanBe = new JLabel("[*] Application can be used to Search, Add, Edit, Delete ");
		lblAppCanBe.setBounds(79, 304, 473, 32);
		workspace.add(lblAppCanBe);
		
		JLabel lblWithFunnySlang = new JLabel("and play Quiz with funny Slang words\n");
		lblWithFunnySlang.setBounds(79, 326, 442, 32);
		workspace.add(lblWithFunnySlang);
		
		lblApplicationBuiltOn = new JLabel("[!] Built on Java Swing");
		lblApplicationBuiltOn.setBounds(79, 223, 473, 15);
		workspace.add(lblApplicationBuiltOn);
		
		lblEnvJdk = new JLabel("[!] JDK 11 amd 64, JRE 11 [JavaSE-11]");
		lblEnvJdk.setBounds(79, 205, 473, 15);
		workspace.add(lblEnvJdk);
		
		lblEnviroment = new JLabel("Enviroment");
		lblEnviroment.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEnviroment.setForeground(new Color(0, 128, 128));
		lblEnviroment.setBounds(234, 178, 121, 15);
		workspace.add(lblEnviroment);
		
		lblUserMvc = new JLabel("[*] MVC Architecture implements\n ");
		lblUserMvc.setBounds(79, 289, 398, 15);
		workspace.add(lblUserMvc);
		
		lblDescribe = new JLabel("Describe");
		lblDescribe.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDescribe.setForeground(new Color(0, 128, 128));
		lblDescribe.setBounds(234, 262, 138, 15);
		workspace.add(lblDescribe);
		
		lblHistoryBrowsing = new JLabel("[*] Randomize a slang word, with it's definition\n");
		lblHistoryBrowsing.setBounds(79, 359, 398, 15);
		workspace.add(lblHistoryBrowsing);
		
		JLabel lblHistoryBrowsing_1 = new JLabel("[*] History browsing slang words");
		lblHistoryBrowsing_1.setBounds(79, 381, 398, 15);
		workspace.add(lblHistoryBrowsing_1);
	}
	
	private void setEventButton() {
		/* Button to search slang */
		btnSearchSlang.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new SearchSlang(this._model, this._history));
			workspace.validate();
			workspace.repaint();
			
		});
		/* Button to search definition */
		btnSearchDef.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new SearchDef(this._model));
			workspace.validate();
			workspace.repaint();
		});
		/* Button to add new slang */
		btnAddSlang.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new AddSlang(this._model));
			workspace.validate();
			workspace.repaint();
		});
		/* Button to edit a slang */
		btnEditSlang.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new EditSlang(this._model));
			workspace.validate();
			workspace.repaint();
		});
		/* Button to delete a slang */
		btnDeleteSlang.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new DeleteSlang(this._model));
			workspace.validate();
			workspace.repaint();
		});
		/* Button to reset dictionary */
		btnReset.addActionListener(e -> {
			workspace.removeAll();
			int res = JOptionPane.showConfirmDialog(new JPanel(), "Are you sure to reset all the dictionary?", "Reset",
					JOptionPane.CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			
			if (res == 0) {
				this._model.reset();
			};
			
			this.defaultView();
			this.validate();
			this.repaint();
			
			workspace.validate();
			workspace.repaint();
		});
		/* Button to get history */
		btnHistory.addActionListener(e -> {
			workspace.removeAll();
			workspace.add(new HistoryView(this._history));
			workspace.validate();
			workspace.repaint();
		});
		/* Button to show the Quiz game */
		btnQuiz.addActionListener(e -> {
			new QuizGame(this._model);
		});
	}
	
	/* Main view */
	public JPanel getMainView() {
		return _mainView;
	}

	public void setMainView(JPanel mainView) {
		this._mainView = mainView;
	}

	/* Left Toolbar */
	public JPanel getToolbar() {
		return toolbar;
	}

	public void setToolbar(JPanel toolbar) {
		this.toolbar = toolbar;
	}

	/* Workspace */
	public JPanel getWorkspace() {
		return workspace;
	}

	public void setWorkspace(JPanel workspace) {
		this.workspace = workspace;
	}
}
