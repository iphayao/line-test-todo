package com.iphayao.linetest.todo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceTest {
    @Autowired
    TodoRepository repository;

    @Autowired
    private TodoService todoService;

    @Test
    public void testCreateTodoByDateTypeWithDateTime() {
        String userId = "123456789";
        String expectAction = "Buy milk";
        String expectDate = "2/5/18";
        String expectTime = "13:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/[yyyy][yy] HH:mm");
        LocalDateTime expectDateTime = LocalDateTime.parse(String.format("%s %s", expectDate, expectTime), formatter);

        String message = formatTodoMessage("date/month/year", expectAction, expectDate, expectTime);

        Todo expect = getTodoMock(TodoType.ByDate, userId, expectAction, expectDateTime);

        Todo result = todoService.createTodo(userId, message).orElse(null);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(TodoType.ByDate, result.getType());
        assertEquals(expect.getAction(), result.getAction());
        assertEquals(expect.getDateTime(), result.getDateTime());
        assertEquals(expect.getDateTime(), result.getDateTime());

    }

    @Test
    public void testCreateTodoByDateTypeWithDateNoTime() {
        String userId = "123456789";
        String expectAction = "Buy milk";
        String expectDate = "2/5/18";
        String expectTime = "00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/[yyyy][yy] HH:mm");
        LocalDateTime expectDateTime = LocalDateTime.parse(String.format("%s %s", expectDate, expectTime), formatter);

        String message = formatTodoMessage("date/month/year", expectAction, expectDate);

        Todo expect = getTodoMock(TodoType.ByDate, userId, expectAction, expectDateTime);

        Todo result = todoService.createTodo(userId, message).orElse(null);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(TodoType.ByDate, result.getType());
        assertEquals(expect.getAction(), result.getAction());
        assertEquals(expect.getDateTime(), result.getDateTime());
        assertEquals(expect.getDateTime(), result.getDateTime());

    }

    @Test
    public void testCreateTodoTodayTypeWithDateTime() {
        String userId = "123456789";
        String expectAction = "Finish writing shopping list";
        String expectDate = "today";
        String expectTime = "15:30";
        LocalDateTime expectDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(expectTime));

        String message = formatTodoMessage(expectDate, expectAction, expectDate, expectTime);

        Todo expect = getTodoMock(TodoType.Today, userId, expectAction, expectDateTime);

        Todo result = todoService.extractTodoMessage(userId, message);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(TodoType.Today, result.getType());
        assertEquals(expect.getAction(), result.getAction());
        assertEquals(expect.getDateTime(), result.getDateTime());

    }

    @Test
    public void testCreateTodoTodayTypeWithDateNoTime() {
        String userId = "123456789";
        String expectAction = "Finish writing shopping list";
        String expectDate = "today";
        String expectTime = "00:00";
        LocalDateTime expectDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(expectTime));

        String message = formatTodoMessage(expectDate, expectAction, expectDate, expectTime);

        Todo expect = getTodoMock(TodoType.Today, userId, expectAction, expectDateTime);

        Todo result = todoService.extractTodoMessage(userId, message);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(TodoType.Today, result.getType());
        assertEquals(expect.getAction(), result.getAction());
        assertEquals(expect.getDateTime(), result.getDateTime());

    }

    @Test
    public void testCreateTodoTomorrowTypeWithDateTime() {
        String userId = "123456789";
        String expectAction = "Finish writing shopping list";
        String expectDate = "tomorrow";
        String expectTime = "15:30";
        LocalDateTime expectDateTime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.parse(expectTime));

        String message = formatTodoMessage(expectDate, expectAction, expectDate, expectTime );

        Todo expect = getTodoMock(TodoType.Tomorrow, userId, expectAction, expectDateTime);

        Todo result = todoService.extractTodoMessage(userId, message);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(TodoType.Tomorrow, result.getType());
        assertEquals(expect.getAction(), result.getAction());
        assertEquals(expect.getDateTime(), result.getDateTime());

    }

    @Test
    public void testCreateTodoTomorrowTypeWithDateNoTime() {
        String userId = "123456789";
        String expectAction = "Finish writing shopping list";
        String expectDate = "tomorrow";
        String expectTime = "00:00";
        LocalDateTime expectDateTime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.parse(expectTime));

        String message = formatTodoMessage(expectDate, expectAction, expectDate, expectTime );

        Todo expect = getTodoMock(TodoType.Tomorrow, userId, expectAction, expectDateTime);

        Todo result = todoService.extractTodoMessage(userId, message);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(TodoType.Tomorrow, result.getType());
        assertEquals(expect.getAction(), result.getAction());
        assertEquals(expect.getDateTime(), result.getDateTime());

    }

    private Todo getTodoMock(TodoType type, String userId, String expectAction, LocalDateTime expectDateTime) {
        return Todo.builder()
                .type(type)
                .userId(userId)
                .dateTime(expectDateTime)
                .action(expectAction)
                .build();
    }

    private String formatTodoMessage(String type, String action, String date, String time) {
        return String.format("task : %s : time e.g. %s : %s : %s", type, action, date, time);
    }

    private String formatTodoMessage(String type, String action, String date) {
        return String.format("task : %s : time e.g. %s : %s : ", type, action, date);
    }


}