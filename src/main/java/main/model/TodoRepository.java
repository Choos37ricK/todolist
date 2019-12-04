package main.model;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoEvent, Integer> {

}
