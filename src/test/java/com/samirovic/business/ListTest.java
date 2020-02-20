package com.samirovic.business;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void testMockListSizeMethod() {

        List list = mock(List.class);
        when(list.size()).thenReturn(2);

        assertEquals(2, list.size());
        assertEquals(2, list.size());
    }

    @Test
    public void testMockListSize_ReturnMultipleValues() {
        List list = mock(List.class);
        when(list.size()).thenReturn(2).thenReturn(3);

        assertEquals(2, list.size());
        assertEquals(3, list.size());
    }

    @Test
    public void testMockListGet() {
        List list = mock(List.class);
        // arguments Matchers
        when(list.get(anyInt())).thenReturn("Samirovic");

        assertEquals("Samirovic", list.get(0));
        assertEquals("Samirovic", list.get(6));
    }

    @Test
    public void testMockListGet_usingBDD() {
        // Given
        List<String> list = mock(List.class);
        given(list.get(anyInt())).willReturn("Samirovic");

        // when
        String name = list.get(0);
        // then
        assertThat(name, is("Samirovic"));
    }

    @Test(expected = RuntimeException.class)
    public void testMockListGet_ThrowAnException() {
        List list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new RuntimeException("Something"));

        list.get(0);
    }

}
