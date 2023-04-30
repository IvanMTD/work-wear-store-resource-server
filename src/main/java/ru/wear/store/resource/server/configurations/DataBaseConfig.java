package ru.wear.store.resource.server.configurations;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import ru.wear.store.resource.server.models.Client;
import ru.wear.store.resource.server.repositories.ClientRepository;

import java.time.LocalDate;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class DataBaseConfig {
    @Bean
    public ConnectionFactory connectionFactory(){
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "h2")
                        .option(PROTOCOL,"file")
                        .option(USER, "admin")
                        .option(PASSWORD, "admin")
                        .option(DATABASE, "./src/main/resources/db/work-wear-db")
                        .build());
    }

    @Bean
    public CommandLineRunner preLoad(ClientRepository clientRepository, PasswordEncoder encoder, ConnectionFactory connectionFactory){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("Это системное сообщение, оповещает о внедрении внешних зависимостей");
                //createTable(connectionFactory);
                //saveClient(clientRepository,encoder);
            }
        };
    }

    private void createTable(ConnectionFactory connectionFactory){
        System.out.println("Создаю таблицу");
        /*Mono.from(connectionFactory.create()).flatMapMany(connection -> connection
                .createStatement("create table Clients (" +
                        "id long primary key auto_increment," +
                        "username varchar(20) unique not null," +
                        "password varchar(30) not null," +
                        "surname varchar(20) not null," +
                        "name varchar(30) not null," +
                        "middle_name varchar(30) not null," +
                        "gender varchar(10) not null," +
                        "birthdate date not null," +
                        "e_mail varchar not null," +
                        "phone_number varchar," +
                        "index int not null," +
                        "country varchar(20) not null," +
                        "state varchar(20) not null," +
                        "city varchar(20) not null," +
                        "street varchar(20) not null," +
                        "house_number int not null," +
                        "apartment_number int not null)")
                .execute()
        ).subscribe();*/

        /*Mono.from(connectionFactory.create()).flatMapMany(connection -> connection
                .createStatement("create table Products (" +
                        "id long primary key auto_increment," +
                        "name varchar(30) not null," +
                        "description text not null," +
                        "image blob," +
                        "size varchar(10) not null," +
                        "coast decimal not null," +
                        "discount int not null," +
                        "out_of_stock boolean not null)")
                .execute()
        ).subscribe();*/

        Mono.from(connectionFactory.create()).flatMapMany(connection -> connection
                .createStatement("create table ClientOrders (" +
                        "id long primary key auto_increment," +
                        "client_id long," +
                        "products_id long array)")
                .execute()
        ).subscribe();
    }

    private void saveClient(ClientRepository clientRepository, PasswordEncoder encoder){
        Client admin = new Client();
        admin.setUsername("Morgan");
        admin.setPassword(encoder.encode("Admin_1234"));
        admin.setRole("ROLE_Admin");
        admin.setSurname("Карачков");
        admin.setName("Иван");
        admin.setMiddleName("Сергеевич");
        admin.setGender(Client.Gender.MALE);
        admin.setBirthdate(LocalDate.of(1985,10,30));
        admin.setEMail("ivan.se.karachkov@gmail.com");
        admin.setPhoneNumber("+79168792043");
        admin.setIndex(123592);
        admin.setCountry("Россия");
        admin.setState("Москва");
        admin.setCity("Москва");
        admin.setStreet("Строгинский бульвар");
        admin.setHouseNumber(23);
        admin.setApartmentNumber(89);

        Mono<Client> clientMono = clientRepository.save(admin);
        clientMono.doOnNext(c -> System.out.println(c.getUsername())).subscribe();
    }
}
