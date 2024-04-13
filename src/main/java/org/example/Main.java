package org.example;

import DataBase.AccountDaoImpl;
import UserApi.UserApi;
import model.BaseAccount;
import model.BusinessAccount;
import model.IndividualAccount;

import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args){
        UserApi userApi = new UserApi(new AccountDaoImpl());
        IndividualAccount individualAccount = new IndividualAccount(22,"Artem","Zaitsev");
        userApi.createAccount(individualAccount);
        BusinessAccount businessAccount = new BusinessAccount(22,124435);
        userApi.createAccount(businessAccount);

        System.out.println("List of all accounts:");
        List<BaseAccount> accounts = userApi.getAllAccounts();

        long accountid = 22;
        Optional<BaseAccount> account = userApi.getAccount(accountid);

        userApi.deleteAccount(accountid);

        List<BusinessAccount> businessAccounts = userApi.getAllAccounts().stream()
                .filter(a -> a instanceof BusinessAccount)
                .map(a -> (BusinessAccount) a)
                .toList();
        System.out.println("Information about all phys accounts:");
        businessAccounts.forEach(System.out::println);

    }
}
