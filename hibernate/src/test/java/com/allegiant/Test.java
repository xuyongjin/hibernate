package com.allegiant;

import com.allegiant.model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * Created by 许勇进 on 2015-10-27.
 */
public class Test extends BaseTest {

    @org.junit.Test
    public void persistPerson() {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Person person = new Person();
            person.setFirstName("Homer");
            person.setLastName("Simpson");
            session.save(person);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE) {
                session.getTransaction().rollback();
            }
            throw e;
        }finally {
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
