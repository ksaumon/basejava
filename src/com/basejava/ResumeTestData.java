package com.basejava;

import com.basejava.model.*;

import java.time.Month;
import java.util.List;

public class ResumeTestData {
    public static Resume createFilledResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.HOME_PHONE, "+7(345) 855-0482");
        resume.setContact(ContactType.MOBILE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "john.doe@example.com");
        resume.setContact(ContactType.MAIL, "john.doe@example.com");
        resume.setContact(ContactType.STATCKOVERFLOW, "john.doe@example.com");
        resume.setContact(ContactType.HOME_PAGE, "john.doe@example.com");
        resume.setContact(ContactType.LINKEDIN, "linkedin.com/in/johndoe");
        resume.setContact(ContactType.GITHUB, "github.com/johndoe");
        System.out.println();

        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения" +
                "по Java Web и Enterprise технологиям"));
        resume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика," +
                "креативность, инициативность. Пурист кода и архитектуры."));
        resume.setSection(SectionType.ACHIEVEMENT, new TextSection("Организация команды и успешная реализация" +
                "Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы," + "\n"
                + "система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2," +
                "многомодульный Spring Boot + Vaadin" + "\n" + "проект для комплексных DIY смет.\n" +
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный" +
                "maven. Многопоточность. XML (JAXB/StAX)." + "\n" + "Веб сервисы (JAX-RS/SOAP). Удаленное" +
                "взаимодействие(JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500" +
                "выпускников.\n Реализация двухфакторной аутентификации для онлайн платформы управления проектами" +
                "Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator," + "\n" + "Jira, Zendesk." +
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С," +
                "Bonita BPM, CMIS, LDAP." + "\n" + "Разработка приложения управления окружением на" +
                "стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP" +
                "модулей," + "\n" + "интеграция CIFS/SMB java сервера." +
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC," +
                "GWT, ExtGWT" + "\n" + "(GXT), Commet, HTML5, Highstock для алгоритмического трейдинга." +
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных" + "\n" + "сервисов" +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии" +
                "через систему мониторинга Nagios." + "\n" + "Реализация онлайн клиента для администрирования и" +
                "мониторинга системы по JMX (Jython/ Django)." + "Реализация протоколов по приему платежей всех" +
                "\n" + "основных платежных системы России(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии" +
                "(Erip, Osmp) и Никарагуа."));
        resume.setSection(SectionType.QUALIFICATIONS, new TextSection("JEE AS: GlassFish (v2.1, v3), OC4J," +
                "JBoss, Tomcat, Jetty, WebLogic, WSO2\n" +
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce\n" +
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite," +
                "MS SQL, HSQLDB\n" +
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy\n" +
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts\n" +
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring" +
                "(MVC, Security, Data, Clouds, Boot), " + "\n" + "JPA (Hibernate, EclipseLink), Guice," +
                "GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium" +
                "(htmlelements).\n" +
                "Python: Django.\n" +
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js\n" +
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka\n" +
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT," +
                "MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX," + "\n" + "Commet, HTML5, ESB, CMIS, BPMN2, LDAP," +
                "OAuth1, OAuth2, JWT.\n" +
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n" +
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport," +
                "OpenCmis, Bonita, pgBouncer\n" +
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных" +
                "шаблонов, UML, функционального программирования\n" +
                "Родной русский, английский "));

        resume.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        List.of(new Organization("Organization11", "http://Organization11.ru",
                                new Period(2005, Month.JANUARY, "position1", "content1"),
                                new Period(2001, Month.MARCH, 2005, Month.JANUARY,
                                        "position2", "content2")))));

        resume.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        List.of(new Organization("Institute", null,
                                        new Period(1996, Month.JANUARY, 2000, Month.DECEMBER,
                                                "aspirant", null), new Period(2001, Month.MARCH,
                                        2005, Month.JANUARY, "student", "IT facultet")),
                                new Organization("Organization12", "http://Organization12.ru"))));
        return resume;
    }
}
