package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.ufg.inf.fs.business.PostsSystems;
import br.ufg.inf.fs.ctrl.HospedagemCtrl;
import br.ufg.inf.fs.ctrl.HospedeCtrl;
import br.ufg.inf.fs.dao.DB;
import br.ufg.inf.fs.entities.Hospedagem;
import br.ufg.inf.fs.entities.Hospede;
import br.ufg.inf.fs.ctrl.HotelCtrl;
import br.ufg.inf.fs.ctrl.QuartoCtrl;
import br.ufg.inf.fs.entities.Hotel;
import br.ufg.inf.fs.entities.Quarto;
import br.ufg.inf.fs.enums.CategoriaQuarto;

public class App {
    
    public static void main(String[] args) {
        testeCrudHospede();
        testeCrudHospedagem();
    }

    public static void testeSystem() {

        System.out.println("Hello World");
        System.err.println("Hello World com erro");

    }

    public static void testeDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String ann = "28/02/1999";
        try {
            java.util.Date annSDF = sdf.parse(ann);
            long ms = annSDF.getTime();
            java.sql.Date annSQL = new java.sql.Date(ms);
            String annF = sdf.format(annSQL);
            System.out.println(annSQL);
            System.out.println(annF);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void testePostSystem() {
        System.out.println(PostsSystems.getPost("password"));
    }

    public static void testeConexao() {

        Statement stmt = null;
        ResultSet rs = null;
        try {
            String url = PostsSystems.getPost("dburl");
            String password = PostsSystems.getPost("password");
            String user = PostsSystems.getPost("user");
            Connection con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT id_hotel, nm_hotel FROM db_hotel.tb_hotel");
            System.out.println(PostsSystems.getPost("ok01"));
            while (rs.next()) {
                System.out.println(rs.getInt("id_hotel") + " " + rs.getString("nm_hotel"));
            }
        } catch (SQLException e) {
            System.err.println(PostsSystems.getPost("erro01"));
            System.out.println(e.getMessage());
        } finally {
            DB.closeStatment(stmt);
            DB.closeResultSet(rs);
        }
    }

    public static void testeCrudHotel() {
        
        HotelCtrl ctrl = new HotelCtrl();

        System.out.println(PostsSystems.getPost("hoteis"));
        for (Hotel h : ctrl.findAll()) {
            System.out.println(h);
        }

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        Integer id = 2;
        System.out.println(PostsSystems.getPost("hotel_id") + id);
        Hotel hotel = ctrl.findById(id);
        System.out.println(hotel);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        System.out.println(PostsSystems.getPost("inserir_hotel"));
        Hotel h1 = new Hotel("Ibis Hotel", "R. 21, No 55 - St. Oeste, Goiânia - GO, 74120-120", 5);
        //h1 = ctrl.insert(h1);
        System.out.println(h1);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        Hotel h2 = ctrl.findById(id);
        System.out.println(PostsSystems.getPost("update_hotel") + id);
        System.out.println(h2);
        h2.setNmHotel(h2.getNmHotel() + " ALTERADO");
        h2.setQtdEstrelas(1);
        //h2 = ctrl.update(h2);
        System.out.println("#ID alterado: " + h2);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        System.out.println(PostsSystems.getPost("hoteis"));
        for (Hotel h : ctrl.findAll()) {
            System.out.println(h);
        }

        Integer id1 = 10;
        System.out.println(PostsSystems.getPost("delete_hotel") + id1);
        //ctrl.delete(id1);

        System.out.println(PostsSystems.getPost("hoteis") + " ATUALIZADO");
        for (Hotel h : ctrl.findAll()) {
            System.out.println(h);
        }

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

    }

    
    public static void testeCrudQuarto() {

        HotelCtrl hotelCtrl = new HotelCtrl();
        QuartoCtrl ctrl = new QuartoCtrl();

        System.out.println(PostsSystems.getPost("quartos"));
        for (Quarto q : ctrl.findAll()) {
            System.out.println(q);
        }

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        Integer id = 1;
        System.out.println(PostsSystems.getPost("quarto_id") + id);
        Quarto quarto = ctrl.findById(id);
        System.out.println(quarto);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        System.out.println(PostsSystems.getPost("inserir_quarto"));
        Quarto q1 = new Quarto(hotelCtrl.findById(1), CategoriaQuarto.DORMITORIO, 5, 220, 440.0);
        q1 = ctrl.insert(q1);
        System.out.println(q1);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        Quarto q2 = ctrl.findById(id);
        System.out.println(PostsSystems.getPost("update_quarto") + id);
        System.out.println(q2);
        q2.setPrDiaria(q2.getPrDiaria() + 20);
        q2 = ctrl.update(q2);
        q2 = ctrl.update(q2);
        System.out.println("#ID alterado: " + q2);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        System.out.println(PostsSystems.getPost("quartos"));
        for (Quarto q : ctrl.findAll()) {
            System.out.println(q);
        }

        Integer idDel = 5;
        System.out.println(PostsSystems.getPost("delete_hotel") + idDel);
        ctrl.delete(idDel);
        System.out.println(PostsSystems.getPost("quartos") + "ATUALIZADO");
        for (Quarto q : ctrl.findAll()) {
            System.out.println(q);
        }
    }

    public static void testeCrudHospede() {

        HospedeCtrl ctrl = new HospedeCtrl();

        System.out.println(PostsSystems.getPost("hospedes"));
        for (Hospede h : ctrl.findAll()) {
            System.out.println(h);
        }

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        Integer id = 3;
        System.out.println(PostsSystems.getPost("hotel_id") + id);
        Hospede hospede = ctrl.findById(id);
        System.out.println(hospede);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String dtNascimento = "06/11/1998";
        java.sql.Date dtNascimentoSQL = null;
        try {
            java.util.Date dtNascimentoSDF = sdf.parse(dtNascimento);
            long ms = dtNascimentoSDF.getTime();
            dtNascimentoSQL = new java.sql.Date(ms);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(PostsSystems.getPost("inserir_hospede"));
        Hospede h1 = new Hospede("Adrielly", dtNascimentoSQL, 444455);
        h1 = ctrl.insert(h1);
        System.out.println(h1);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        Hospede h2 = ctrl.findById(id);
        System.out.println(PostsSystems.getPost("update_hospede") + id);
        System.out.println(h2);
        h2.setNmHospede(h2.getNmHospede() + " Goncalves");
        h2 = ctrl.update(h2);
        System.out.println("#ID alterado: " + h2);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        System.out.println(PostsSystems.getPost("hospedes"));
        for (Hospede h : ctrl.findAll()) {
            System.out.println(h);
        }

        Integer idDel = 7;
        System.out.println(PostsSystems.getPost("delete_hospede") + idDel);
        ctrl.delete(idDel);
        System.out.println(PostsSystems.getPost("hospedes") + "ATUALIZADO");
        for (Hospede h : ctrl.findAll()) {
            System.out.println(h);
        }
    }

    public static void testeCrudHospedagem() {

        QuartoCtrl quartoCtrl = new QuartoCtrl();
        HospedeCtrl hospedeCtrl = new HospedeCtrl();
        HospedagemCtrl ctrl = new HospedagemCtrl();

        System.out.println(PostsSystems.getPost("hospedagens"));
        for (Hospedagem h : ctrl.findAll()) {
            System.out.println(h);
        }

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        Integer id = 1;
        System.out.println(PostsSystems.getPost("hospedagem_id") + id);
        Hospedagem hospedagem = ctrl.findById(id);
        System.out.println(hospedagem);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String dtCheckin = "17/08/2021";
        String dtCheckout = "01/09/2021";
        java.sql.Date dtCheckinSQL = null;
        java.sql.Date dtCheckoutSQL = null;
        try {
            java.util.Date dtCheckinSDF = sdf.parse(dtCheckin);
            java.util.Date dtCheckoutSDF = sdf.parse(dtCheckout);
            long msDtCheckin = dtCheckinSDF.getTime();
            long msDtCheckout = dtCheckoutSDF.getTime();
            dtCheckinSQL = new java.sql.Date(msDtCheckin);
            dtCheckoutSQL = new java.sql.Date(msDtCheckout);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(PostsSystems.getPost("inserir_hospedagem"));
        Hospedagem h1 = new Hospedagem(quartoCtrl.findById(4), hospedeCtrl.findById(3), dtCheckinSQL, dtCheckoutSQL);
        h1 = ctrl.insert(h1);
        System.out.println(h1);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        String dtCheckoutNew = "31/08/2021";
        java.sql.Date dtCheckoutSQLNew = null;
        try {
            java.util.Date dtCheckoutSDFNew = sdf.parse(dtCheckoutNew);
            long msDtCheckoutNew = dtCheckoutSDFNew.getTime();
            dtCheckoutSQLNew = new java.sql.Date(msDtCheckoutNew);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Hospedagem h2 = ctrl.findById(id);
        System.out.println(PostsSystems.getPost("update_hospedagem") + id);
        System.out.println(h2);
        h2.setDtCheckout(dtCheckoutSQLNew);
        h2 = ctrl.update(h2);
        System.out.println("#ID alterado: " + h2);

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();

        System.out.println(PostsSystems.getPost("hospedagens"));
        for (Hospedagem h : ctrl.findAll()) {
            System.out.println(h);
        }

        Integer idDel = 4;
        System.out.println(PostsSystems.getPost("delete_hospedagem") + idDel);
        ctrl.delete(idDel);
        System.out.println(PostsSystems.getPost("hospedagens") + "ATUALIZADO");
        for (Hospedagem h : ctrl.findAll()) {
            System.out.println(h);
        }
    }
}