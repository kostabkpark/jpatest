package jpajava;

import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("트랜잭션 시작");
        try {

            Employee emp = new Employee("202401","홍길동",null,"2024-01-01", 10L);
            em.persist(emp);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("트랜잭션 종료");
    }
}
