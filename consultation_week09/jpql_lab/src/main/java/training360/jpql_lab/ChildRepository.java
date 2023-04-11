package training360.jpql_lab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {

    @Query("select c from Child c where c.yearOfBirth > :year")
    List<Child> findChildrenByYearOfBirthAfter(@Param("year") int year);

    @Query("select c from Child c where c.person.name = :name and c.yearOfBirth = :year")
    Child findChildWithParentNameAndYearOfBirthGiven(@Param("name") String name, @Param("year") int year);

    @Query("select c from Child c where size(c.person.children) = (select max(size(p.children)) from Person p)")
    List<Child> findChildrenWithMostSiblings();
}
