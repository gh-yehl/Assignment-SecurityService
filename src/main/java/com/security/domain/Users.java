package com.security.domain;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "usertype", nullable = false)
    private String userType;

    @Column(name = "mobilenumber", nullable = false)
    private String mobileNumber;

    @Column(name = "confirmed")
    private String confirmed;

//    @OneToMany(mappedBy = "mentorId", cascade = CascadeType.DETACH)
//    private List<Trainings> trainingsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    //    public List<Trainings> getTrainingsList() {
//        return trainingsList;
//    }
//
//    public void setTrainingsList(List<Trainings> trainingsList) {
//        this.trainingsList = trainingsList;
//    }

}
