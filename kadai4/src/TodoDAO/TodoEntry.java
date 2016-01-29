package TodoDAO;
import java.io.*;

public class TodoEntry implements Serializable {

    private int todoId;
    private String todoText;

    public TodoEntry() {
    }

    public int getTodoId() {
	return this.todoId;
    }
    public void setTodoId(int todoId) {
	this.todoId = todoId;
    }

    public String getTodoText() {
	return this.todoText;
    }
    public void setTodoText(String todoText) {
	this.todoText = todoText;
    }
}
