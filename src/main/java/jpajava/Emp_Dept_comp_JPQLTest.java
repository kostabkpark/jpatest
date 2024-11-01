package jpajava;

import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Emp_Dept_comp_JPQLTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("트랜잭션 시작");
        try {
            // JPQL ==> N + 1
//            String jpql = "select e from Employee e " ; // select * from employee ;
//            List<Employee> emps = em.createQuery(jpql, Employee.class).getResultList();
            // jpql / fetch join (연관관계가 있는 객체까지 한번에 가져오는 쿼리)
            String fetchjpql = "select e from Employee e join fetch e.department";
            List<Employee> emps = em.createQuery(fetchjpql, Employee.class).getResultList();
            for (Employee emp : emps) {
                System.out.println(emp.getEmpName());
                System.out.println(emp.getDepartment().getDeptName());
            }

            System.out.println("커밋 전");
            tx.commit();
            System.out.println("커밋 후");
        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("트랜잭션 종료");
    }
}
