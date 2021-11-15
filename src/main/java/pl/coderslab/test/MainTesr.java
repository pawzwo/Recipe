package pl.coderslab.test;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admins;

public class MainTesr {

    public static void main(String[] args) {

        AdminDao adminDao = new AdminDao();
        Admins admin = new Admins("Jan", "Kowalski", "email", "haslo123", 1, 1);
        adminDao.create(admin);
        System.out.println(admin.checkPassword("haslo123"));
    }


}
