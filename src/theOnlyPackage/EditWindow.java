package theOnlyPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class EditWindow extends JFrame {

	private JPanel contentPane;
	private JTextField keyStuff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditWindow frame = new EditWindow();
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
	public EditWindow() {
		setTitle("EncryptEdit");
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JEditorPane editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 0, 102));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnSavedesu = new JButton("Save");
		btnSavedesu.setBackground(new Color(0, 0, 0));
		btnSavedesu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saver = new JFileChooser();
				int value = saver.showSaveDialog(EditWindow.this);
				if (value == JFileChooser.APPROVE_OPTION) {
					File file = saver.getSelectedFile();
					FileHelpar.writeFile(file, TheMoreDiverseEncryptor.encrypt(editorPane.getText(), keyStuff.getText()));
				}
			}
		});
		panel.add(btnSavedesu);
		
		JButton btnLoaddesu = new JButton("Load");
		btnLoaddesu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser loader = new JFileChooser();
				int value = loader.showOpenDialog(EditWindow.this);
				if (value == JFileChooser.APPROVE_OPTION) {
					File file = loader.getSelectedFile();
					editorPane.setText(TheMoreDiverseEncryptor.decrypt(FileHelpar.readFile(file), keyStuff.getText()));
				}
			}
		});
		panel.add(btnLoaddesu);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setForeground(Color.WHITE);
		panel.add(lblKey);
		
		keyStuff = new JTextField();
		panel.add(keyStuff);
		keyStuff.setColumns(10);
	}

}
