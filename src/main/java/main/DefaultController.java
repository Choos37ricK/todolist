package main;


import main.model.TodoEvent;
import main.model.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<TodoEvent> todoEventsIterable = todoRepository.findAll();
        ArrayList<TodoEvent> todoEvents = new ArrayList<>();
        todoEventsIterable.forEach(todoEvents::add);
        model.addAttribute("tasks", todoEvents);
        model.addAttribute("tasksCount", todoEvents.size());

        return "index";
    }
}
