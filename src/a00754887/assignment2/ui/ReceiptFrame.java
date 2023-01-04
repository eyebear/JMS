package a00754887.assignment2.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

/**
 * this class creates a dialog for receipt
 * 
 * @author AoAo_Feng
 * 
 */
public class ReceiptFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(ReceiptFrame.class);
	private JPanel contentPane;

	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public ReceiptFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(250, 250, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][]", "[grow][]"));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0 2 1,grow");

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				LOG.info("save button pressed in receipt frame");
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton, "cell 1 1");
	}

	/**
	 * returns the textArea of this class for other class to use
	 * 
	 * @return textArea
	 */
	public JTextArea getReceiptTextArea() {
		return textArea;
	}

}
