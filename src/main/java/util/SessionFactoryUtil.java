package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

    private static SessionFactory factory = null;


    static {
        Configuration cfg = new Configuration().configure();
        factory = cfg.buildSessionFactory();
    }

    public static SessionFactory getInstance() {
        return factory;
    }

    public static void close() {
        factory.close();
    }
}
