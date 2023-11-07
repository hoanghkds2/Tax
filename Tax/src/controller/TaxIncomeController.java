package controller;

import model.User;
import repository.TaxIncomeRepository;


public class TaxIncomeController {
    TaxIncomeRepository repo;
    User user;
    
    public TaxIncomeController(){
        repo = new TaxIncomeRepository();
        user = new User();
    }
    
    public void run(){
        repo.taxIncome(user);
    }
}
