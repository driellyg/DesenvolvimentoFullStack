package br.ufg.inf.fs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.fs.business.PostsSystems;
import br.ufg.inf.fs.entities.Hospede;
import br.ufg.inf.fs.exceptions.HospedeException;

public class HospedeDao {
    Connection conn;

    public HospedeDao (Connection conn) {
        this.conn = conn;
    }

    public List<Hospede> findAll() throws HospedeException {

        List<Hospede> retorno = new ArrayList<Hospede>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_hospede, nm_hospede, dt_nascimento, cpf FROM db_hotel.tb_hospede";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Hospede hospede = new Hospede(
                        rs.getInt("id_hospede"), 
                        rs.getString("nm_hospede"), 
                        rs.getDate("dt_nascimento"), 
                        rs.getInt("cpf"));
                
                retorno.add(hospede);
            }
        } catch (SQLException e) {
            throw new HospedeException(PostsSystems.getPost("erro02") + e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }

        return retorno;
    }

    public Hospede findById(Integer id) throws HospedeException {

        Hospede retorno = new Hospede();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_hospede, nm_hospede, dt_nascimento, cpf FROM db_hotel.tb_hospede WHERE id_hospede = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                retorno = new Hospede(
                        rs.getInt("id_hospede"), 
                        rs.getString("nm_hospede"),
                        rs.getDate("dt_nascimento"), 
                        rs.getInt("cpf"));
            }
        } catch (SQLException e) {
            throw new HospedeException(PostsSystems.getPost("erro02") + e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }

        return retorno;
    }

    public Hospede insert(Hospede hospede) throws HospedeException {

        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO tb_hospede (nm_hospede, dt_nascimento, cpf) VALUES (?, ?, ?)";
            st = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            st.setString(1, hospede.getNmHospede());
            st.setDate(2, hospede.getDtNascimento());
            st.setInt(3, hospede.getCpf());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    hospede.setIdHospede(rs.getInt(1));
                }
            }else {
                throw new HospedeException(PostsSystems.getPost("erro03"));
            }
        } catch (SQLException e) {
            throw new HospedeException(PostsSystems.getPost("erro02") + e.getMessage());
        } finally {
            DB.closeStatment(st);
        }

        return hospede;
    }

    public Hospede update(Hospede hospede) throws HospedeException {

        PreparedStatement st = null;
        try {
            String sql = "UPDATE tb_hospede SET id_hospede = ?, nm_hospede = ?, dt_nascimento = ?, cpf = ? WHERE id_hospede = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, hospede.getIdHospede());
            st.setString(2, hospede.getNmHospede());
            st.setDate(3, hospede.getDtNascimento());
            st.setInt(4, hospede.getCpf());
            st.setInt(5, hospede.getIdHospede());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new HospedeException(PostsSystems.getPost("erro02") + e.getMessage());
        } finally {
            DB.closeStatment(st);
        }

        return hospede;
    }

    public void delete(Integer id) throws HospedeException {

        PreparedStatement st = null;
        try {
            String sql = "DELETE FROM tb_hospede WHERE id_hospede = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new HospedeException(PostsSystems.getPost("erro02") + e.getMessage());
        } finally {
            DB.closeStatment(st);
        }
    }
}