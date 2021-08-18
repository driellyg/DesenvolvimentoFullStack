package br.ufg.inf.fs20211.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufg.inf.fs20211.entities.Hospedagem;
import br.ufg.inf.fs20211.exceptions.HospedagemException;

public class HospedagemDao {
    EntityManager em;

    public HospedagemDao(EntityManager em) {
        this.em = em;
    }

    public List<Hospedagem> findAll() {
        String sql = "FROM Hospedagem";
        TypedQuery<Hospedagem> HospedagemsQuery = em.createQuery(sql, Hospedagem.class);
        return HospedagemsQuery.getResultList();
    }

    public Hospedagem findById(Integer id) {
        return em.find(Hospedagem.class, id);
    }

    public Hospedagem insert(Hospedagem hospedagem) throws HospedagemException {
        try {
            em.getTransaction().begin();
            em.persist(hospedagem);
            em.getTransaction().commit();

            return hospedagem;
        } catch (Exception e) {
            throw new HospedagemException("Ação inesperada! Nenhuma linha foi alterada; " + e.getMessage());
        }
    }

    public Hospedagem update(Hospedagem hospedagem) throws HospedagemException {
        try {
            this.em.getTransaction().begin();
            this.em.persist(hospedagem);
            this.em.getTransaction().commit();
            return hospedagem;
        } catch (Exception e) {
            throw new HospedagemException("Ação inesperada! Nenhuma linha foi alterada; " + e.getMessage());
        }
    }

    public Hospedagem delete(Integer id) throws HospedagemException {
        Hospedagem hospedagem = findById(id);
        try {
            this.em.getTransaction().begin();
            this.em.remove(hospedagem);
            this.em.getTransaction().commit();
            return hospedagem;
        } catch (Exception e) {
            throw new HospedagemException("Ação inesperada! Nenhuma linha foi excluída; " + e.getMessage());
        }
    }
}
