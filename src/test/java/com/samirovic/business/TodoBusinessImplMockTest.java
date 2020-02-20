package com.samirovic.business;

import com.samirovic.data.TodoService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockTest {

    @Test
    public void testRetrieveTodosRelatedToSpring() {

        // Creat Mock & stub
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Dance");
        when(todoServiceMock.retrieveTodos("Spring"))
                .thenReturn(todos);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredTodo = todoBusiness.retrieveTodosRelatedToSpring("Spring");

        assertEquals(2, filteredTodo.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Dance");
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        // When
        List<String> filteredTodo = todoBusiness.retrieveTodosRelatedToSpring("Dummy");

        // then
        assertThat(filteredTodo.size(), is(2));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD() {

        // Given
        TodoService todoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Dance");

        given(todoService.retrieveTodos("Dummy"))
                .willReturn(todos);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        // When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        // then ==> verify call method
        verify(todoService).deleteTodo("Learn Dance");
        verify(todoService, times(1)).deleteTodo("Learn Dance");
        verify(todoService, atLeastOnce()).deleteTodo("Learn Dance");
        verify(todoService, atLeast(1)).deleteTodo("Learn Dance");
        verify(todoService, never()).deleteTodo("Learn Spring");
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        // Given
        TodoService todoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Rock in Roll", "Learn Spring boot", "Learn Dance");

        given(todoService.retrieveTodos("Dummy"))
                .willReturn(todos);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        // When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        // then ==> verify call method
        //verify(todoService).deleteTodo("Learn Dance");
        then(todoService).
                should(times(2))
                .deleteTodo(argumentCaptor.capture());

        System.out.println(argumentCaptor.getAllValues());
        assertThat(argumentCaptor.getAllValues().size(), is(2));
    }
}
