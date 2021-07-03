package slangwords;

import slangwords.views.MainView;

import java.awt.EventQueue;

import slangwords.data.Slang;
import slangwords.model.SlangModel;

/**
 * Controll application
 * 
 * @author zuy
 *
 */
public class Controller {
	
	public Controller() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/* Getting data from file */
					Slang<String, String> data = new Slang<String, String>();
					data.importData();
					/* Set up model */
					SlangModel<String, String> model = new SlangModel<String, String>(data);
					
					/* Set up view */
					MainView frame = new MainView(model);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
