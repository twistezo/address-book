import javax.swing.table.DefaultTableModel;

/**
 * Class for creating table model which @override DefaultTableModel
 * 
 * @author twistezo
 *
 */

@SuppressWarnings("serial")
class MyTableModel extends DefaultTableModel {
	
	
		/** 
		 *  super(columnNames, 0); is from:
		 *  DefaultTableModel
		 *	public DefaultTableModel(Object[] columnNames, int rowCount)
		 *	{ }
		 */
		public MyTableModel(String[] columnNames) {
            super(columnNames, 0);
        }
		
		/** Adds Object Person to appropriate fields in table */
        public void addRow(Person person) {
            Object[] rowData = new Object[4];
            rowData[0] = person.getName();
            rowData[1] = person.getSurname();
            rowData[2] = person.getMail();
            rowData[3] = person.getTelephone();  
            super.addRow(rowData);
        }
        
        /** Logic of table */
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getRowCount() > 0) {
                Object value = getValueAt(0, columnIndex);
                if (value != null) {
                    return value.getClass(); 
                }
            }
            return super.getColumnClass(columnIndex);
        }
        
        /** Make cells uneditable */
        @Override
        public boolean isCellEditable(int row, int column) {
           return false;
        }
        
}