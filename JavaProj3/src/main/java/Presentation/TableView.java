package Presentation;

import javax.swing.*;

/**
 * This class is used to create a separate window for displaying a JTable
 */

public class TableView extends JFrame{
    private JPanel tableView;

    public JPanel getTableView() {
        return tableView;
    }

    public TableView(){
        setContentPane(tableView);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(650,600);

    }
}
