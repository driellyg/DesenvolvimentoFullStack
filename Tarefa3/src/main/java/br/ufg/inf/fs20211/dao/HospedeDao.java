package br.ufg.inf.fs20211.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufg.inf.fs20211.entities.Hospede;
import br.ufg.inf.fs20211.exceptions.HospedeException;

public class HospedeDao {
    EntityManager em;

    public HospedeDao(EntityManager em) {
        this.em = em;
    }

    public List<Hospede> findAll() {
        String sql = "FROM Hospede";
        TypedQuery<Hospede> HospedesQuery = em.createQuery(sql, Hospede.class);
        return HospedesQuery.getResultList();
    }

    public Hospede findById(Integer id) {
        return em.find(Hospede.class, id);
    }

    public Hospede insert(Hospede hospede) throws HospedeException {
        try {
            em.getTransaction().begin();
            em.persist(hospede);
            em.getTransaction().commit();

            return hospede;
        } catch (Exception e) {
            throw new HospedeException("Ação inesperada! Nenhuma linha foi alterada; " + e.getMessage());
        }
    }

    public Hospede update(Hospede hospede) throws HospedeException {
        try {
            this.em.getTransaction().begin();
            this.em.persist(hospede);
            this.em.getTransaction().commit();
            return hospede;
        } catch (Exception e) {
            throw new HospedeException("Ação inesperada! Nenhuma linha foi alterada; " + e.getMessage());
        }
    }

    public Hospede delete(Integer id) throws HospedeException {
        Hospede hospede = findById(id);
        try {
            this.em.getTransaction().begin();
            this.em.remove(hospede);
            this.em.getTransaction().commit();
            return hospede;
        } catch (Exception e) {
            throw new HospedeException("Ação inesperada! Nenhuma linha foi excluída; " + e.getMessage());
        }
    }
}
