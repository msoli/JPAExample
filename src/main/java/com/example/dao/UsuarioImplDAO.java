package com.example.dao;

import com.example.modelv2.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by SISTEMAS03-PC on 04/01/2017.
 */
@SuppressWarnings("JpaQlInspection")
@Repository
public class UsuarioImplDAO implements UsuarioDAO {


    @Inject
    @Qualifier(value = "sessionFactoryHib")
    private SessionFactory sf;

    /**
     * add un nuevo heroe
     *
     * @param item
     * @return
     */
    @Override
    public void create(List<Usuario> item) {

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        for (int i = 0; i < item.size(); i++) {
            session.persist(item.get(i));

            if (i % 30 == 0) {
                session.flush();
                session.clear();
            }
        }


        tx.commit();
        session.close();


    }


}
