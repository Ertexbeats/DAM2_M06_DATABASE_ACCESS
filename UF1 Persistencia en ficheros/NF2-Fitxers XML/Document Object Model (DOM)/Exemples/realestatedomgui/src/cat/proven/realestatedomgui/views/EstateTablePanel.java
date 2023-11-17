
package cat.proven.realestatedomgui.views;

import cat.proven.realestatedomgui.model.Estate;
import java.util.List;

/**
 *
 * @author ProvenSoft
 */
public class EstateTablePanel extends javax.swing.JPanel {

    /**
     * Creates new form EstateTablePanel
     * @param estates
     */
    public EstateTablePanel(List<Estate> estates) {
        this.estates = estates;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        estateTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        estateTable.setModel(new EstateTableModel(estates));
        jScrollPane1.setViewportView(estateTable);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable estateTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private List<Estate> estates;

    public List<Estate> getEstates() {
        return estates;
    }

    public void setEstates(List<Estate> estates) {
        this.estates = estates;
    }


    
}
