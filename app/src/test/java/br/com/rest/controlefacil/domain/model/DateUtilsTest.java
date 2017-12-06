package br.com.rest.controlefacil.domain.model;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Calendar;

import br.com.rest.controlefacil.util.DateUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by PROGRAMAÇÃO on 06/12/2017.
 */

public class DateUtilsTest {

    @Test
    public void formatShouldReturnDateInFormatString() {

        String newDate = DateUtils.format(2017, 11, 6);

        assertEquals("06/11/2017", newDate);
    }

    @Test
    public void formatShouldReturnCalendar(){

        Calendar calendar = DateUtils.getInstance(2017, 11, 6);

        assertEquals("06", calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals("11", calendar.get(Calendar.MONTH));
        assertEquals("2017", calendar.get(Calendar.YEAR));
    }
}
