package com.mychat.reporitory;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.annotations.QueryHints;

import com.mychat.domain.ChatRoom;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<ChatRoom> findByName(String name) {
		// TODO Auto-generated method stub
		return em.createQuery("From ChatRoom room join fetch room.users users where room.hashName =:name ", ChatRoom.class)
				.setParameter("name", name).setHint(QueryHints.READ_ONLY, true).getResultStream().findFirst();
	}

	@Override
	public void persist(ChatRoom room) {
		em.persist(room);

	}

}
