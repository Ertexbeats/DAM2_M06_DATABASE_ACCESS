package cat.proven.texteditor;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TextEditorFrame extends JFrame implements ActionListener {

    private final String frameTitle;
    private final ActionListener actionListener;
    private final String aboutMessage, exitMessage;
    private TextEditorPanel editorPanel;

    public static void main( String[] args ) {
        SwingUtilities.invokeLater( () -> {
            new TextEditorFrame().setVisible( true );
        } );
    }

    public TextEditorFrame() {
        actionListener = this;
        frameTitle = "Text editor";
        aboutMessage = "<html>"
                + "<p>TextEditor</p>"
                + "<p>(c) ProvenSoft 2023</p>"
                + "</html>";
        exitMessage = "Are u sure?";
        initComponents();
    }

    private void initComponents() {
        UIManager.put( "swing.boldMetal", Boolean.FALSE );
        setTitle( frameTitle );
        setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent we ) {
                handleExit();
            }
        } );
        createMenuBar();
        Container contentPane = getContentPane();
        editorPanel = new TextEditorPanel();
        contentPane.add( new TextEditorPanel() );
        pack();
    }

    private void createMenuBar() {
        //
        JMenuBar mnuBar = new JMenuBar();
        //File Menu
        JMenu mnu = new JMenu( "File" );
        mnuBar.add( mnu );
        //ITEMS File
        JMenuItem mItem = new JMenuItem( "Open" );
        mItem.setActionCommand( "open" );
        mItem.addActionListener( actionListener );
        mnu.add( mItem );

        mItem = new JMenuItem( "Select" );
        mItem.setActionCommand( "select" );
        mItem.addActionListener( actionListener );
        mnu.add( mItem );

        mItem = new JMenuItem( "Exit" );
        mItem.setActionCommand( "exit" );
        mItem.addActionListener( actionListener );
        mnu.add( mItem );

        //Help Menu
        mnu = new JMenu( "Help" );
        mnuBar.add( mnu );
        //ITEMS HELP
        mItem = new JMenuItem( "About" );
        mItem.setActionCommand( "about" );
        mItem.addActionListener( actionListener );
        mnu.add( mItem );

        //
        setJMenuBar( mnuBar );
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        String action = e.getActionCommand();
        switch ( action ) {
            case "open" ->
                handleOpen();

            case "select" ->
                handleSave();

            case "exit" ->
                handleExit();

            case "about" ->
                handleAbout();

            default ->
                throw new AssertionError();
        }
    }

    private void handleAbout() {
        JOptionPane.showMessageDialog( this, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE );
    }

    private void handleExit() {
        //TODO ask for confirmation
        int answer = JOptionPane.showConfirmDialog( this, exitMessage, "exit", JOptionPane.OK_CANCEL_OPTION );
        if ( answer == JOptionPane.OK_OPTION ) {
            System.exit( 0 );
        }
    }

    private void handleOpen() {
        editorPanel.handleOpen();
    }

    private void handleSave() {
        editorPanel.handleSave();
    }
}
