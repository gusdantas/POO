package activities;

import java.awt.EventQueue;
import java.io.File;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class Activity {

	private JFrame frame;
	JMenuBar menuBar;
	JMenu mnArquivo;
	JFileChooser xmlFileChooser;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Activity window = new Activity();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Activity() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		xmlFileChooser = new JFileChooser();
		mnArquivo.add(xmlFileChooser);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
	}
	
	private void OpenActionPerformed(java.awt.event.ActionEvent evt) {
	    int returnVal = xmlFileChooser.showOpenDialog(mnArquivo);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        File file = xmlFileChooser.getSelectedFile();
	        SAXBuilder sb = new SAXBuilder();

	        file.
	        
	        try {
	          // What to do with the file, e.g. display it in a TextArea
	          //textarea.read( new FileReader( file.getAbsolutePath() ), null );
	        
	        } catch (IOException ex) {
	          System.out.println("problem accessing file"+file.getAbsolutePath());
	        }
	    } else {
	        System.out.println("File access cancelled by user.");
	    }
	} 

}
