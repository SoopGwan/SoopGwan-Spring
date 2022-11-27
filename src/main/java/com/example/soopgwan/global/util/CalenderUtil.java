package com.example.soopgwan.global.util;

import com.example.soopgwan.domain.habit.application.enums.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

@Component
public class CalenderUtil {

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

    public LocalDate getStartAtAndEndAt(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        if (Date.END_AT.equals(date)) {
            calendar.add(Calendar.DATE, 6);
        }

        String dateFormat = formatter.format(calendar.getTime()).replace(".", "-");

        return LocalDate.parse(dateFormat);
    }
}
