/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.domain.Questionpoint;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sawad
 */
@Stateless
public class QuestionpointFacade extends AbstractFacade<Questionpoint> {

    @PersistenceContext(unitName = "ProgramPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionpointFacade() {
        super(Questionpoint.class);
    }
    
}
