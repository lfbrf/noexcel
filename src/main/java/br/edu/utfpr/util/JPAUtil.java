package br.edu.utfpr.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class JPAUtil {

    private static String PERSISTENCE_UNIT = "no_excel_jpa";

    private static ThreadLocal<EntityManager> threadLocal
            = new ThreadLocal<EntityManager>();

    private static EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager;

    private JPAUtil() {
    }

    public JPAUtil(String persistenceUnit) {
        PERSISTENCE_UNIT = persistenceUnit;
        getEntityManager();
    }

    /**
     *
     * Gera o schema do banco de dados
     *
     */
    public static void generateSchema() {
        Persistence.generateSchema(PERSISTENCE_UNIT, null);
    }

    //uso do padrão double-checked locking
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            synchronized (JPAUtil.class) {
                if (entityManagerFactory == null) {
                    entityManagerFactory
                            = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
                }
            }
        }

        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {

        entityManagerFactory = getEntityManagerFactory();
        EntityManager entityManager = threadLocal.get();

        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
            threadLocal.set(entityManager);
        }

        return entityManager;
    }

    public static void beginTransaction() {
        EntityManager entityManager = threadLocal.get();

        if (entityManager == null) {
            entityManager = getEntityManager();
        }

        entityManager.getTransaction().begin();
    }

    public static void commit() {
        EntityManager entityManager = threadLocal.get();

        if (entityManager == null) {
            entityManager = getEntityManager();
        }

        EntityTransaction transaction = entityManager.getTransaction();

        if (transaction != null && transaction.isActive()) {
            entityManager.getTransaction().commit();
        }
    }

    public static void rollBack() {
        EntityManager entityManager = threadLocal.get();

        if (entityManager == null) {
            entityManager = getEntityManager();
        }

        EntityTransaction transaction = entityManager.getTransaction();

        if (transaction != null && transaction.isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();

        if (em != null) {
            threadLocal.set(null);
            threadLocal.remove();
            em.close();
        }
    }

    public static void closeEntityManagerFactory() {
        closeEntityManager();
        entityManagerFactory.close();
    }
}
