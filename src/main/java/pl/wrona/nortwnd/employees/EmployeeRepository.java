package pl.wrona.nortwnd.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long>, PagingAndSortingRepository<Employees, Long> {

//    List<Employees> findEmployeesByFirstNameIsLike(String like);

    @Query(value = "SELECT * FROM Employees e WHERE e.FirstName = ?1 ORDER BY firstName", nativeQuery = true)
    List<Employees> findEmployeesByFirstNameIsLikeOrderByFirstNameAsc(String firstName);

    List<Employees> findEmployeesByFirstNameIsLike(String name);
}
