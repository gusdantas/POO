package activities;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Principal{

	private JFrame frame;
	private JTable table;
	private JButton xmlFileBtn;
	private JFileChooser xmlFileChooser;
	private DefaultTableModel dtm;
	private String[] headerColumn = {"conceito","disciplina","codigo","creditos","categoria","situacao","periodo","ano"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
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
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelEscolheXml = new JPanel();
		tabbedPane.addTab("Opções", null, panelEscolheXml, null);
		
		xmlFileBtn = new JButton("Abrir XML");
		xmlFileBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xmlFileChooser = new JFileChooser();
				panelEscolheXml.add(xmlFileChooser);
				
				int returnVal = xmlFileChooser.showOpenDialog(xmlFileBtn);
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
			        	table.setModel(dtm);
			        } catch (Exception e1) {
			        	e1.printStackTrace();
			        }
			        table.setVisible(true);
			    } else {
			        System.out.println("File access cancelled by user.");
			    }
			}
		});
		panelEscolheXml.add(xmlFileBtn);
		
		table = new JTable();
		table.setVisible(false);

		JScrollPane scrollPaneFicha = new JScrollPane(table);
		tabbedPane.addTab("Ficha", null, scrollPaneFicha, null);
		
		JScrollPane scrollPaneProjeto = new JScrollPane();
		tabbedPane.addTab("Projeto", null, scrollPaneProjeto, null);
	}
}
