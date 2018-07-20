/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.domain.Questionsubject;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sawad
 */
@Stateless
public class QuestionsubjectFacade extends AbstractFacade<Questionsubject> {

    @PersistenceContext(unitName = "ProgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionsubjectFacade() {
        super(Questionsubject.class);
    }
    
}
