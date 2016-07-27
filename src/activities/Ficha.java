package activities;

import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Ficha {

	private JFrame frame;
	JMenuBar menuBar;
	JMenu mnArquivo;
	JFileChooser xmlFileChooser;
	private JTable table;
	private DefaultTableModel dtm;
	private String[] headerColumn = {"conceito","disciplina","codigo","creditos","categoria","situacao","periodo","ano"};
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ficha window = new Ficha();
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
	public Ficha() {
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
		
		int returnVal = xmlFileChooser.showOpenDialog(mnArquivo);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        
	        try {
	        	File XmlFile = xmlFileChooser.getSelectedFile();
	        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        	Document doc = dBuilder.parse(XmlFile);
	        			
	        	//optional, but recommended
	        	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	        	doc.getDocumentElement().normalize();
	        	
	        	dtm = new DefaultTableModel(0, 0);
	        	dtm.setColumnIdentifiers(headerColumn);
	        			
	        	NodeList nList = doc.getElementsByTagName("Disciplina");     			

	        	for (int temp = 0; temp < nList.getLength(); temp++) {

	        		Node nNode = nList.item(temp);
	        				
	        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	        			Element eElement = (Element) nNode;
	        			
	        			dtm.addRow(new Object[] {eElement.getElementsByTagName("codigo").item(0).getTextContent(),
    					eElement.getElementsByTagName("disciplina").item(0).getTextContent(),
    					eElement.getElementsByTagName("creditos").item(0).getTextContent(),
    					eElement.getElementsByTagName("conceito").item(0).getTextContent(),
    					eElement.getElementsByTagName("situacao").item(0).getTextContent(),
    					eElement.getElementsByTagName("categoria").item(0).getTextContent(),
    					eElement.getElementsByTagName("periodo").item(0).getTextContent(),
    					eElement.getElementsByTagName("ano").item(0).getTextContent()});
	        		}
	        	}
	            } catch (Exception e) {
	        	e.printStackTrace();
	            }
	    } else {
	        System.out.println("File access cancelled by user.");
	    }
		table = new JTable(dtm);
		scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
