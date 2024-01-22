package com.example.players;

public class AAA {


//## input example:# yesterday users file     |
// today users file# id, name, phone##
// 1, Hulk, 0541111111
// 1, Hulk, 0541111111#
// 2, Thor, 0542222222
// 4, Thanos, 0544444444# 4, Thanos, 0544444444         5, Groot, 0545555555# 6, Starlord, 0546666666##
//
//
// ** The list is sorted by ID#
// line = get_next_line(file_name) # act as Iterator (return null if empty)##
// the program should print all the added and deleted users#
// e.g.
// user with id 2 was deleted#
// user with id 5 was added


    public static void main(String[] args) {

        yRow = get_next_line(y_file_name);
        tRow = get_next_line(t_file_name);


        while (yRow != null && tRow != null) {

            if (yRow.getId() == tRow.getId()) {
                //inc both cause we got a match
                yRow = get_next_line(y_file_name);
                tRow = get_next_line(t_file_name);
            } else {
                if (yRow.getId() < tRow.getId()) {
                    System.out.printf("user with id " + yRow.getId() + " was deleted");
                    yRow = get_next_line(y_file_name);
                } else {
                    if (yRow.getId() > tRow.getId()) {
                        System.out.printf("user with id " + tRow.getId() + " was added");
                        tRow = get_next_line(t_file_name);
                    }
                }
            }
        }

        if (yRow == null) {
            //run on all t and mark them as new items
        } else {
            // run on all yesterday and mark them as new items
        }


    }
}
