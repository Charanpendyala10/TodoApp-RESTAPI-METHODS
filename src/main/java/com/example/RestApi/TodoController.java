package com.example.RestApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TodoController {
    public static List<Todo> todolist;

    public TodoController(){
        todolist=new ArrayList<>();
        todolist.add(new Todo(1, false, "todo 1", 01));
        todolist.add(new Todo(2, true, "todo 2", 02));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(todolist);
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo>  createTodo(@RequestBody Todo newtodo){
        todolist.add(newtodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newtodo);
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<Todo> findByTodoId(@PathVariable Long todoId){
        for(Todo todo:todolist){
            if(todo.getId()== todoId ){
                return ResponseEntity.status(HttpStatus.OK).body(todo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable Long todoId){
        for(Todo todo:todolist){
            if(todo.getId()==todoId){
                todolist.remove(todo);
                return ResponseEntity.status(HttpStatus.OK).body(todo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/todos/{todoId}")
    public ResponseEntity<Todo> updateTodoField(@PathVariable Long todoId, @RequestBody Map<String, Object> updates){
        Optional<Todo> existingTodo = todolist.stream().filter(todo -> todo.getId() == todoId).findFirst();
        if(!existingTodo.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Todo todo=existingTodo.get();
        updates.forEach((key,value) ->{
            switch (key) {
                case "id":
                    todo.setId((int) value);
                    break;
                case "completed":
                    todo.setCompleted((boolean) value);
                    break;
                case "title":
                    todo.setTitle((String) value);
                    break;
                case "userId":
                    todo.setUserId((int) value);
                    break;
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body(todo);
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<Todo> replaceTodo(@PathVariable int todoId, @RequestBody Todo newTodo){
        Optional<Todo> existingTodo= todolist.stream().filter(todo -> todo.getId()==todoId).findFirst();
        if(!existingTodo.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Todo todo=newTodo;
        todo.setUserId(newTodo.getUserId());
        todo.setTitle(newTodo.getTitle());
        todo.setId(newTodo.getId());
        todo.setCompleted(newTodo.isCompleted());

        return ResponseEntity.ok(todo);
    }

}
