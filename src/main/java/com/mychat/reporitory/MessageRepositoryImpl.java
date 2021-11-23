package com.mychat.reporitory;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import com.mychat.domain.Message;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MessageRepositoryImpl implements MessageRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void persist(List<Message> messages) {
		em.unwrap(Session.class).setJdbcBatchSize(10);
		messages.forEach(message -> {
			em.persist(message);
		});

	}

}
