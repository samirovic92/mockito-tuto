package com.samirovic.business;

import com.samirovic.data.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockitoInjectTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusiness;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    public void testRetrieveTodosRelatedToSpring() {

        // Creat Mock & stub
        List<String> todos = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Dance");
        when(todoServiceMock.retrieveTodos("Spring"))
                .thenReturn(todos);

        List<String> filteredTodo = todoBusiness.retrieveTodosRelatedToSpring("Spring");

        assertEquals(2, filteredTodo.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        // Given
        List<String> todos = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Dance");
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(todos);

        // When
        List<String> filteredTodo = todoBusiness.retrieveTodosRelatedToSpring("Dummy");

        // then
        assertThat(filteredTodo.size(), is(2));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD() {

        // Given
        List<String> todos = Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Dance");

        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(todos);

        // When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        // then ==> verify call method
        verify(todoServiceMock).deleteTodo("Learn Dance");
        verify(todoServiceMock, times(1)).deleteTodo("Learn Dance");
        verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn Dance");
        verify(todoServiceMock, atLeast(1)).deleteTodo("Learn Dance");
        verify(todoServiceMock, never()).deleteTodo("Learn Spring");
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {

        // Given
        List<String> todos = Arrays.asList("Learn Rock in Roll", "Learn Spring boot", "Learn Dance");
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(todos);

        // When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        // then ==> verify call method
        //verify(todoService).deleteTodo("Learn Dance");
        then(todoServiceMock).
                should(times(2))
                .deleteTodo(argumentCaptor.capture());

        System.out.println(argumentCaptor.getAllValues());
        assertThat(argumentCaptor.getAllValues().size(), is(2));
    }
}
