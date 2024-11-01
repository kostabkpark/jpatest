package jpajava;

import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Emp_Dept_loop_test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("트랜잭션 시작");
        try {

            Employee employee = em.find(Employee.class, "202403");
            System.out.println("emp name : " + employee.getEmpName());
            System.out.println("dept name : " + employee.getDepartment().getDeptName());


            tx.commit();
            System.out.println("커밋 후");
        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("트랜잭션 종료");
    }
}
