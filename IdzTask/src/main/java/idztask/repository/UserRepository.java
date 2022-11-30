package idztask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import idztask.entity.Employee;

@Repository
public interface UserRepository extends JpaRepository<Employee, Integer>{

}
