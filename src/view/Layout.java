package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {
    // Pencere özelliklerini buradan giriyoruz
    public void guiInitilaze(int width,int height){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Amasia Hotel"); // Pencere Başlık
        this.setSize(width,height); // En x boy ölçüleri
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize())); // Penereyi ekranın ortasında açmamızı sağlar
        this.setVisible(true); // Pencereyi görebilmemizi sağlayan satır

    }
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows){
        model.setColumnIdentifiers(columns);
        // Modeli tabloya bağla
        table.setModel(model);
        // Sütunların yeniden sıralanmasını engelle
        table.getTableHeader().setReorderingAllowed(false);
        // İlk sütunun maksimum genişliğini ayarla
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        // Tabloyu etkisiz hale getir
        table.setEnabled(false);


        // Tabloyu temizleme
        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);


        // Eğer satırlar null ise boş bir liste oluştur
        if(rows == null){
            rows = new ArrayList<>();
        }

        // Satırları modele ekle
        for(Object[] row : rows){
            model.addRow(row);
        }
    }


    // Belirli bir satırdaki belirli bir indeksteki değeri döndür
    public int getTableSelectedRow(JTable table,int index){
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());
    }


    // Tablodaki satır seçimini dinleme
    public void tableRowSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row,selected_row);
            }
        });
    }
}
