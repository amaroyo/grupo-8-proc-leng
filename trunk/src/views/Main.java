package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import MV.GeneradorFichero;
import MV.Interprete;
import parser.AnalizadorSintactico;
import javax.swing.JScrollPane;

public class Main extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
//	private JTextField textField_1;
//	private JLabel lblIntroduzcaLaRuta_1;
	
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
		
		JButton btnAnalizador = new JButton("Ejecutar");
		btnAnalizador.setBounds(375, 46, 175, 43);
		btnAnalizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rutaArchivo = textField.getText();
				analizadorSintactico = new AnalizadorSintactico(rutaArchivo);
				if (!(analizadorSintactico.getSalida().equals("El fichero no existe"))){
					
					if (analizadorSintactico.compilar())
					{
						try {
						    // a jframe here isn't strictly necessary, but it makes the example a little more real
						    JFrame frame = new JFrame("Error de Compilaci�n");

						    // prompt the user to enter their name
						    JOptionPane.showMessageDialog(frame, "Error en compilaci�n");
						    analizadorSintactico.printParser();
						    textArea.setText(analizadorSintactico.getSalida());
						
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					else
					{
						generadorFichero=new GeneradorFichero(); 
						
						generadorFichero.generaFichero("binario.txt", analizadorSintactico.getByteOut());//te lo crea en el directorio del proyecto
						Interprete inter= new Interprete();
						try {
							inter.generar("binario.txt", 0, analizadorSintactico.datosParaInterprete());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
							try {
							    // a jframe here isn't strictly necessary, but it makes the example a little more real
							    JFrame frame = new JFrame("Error de ejecucion");
	
							    // prompt the user to enter their name
							    JOptionPane.showMessageDialog(frame, "Error en tiempo de ejecucion");
							    textArea.setText(inter.imprimirMemoria());
							
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
						analizadorSintactico.printParser();
						textArea.setText(analizadorSintactico.getSalida()+inter.imprimirMemoria());
					}
				}
				else{
					textArea.setText(analizadorSintactico.getSalida());
				}
				//textArea.setText(analizadorSintactico.getSalida()+inter.imprimirMemoria());
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnAnalizador);
		
/*		JButton btnEjecutar = new JButton("Ejecutar");//No se puede ejecutar solo con el binario porque los datos de la memoria no estan ahi, por lo que hay que analizar y ejutar al mismo tiempo. Este boton no creo que sirva
		btnEjecutar.setBounds(375, 109, 175, 43);
		btnEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interprete = new Interprete();
				interprete.generar(textField_1.getText(), 0,analizadorSintactico.datosParaInterprete());//no funciona porque analizadorSintactico es null y es necesario tener datos de la memoria
				textArea.append(interprete.muestraMemoria());
				textArea.append(interprete.muestraPila());
			}
		});
		contentPane.add(btnEjecutar);*/
		
		textField = new JTextField();
		textField.setBounds(21, 52, 317, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIntroduzcaLaRuta = new JLabel("Ruta del archivo del programa:");
		lblIntroduzcaLaRuta.setBounds(21, 24, 246, 16);
		contentPane.add(lblIntroduzcaLaRuta);
		
/*		textField_1 = new JTextField();
		textField_1.setBounds(21, 127, 317, 28);
		textField_1.setColumns(10);
		contentPane.add(textField_1);*/
		
/*		lblIntroduzcaLaRuta_1 = new JLabel("Ruta del archivo del c\u00F3digo compilado:");
		lblIntroduzcaLaRuta_1.setBounds(21, 99, 281, 16);
		contentPane.add(lblIntroduzcaLaRuta_1);*/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 185, 529, 304);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
	}
}
