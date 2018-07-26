/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.domain.Episode;
import entity.domain.Question;
import entity.domain.Stage;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author sawad
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
//        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<Object> findRunning() {
        return getEntityManager()
                .createNamedQuery("CompetitionMatch.findByStatus")
                .setParameter("status", 1)
                .getResultList();
    }

    public List<T> findQuestionByEpisodeStageStatus(Episode episode, Stage stage) {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        return getEntityManager().createNamedQuery("Question.findByEpisodeStageHide")
                .setParameter("episodeId", episode)
                .setParameter("stageId", stage)
                .setParameter("hide", "0")
                .getResultList();
    }
    
    public List<T> findCompetitionMatchSorted() {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        return getEntityManager()
                .createNamedQuery("CompetitionMatch.findAllSorted")
                .getResultList();
    }
    
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
