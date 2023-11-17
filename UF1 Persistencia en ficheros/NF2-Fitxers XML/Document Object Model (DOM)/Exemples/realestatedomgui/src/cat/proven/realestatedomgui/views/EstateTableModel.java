
package cat.proven.realestatedomgui.views;

import cat.proven.realestatedomgui.model.Address;
import cat.proven.realestatedomgui.model.Estate;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ProvenSoft
 */
public class EstateTableModel extends AbstractTableModel  {

    private final List<Estate> estates;
    private final String [] colNames = {
        "id", "type", "surface", "price", "street", "number", "floor", "door"
    };

    public EstateTableModel(List<Estate> estates) {
        this.estates = estates;
    }
    
    @Override
    public int getRowCount() {
        return estates.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> [] c = {String.class, String.class, Double.class, Double.class, String.class, Integer.class, Integer.class, Integer.class};
        return c[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex>=1) return true;
        else return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Estate estate = estates.get(rowIndex);
        Address address = estate.getAddress();
        switch (columnIndex) {
            case 0: return estate.getId();
            case 1: return estate.getType();
            case 2: return estate.getSurface();
            case 3: return estate.getPrice();
            case 4: return address.getStreet();
            case 5: return address.getNumber();
            case 6: return address.getFloor();
            case 7: return address.getDoor();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //TODO
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        //TODO
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        //TODO
    }
    
}
