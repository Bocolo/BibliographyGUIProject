package console;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bibliography.Bibliography;
import factories.PublicationFactory;
import publications.BibItem;
import strategies.ArticleGUIInputStrategy;
import strategies.BookGUIInputStrategy;
import strategies.TechReportGUIInputStrategy;

/**
 * This is the class that controls the main gui elements of the program and
 * contains the main method
 * 
 * @author Bronagh
 *
 */
public class ConsoleBibManager {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private PublicationFactory publicationFactory;
	private Bibliography bibliography;

	/**
	 * The main method that runs the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ConsoleBibManager();
	}

	/**
	 * ConsoleBibManager Constructor - calls the main gui and sets the publication
	 * factory and bibliography
	 */
	public ConsoleBibManager() {
		mainGUI();
		publicationFactory = new PublicationFactory(new BookGUIInputStrategy(), new ArticleGUIInputStrategy(),
				new TechReportGUIInputStrategy());
		bibliography = Bibliography.getInstance();
	}

	/**
	 * This method sets up the main GUI creating a JFrame, attaching a Jpanel to the
	 * frame and attaching buttons to the Jpanel sets the frame visible
	 */
	private void mainGUI() {
		mainFrame = new JFrame("Bibliography Manager");
		mainFrame.setSize(600, 600);
		mainFrame.setLayout(new GridLayout(3, 1));
		headerLabel = new JLabel("What would you like to do?", JLabel.CENTER);
		headerLabel.setFont(new Font("Courier", Font.BOLD, 25));
		statusLabel = new JLabel("", JLabel.CENTER);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(3, 1));
		JButton loadButton = new JButton("Load Bibliography");
		JButton saveButton = new JButton("Save Bibliography");
		JButton exitButton = new JButton("Exit");
		JButton deleteButton = new JButton("Delete an Entry");
		JButton viewButton = new JButton("View Bibliography");
		JButton addButton = new JButton("Add a Citation");
		loadButton.setActionCommand("load");
		saveButton.setActionCommand("save");
		exitButton.setActionCommand("exit");
		deleteButton.setActionCommand("delete");
		viewButton.setActionCommand("view");
		addButton.setActionCommand("add");
		loadButton.addActionListener(new ButtonClickListener());
		saveButton.addActionListener(new ButtonClickListener());
		exitButton.addActionListener(new ButtonClickListener());
		deleteButton.addActionListener(new ButtonClickListener());
		viewButton.addActionListener(new ButtonClickListener());
		addButton.addActionListener(new ButtonClickListener());
		controlPanel.add(loadButton);
		controlPanel.add(saveButton);
		controlPanel.add(deleteButton);
		controlPanel.add(viewButton);
		controlPanel.add(addButton);
		controlPanel.add(exitButton);
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	/**
	 * displays a jpanel requesting input of a citekey, with an ok - cancel
	 * Joption_pane. if ok is selected a biblioghraphy entry with the input citekey
	 * is removed from the bibliographies tree map
	 */
	private void deleteEntryGUI() {
		JTextField citeKey = new JTextField(5);
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.add((new JLabel("Please Enter The Cite Key of the Entry You Would Like to Delete:")));
		myPanel.add((new JLabel("CiteKey:")));
		myPanel.add(citeKey);
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Delete an Entry", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			bibliography.removeFromTree(citeKey.getText());
		}
	}

	/**
	 * creates a bibItem called publication displays a JOptionPane asking user to
	 * select the a publication type to add options - "Book", "Article", "Tech
	 * Report" if a publication Type is chosen, sets publication to the returned
	 * value of publicationFactory.createBibItem(), which is passed the chosen
	 * publication type as a parameter. if the returned publication is not null, it
	 * is added to the bibliogrpahys TreeMap, using its citekey as a key
	 */
	private void publicationChoiceGUI() {
		BibItem publication;
		String[] choices = { "Book", "Article", "Tech Report" };
		String publicationType = (String) JOptionPane.showInputDialog(mainFrame, "Pick a Publication Type:",
				"Publication Choice Dialog", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		publication = publicationFactory.createBibItem(publicationType);
		if (publication != null) {
			bibliography.addToTree(publication.getCiteKey(), publication);
		}
	}

	/**
	 * displays a JOptionPane asking user to select the style /type of bibliography
	 * they would like printed options - Harvard / bibTex will call
	 * biblioPrintLocationGUI passing the chosen option if the optionis not null
	 */
	private void bibliographyTypeGUI() {
		String[] choices = { "Harvard", "BibTex" };
		String picked = (String) JOptionPane.showInputDialog(mainFrame, "What kind of Bibliograph do you want?",
				"Bibliograph Choice Dialog", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if (picked != null) {
			biblioPrintLocationGUI(picked);
		}
	}

	/**
	 * displays a JOptionPane asking user where they would like the bibliogprahy
	 * printed options - GUI / Console if the option choice is not null, it will
	 * either call viewBibliographyConsole or viewBibliographyGUI based on the
	 * chosen option. it will pass biblioType string to these methods
	 * 
	 * @param biblioType string representing the type of bibliography style to be
	 *                   printed - Harvard / BibTex
	 */
	private void biblioPrintLocationGUI(String biblioType) {
		String[] choices = { "GUI", "Console" };
		String picked = (String) JOptionPane.showInputDialog(mainFrame,
				"Where would you like the bibliography printed?", "Printing Choice", JOptionPane.QUESTION_MESSAGE, null,
				choices, choices[0]);
		if (picked != null) {
			if (picked.equals("Console")) {
				viewBibliographyConsole(biblioType);
			} else if (picked.equals("GUI")) {
				viewBibliographyGUI(biblioType).setVisible(true);
			}
		}
	}

	/**
	 * method will loop over bibliography's bibitems and print them to console.
	 * print style will be based on the string input, using either toString or
	 * toBibTexString bibItem methods
	 * 
	 * @param biblioType a string representing the bibliography display type -
	 *                   choices Harvard or BibTex
	 */
	private void viewBibliographyConsole(String biblioType) {
		List<BibItem> bibItemByYear = bibliography.getSortedBibliography();
		if (biblioType.equals("Harvard")) {
			for (BibItem bibItem : bibItemByYear) {
				System.out.println("\n" + bibItem.toString());
			}
		} else {
			for (BibItem bibItem : bibItemByYear) {
				System.out.println(bibItem.toBibTexString() + "\n");
			}
		}
	}

	/**
	 * creates a JDialog to view the bibliography. method will loop over
	 * bibliography's bibitems and add them to the JDialog's panel with an empty
	 * line between entries if the biblioType is not "Harvard" each bibTex item in
	 * the loop will be edited, replacing blank lines with <br>
	 * and surrounding each bibItem with html tags an ok button is added that sets
	 * the dialog invisible when selected
	 * 
	 * @param biblioType string representing the bibliography display type - choices
	 *                   Harvard or BibTex
	 * @return the create JDialog is returned
	 */
	private JDialog viewBibliographyGUI(String biblioType) {
		final JDialog biblioDialog = new JDialog(mainFrame, "Bibliography Viewer", Dialog.ModalityType.DOCUMENT_MODAL);
		biblioDialog.setBounds(132, 132, 600, 600);
		Container dialogContainer = biblioDialog.getContentPane();
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		List<BibItem> bibItemByYear = bibliography.getSortedBibliography();
		if (biblioType.equals("Harvard")) {
			for (BibItem bibItem : bibItemByYear) {
				JLabel bibLabel = new JLabel(bibItem.toString());
				myPanel.add(bibLabel);
				myPanel.add(Box.createVerticalStrut(10));
			}
		} else {
			for (BibItem bibItem : bibItemByYear) {
				String bibTexText = bibItem.toBibTexString();
				bibTexText = bibTexText.replace("\n", "<br>");
				bibTexText = ("<html>" + bibTexText + "</html>");
				JLabel bibLabel = new JLabel(bibTexText);
				myPanel.add(bibLabel);
				myPanel.add(Box.createVerticalStrut(20));
			}
		}
		dialogContainer.setLayout(new BorderLayout());
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				biblioDialog.setVisible(false);
			}
		});
		dialogContainer.add(myPanel, BorderLayout.NORTH);
		dialogContainer.add(okButton, BorderLayout.SOUTH);
		return biblioDialog;
	}

	/**
	 * produces a JPanel that accepts input if the user selects ok, the saveToFile
	 * method of the bibliography instance will save the file with the entered input
	 * as a file name
	 */
	private void saveFileGUI() {
		JTextField filenameInput = new JTextField(5);
		JPanel myPanel = new JPanel();
		myPanel.add((new JLabel("Please enter a filename:")));
		myPanel.add(filenameInput);
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Save Bibliography", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			bibliography.saveToFile(filenameInput.getText());
		}
	}

	/**
	 * This class implements the actions listener interface
	 */
	private class ButtonClickListener implements ActionListener {
		/**
		 * this method saves the action event's action command to a string an if else
		 * block then execute the relevant methods and status label updates based on the
		 * command
		 * 
		 * @param e this is an action event
		 */
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("load")) {
				statusLabel.setText("Load Button clicked.");
			} else if (command.equals("save")) {
				statusLabel.setText("Save Button clicked.");
				saveFileGUI();
			} else if (command.equals("add")) {
				statusLabel.setText("Add Citation Button clicked.");
				publicationChoiceGUI();
			} else if (command.equals("delete")) {
				statusLabel.setText("Delete Button clicked.");
				deleteEntryGUI();
			} else if (command.equals("view")) {
				statusLabel.setText("View Button clicked.");
				bibliographyTypeGUI();
			} else {
				System.exit(0);
			}
		}
	}
}