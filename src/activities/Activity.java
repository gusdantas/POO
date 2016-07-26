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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

	        	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	        	textArea.setText("conceito | disciplina | codigo | creditos | categoria | situacao | periodo | ano\n");
	        	System.out.println("conceito | disciplina | codigo | creditos | categoria | situacao | periodo | ano");
	        			
	        	NodeList nList = doc.getElementsByTagName("Disciplina");
	        			
	        	System.out.println("----------------------------");

	        	for (int temp = 0; temp < nList.getLength(); temp++) {

	        		Node nNode = nList.item(temp);
	        				
	        		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
	        				
	        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	        			Element eElement = (Element) nNode;

	        			/*System.out.println("Staff id : " + eElement.getAttribute("id"));
	        			System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
	        			System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
	        			System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
	        			System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());*/
	        			textArea.setText(eElement.getElementsByTagName("codigo").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("disciplina").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("creditos").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("conceito").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("situacao").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("categoria").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("periodo").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("ano").item(0).getTextContent()+"\n");
	        			System.out.println(eElement.getElementsByTagName("codigo").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("disciplina").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("creditos").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("conceito").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("situacao").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("categoria").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("periodo").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("ano").item(0).getTextContent());

	        		}
	        	}
	            } catch (Exception e) {
	        	e.printStackTrace();
	            }
	    } else {
	        System.out.println("File access cancelled by user.");
	    }
	}
	
	/*private void OpenActionPerformed(java.awt.event.ActionEvent evt) {
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

	        	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	        	System.out.println("conceito | disciplina | codigo | creditos | categoria | situacao | periodo | ano");
	        			
	        	NodeList nList = doc.getElementsByTagName("Disciplina");
	        			
	        	System.out.println("----------------------------");

	        	for (int temp = 0; temp < nList.getLength(); temp++) {

	        		Node nNode = nList.item(temp);
	        				
	        		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
	        				
	        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	        			Element eElement = (Element) nNode;

	        			/*System.out.println("Staff id : " + eElement.getAttribute("id"));
	        			System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
	        			System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
	        			System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
	        			System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
	        			System.out.println(eElement.getElementsByTagName("codigo").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("disciplina").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("creditos").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("conceito").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("situacao").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("categoria").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("periodo").item(0).getTextContent()+
	        					" | "+eElement.getElementsByTagName("ano").item(0).getTextContent());

	        		}
	        	}
	            } catch (Exception e) {
	        	e.printStackTrace();
	            }
	    } else {
	        System.out.println("File access cancelled by user.");
	    }
	} */

}
