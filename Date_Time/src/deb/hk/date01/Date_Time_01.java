package deb.hk.date01;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Date_Time_01 {
    public static void main(String[] args) {
        String date = "2022-04-02T20:43:36";
        LocalDateTime localDateTime = LocalDateTime.parse(date);

        System.out.println("original date as string: "+date);
        System.out.println("generated LocalDateTime: "+localDateTime);

        System.out.println("=========================================");

        String date02 = "2022-04-02T20:43:36";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, format);

        System.out.println("origial date as string: "+ date);
        System.out.println("generated LocalDateTime: "+dateTime);

        System.out.println("========================================");

        DateTimeFormatter aFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, Month.APRIL, 2, 10, 12);
        String formatterdString = localDateTime1.format(aFormatter);

        System.out.println("original LocaldateTime object: "+localDateTime1);
        System.out.println("generated String: "+formatterdString);


        System.out.println("==================================");

        String date03 = "2022-04-02 10:22";
        DateTimeFormatter format02 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime2 = LocalDateTime.parse(date03, format02);

        System.out.println("original date as string: "+date03);
        System.out.println("generated localdatetime: "+localDateTime2);
    }
}
