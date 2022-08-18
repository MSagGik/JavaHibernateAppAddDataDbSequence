package ru.msaggik.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.msaggik.hibernate.model.Person;

public class App {
    public static void main( String[] args ) {
        // подключение файла конфигурации hibernate.properties и класса Person
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        // создание сессии из configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // сессия
        Session session = sessionFactory.getCurrentSession();

        try {
            // начало транзакции
            session.beginTransaction();

            // добавление в БД новых пользователей
            Person person1 = new Person("Tomas", 35);
            Person person2 = new Person("Piter", 18);
            Person person3 = new Person("Steve", 95);
            // сохранение данной информации в БД
            session.save(person1);
            session.save(person2);
            session.save(person3);

            // закрытие транзакции
            session.getTransaction().commit();
        } finally {
            // закрытие сессии
            sessionFactory.close();
        }
    }
}