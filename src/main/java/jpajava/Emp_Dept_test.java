package jpajava;

import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Emp_Dept_test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("트랜잭션 시작");
        try {
            Department dept = new Department();
            dept.setDeptName("HR");
            em.persist(dept);
            System.out.println("dept 생성");
            Employee emp = new Employee();
            emp.setEmpId("202401");
            emp.setEmpName("홍길동");
            emp.setDepartment(dept);
            emp.setJoinDate("2020-01-01");
            emp.setSalary(100);
            em.persist(emp);

            emp = new Employee();
            emp.setEmpId("202402");
            emp.setEmpName("김연아");
            emp.setDepartment(dept);
            emp.setJoinDate("2024-01-01");
            emp.setSalary(200);
            em.persist(emp);
            System.out.println("커밋 전");
            tx.commit();
            System.out.println("커밋 후");
        } catch (Exception e) {
            tx.rollback();
        }
        System.out.println("트랜잭션 종료");
    }
}
