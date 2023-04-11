package training360.jpql_lab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select distinct p from Person p left join fetch p.children c where size(c) > 1")
    List<Person> findPeopleWithChildrenMoreThanOne();

    @Query("select distinct p from Person p left join fetch p.children c where size(c) = (select max(size(q.children)) from Person q)")
    Person findPersonWithMostChildren();

    @Query("select c.person from Child c where c.name = :name")
    Person findPersonByChildName(@Param("name") String name);

    @Query("select avg(size(p.children)) from Person p")
    double getAverageNumberOfChild();
}
