package repository;

import testex.JokeException;

import java.util.Date;

public interface IDateFormat {
    String getFormattedDate(Date time, String timezone) throws JokeException;
}
