package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import MV.GeneradorFichero;
import MV.Interprete;
import parser.AnalizadorSintactico;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JTextField textField_1;
	private JLabel lblIntroduzcaLaRuta_1;
	
	private AnalizadorSintactico analizadorSintactico;
	private Interprete interprete;
	private GeneradorFichero generadorFichero;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAnalizador = new JButton("Analizar");
		btnAnalizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rutaArchivo = textField.getText();
				analizadorSintactico = new AnalizadorSintactico(rutaArchivo);
				analizadorSintactico.compilar();
				generadorFichero=new GeneradorFichero(); 
				generadorFichero.generaFichero(textField_1.getText(), analizadorSintactico.getByteOut());//te lo crea en el directorio del proyecto 
				analizadorSintactico.printParser();
			}
		});
		btnAnalizador.setBounds(375, 46, 175, 43);
		contentPane.add(btnAnalizador);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interprete = new Interprete();
				interprete.generar(textField_1.getText(), 0);
			}
		});
		btnEjecutar.setBounds(375, 109, 175, 43);
		contentPane.add(btnEjecutar);
		
		textField = new JTextField();
		textField.setBounds(21, 52, 317, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(21, 187, 529, 302);
		contentPane.add(textArea);
		
		JLabel lblIntroduzcaLaRuta = new JLabel("Ruta del archivo del programa a leer:");
		lblIntroduzcaLaRuta.setBounds(21, 24, 246, 16);
		contentPane.add(lblIntroduzcaLaRuta);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(21, 127, 317, 28);
		contentPane.add(textField_1);
		
		lblIntroduzcaLaRuta_1 = new JLabel("Ruta del archivo del c\u00F3digo compilado:");
		lblIntroduzcaLaRuta_1.setBounds(21, 99, 281, 16);
		contentPane.add(lblIntroduzcaLaRuta_1);
	}
}
