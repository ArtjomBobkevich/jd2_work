//package by.itacademy.database.entity;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Table (name = "person", schema = "flea_market")
//@Data
//@NoArgsConstructor
//@EqualsAndHashCode(of = "id")
//public class Person {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String avatar;
//    private String login;
//    @Column(name = "first_name")
//    private String firstName;
//    @Column(name = "last_name")
//    private String lastName;
//    private String age;
//    private String mail;
//    private String password;
//    @Column(name = "role")
//    @ManyToOne
//    private PersonRole personRole;
//}
