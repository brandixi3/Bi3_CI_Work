package com.brandixi3.cidemo.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brandixi3.cidemo.dao.InventryDao;
import com.brandixi3.cidemo.model.Item;

@Repository("inventryDao")
public class InventryDaoImpl implements InventryDao{

	 /** The em. */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public final Item createItem(final Item t) {
        entityManager.persist(t);
        entityManager.flush();
        return t;
    }
}
