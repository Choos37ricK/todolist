package main;

import main.model.TodoEvent;
import main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoEventController {

    @Autowired
    TodoRepository todoRepository;

    @GetMapping("/events/")
    public List<TodoEvent> list() {
        Iterable<TodoEvent> todoEventIterable = todoRepository.findAll();
        ArrayList<TodoEvent> todoEvents = new ArrayList<>();
        todoEventIterable.forEach(todoEvents::add);

        return todoEvents;
    }


    @PostMapping("/events/")
    public Integer add(@ModelAttribute TodoEvent todoEvent, Model model) {
        TodoEvent newEvent = todoRepository.save(todoEvent);
        return newEvent.getId();
    }

    @GetMapping("/events/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<TodoEvent> optionalTodoEvent = todoRepository.findById(id);
        return optionalTodoEvent.map(todoEvent -> new ResponseEntity(todoEvent, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/events/")
    public Integer update(TodoEvent todoEvent) {
        TodoEvent editedEvent = todoRepository.save(todoEvent);
        return  editedEvent.getId();
    }

    @DeleteMapping("/events/{id}")
    public HttpStatus delete(@PathVariable int id) {
        todoRepository.deleteById(id);
        Optional<TodoEvent> optionalTodoEvent = todoRepository.findById(id);
        return optionalTodoEvent.map(todoEvent -> HttpStatus.CONFLICT).orElse(HttpStatus.OK);
    }
}
