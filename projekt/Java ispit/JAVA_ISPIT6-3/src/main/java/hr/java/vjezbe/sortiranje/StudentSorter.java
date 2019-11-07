package hr.java.vjezbe.sortiranje;

import hr.java.vjezbe.entitet.Student;

import java.util.Comparator;

public class StudentSorter implements Comparator<Student> {

    public int compare (Student st1, Student st2) {

        int sortPrezime = st1.getPrezime().compareTo(st2.getPrezime());

        if(sortPrezime == 0) {

            return sortPrezime;
        }

        return st1.getIme().compareTo(st2.getIme());

    }
}
