package com.mychat.reporitory;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.annotations.QueryHints;

import com.mychat.domain.User;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub

		return Optional.ofNullable(em.createQuery("From User u where u.email =:email", User.class)
				.setParameter("email", email).setHint(QueryHints.READ_ONLY, true).getSingleResult());
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return Optional
				.ofNullable(em.createQuery("From User u where u.email =:email AND u.password =:password", User.class)
						.setParameter("email", email).setParameter("password", password)
						.setHint(QueryHints.READ_ONLY, true).getSingleResult());
	}

}
