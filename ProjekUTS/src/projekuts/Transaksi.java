/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekuts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author M IQBAL
 */
public class Transaksi {
    public static int id_transaksi;
    public static float total_belanja;
    public static float uang_dibayar;
    public static float kembalian;
    public static String date;
      static ArrayList<itemTransaksi> itemTransaksi = new ArrayList<>();
    
    public Transaksi(int id_transaksi, float total_belanja, float uang_dibayar, float kembalian, String date){
        this.id_transaksi = id_transaksi;
        this.total_belanja = total_belanja;
        this.uang_dibayar = uang_dibayar;
        this.kembalian = kembalian;
        this.date = date;
    }
    
    public static void addItem(itemTransaksi item){
        itemTransaksi.add(item);
    }

     public static void simpanDatabase() throws SQLException{
        
        try{
            Connection conn = DBConnector.initDBConnection();
            String sql = "INSERT INTO sum_transaksi(id_transaksi, total_belanja, uang_dibayar, kembalian, date) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
           
            stmt.setInt(1,id_transaksi);
            stmt.setFloat(2, total_belanja);
            stmt.setFloat(3, uang_dibayar);
            stmt.setFloat(4,kembalian);
            stmt.setString(5, date);
            stmt.executeUpdate();
            
            sql = "INSERT INTO transaksi(id_transaksi,id_barang,nama_barang,harga_satuan,jumlah_pesanan,total_harga) VALUES(?, ?, ?, ?, ?,?)";
            stmt = conn.prepareStatement(sql);
            for(itemTransaksi item : itemTransaksi){
                stmt.setInt(1,id_transaksi);
                stmt.setString(2,item.id_barang);
                stmt.setString(3, item.nama_barang);
                stmt.setFloat(4, item.harga_satuan);
                stmt.setInt(5,item.jumlah_pesanan);
//                stmt.setFloat(6,item.total_harga);
                stmt.executeUpdate();
            }
           
            
           
            
        } catch(SQLException e){
            System.out.print(e);
        }
    }

    static class itemTransaksi {
        public String id_barang;
        public String nama_barang;
        public float harga_satuan;
        public int jumlah_pesanan;
        public float total_harga;

        public itemTransaksi(String id_barang, String nama_barang, float harga_satuan, int jumlah_pesanan, float total_harga) {
            this.id_barang = id_barang;
            this.nama_barang = nama_barang;
            this.harga_satuan=harga_satuan;
            this.jumlah_pesanan = jumlah_pesanan;
            this.total_harga = total_harga;
        }
    }
}
    
            
    

