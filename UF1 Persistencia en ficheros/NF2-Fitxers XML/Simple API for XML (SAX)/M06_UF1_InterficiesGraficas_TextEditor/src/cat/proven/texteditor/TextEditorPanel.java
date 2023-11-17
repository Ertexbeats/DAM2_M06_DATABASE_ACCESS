package cat.proven.texteditor;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Text editor panel Uses: /images/Open16.gif /images/Save16.gif
 *
 * @author Jose
 */
public class TextEditorPanel extends JPanel
        implements ActionListener {

    private static final String NEWLINE = "\n";
    private final ActionListener actionListener;

    private JButton btnOpen;
    private JButton btnSave;
    private JTextArea taContent;
    private JFileChooser fc;

    public TextEditorPanel() {
        actionListener = this;
        initComponents();
    }

    private void initComponents() {
        setLayout( new BorderLayout() );
        taContent = new JTextArea( 20, 50 );
        taContent.setMargin( new Insets( 5, 5, 5, 5 ) );
        taContent.setEditable( true );
        //scrool pane to put filechooser in
        JScrollPane logScrollPane = new JScrollPane( taContent );
        //Create a file chooser
        String workingDirectory = ".";
        fc = new JFileChooser( workingDirectory );
        //Uncomment to apply a filter to file extension
        //FileNameExtensionFilter filter = new FileNameExtensionFilter(
        //"JPG & GIF Images", "jpg", "gif");
        //fc.setFileFilter(filter);
        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        btnOpen = new JButton( "Open a File...",
                createImageIcon( "images/Open16.gif" ) );
        btnOpen.setActionCommand( "open" );
        btnOpen.addActionListener( actionListener );

        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        btnSave = new JButton( "Save a File...",
                createImageIcon( "images/Save16.gif" ) );
        btnSave.setActionCommand( "save" );
        btnSave.addActionListener( actionListener );

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add( btnOpen );
        buttonPanel.add( btnSave );

        //Add the buttons and the taContent to this panel.
        add( buttonPanel, BorderLayout.PAGE_START );
        add( logScrollPane, BorderLayout.CENTER );
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        String action = e.getActionCommand();
        switch ( action ) {
            case "open" -> {
                handleOpen();
            }
            case "save" -> {
                handleSave();
            }
            default ->
                throw new AssertionError();
        }
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     *
     * @param path the path to ImageIcon
     * @return an ImageIcon, or null if the path was invalid.
     */
    protected ImageIcon createImageIcon( String path ) {
        java.net.URL imgURL = TextEditorPanel.class.getResource( path );
        if ( imgURL != null ) {
            return new ImageIcon( imgURL );
        } else {
            JOptionPane.showMessageDialog( this, "Couldn't find file: " + path );
            //System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * handler for open file button
     */
    public void handleOpen() {
        int returnVal = fc.showOpenDialog( TextEditorPanel.this );
        if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            //taContent.append("Opening: " + file.getName() + "." + NEWLINE);
            try {
                String content = TextFile.readFromFile( file );
                taContent.setText( content );
            } catch ( FileNotFoundException ex ) {
                JOptionPane.showMessageDialog( this, "File " + file.getName() + " not found" );
            } catch ( IOException ex ) {
                JOptionPane.showMessageDialog( this, "Error reading from file " + file.getName() );
            }
        } else {
            taContent.append( "Open command cancelled by user." + NEWLINE );
        }
        taContent.setCaretPosition( taContent.getDocument().getLength() );
    }

    /**
     * handler for save button
     */
    public void handleSave() {
        int returnVal = fc.showSaveDialog( TextEditorPanel.this );
        if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            File file = fc.getSelectedFile();
            //This is where a real application would save the file.
            //taContent.append("Saving: " + file.getName() + "." + NEWLINE);
            String content = taContent.getText();
            try {
                TextFile.writeToFile( file, content );
                JOptionPane.showMessageDialog( this, "Content saved to file " + file.getName() );
            } catch ( IOException ex ) {
                JOptionPane.showMessageDialog( this, "Error writing to file " + file.getName() );
            }

        } else {
            JOptionPane.showMessageDialog( this, "Save command cancelled by user" );
            //taContent.append("Save command cancelled by user." + NEWLINE);
        }
        taContent.setCaretPosition( taContent.getDocument().getLength() );
    }

}
