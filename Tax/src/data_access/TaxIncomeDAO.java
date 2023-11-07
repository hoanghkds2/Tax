package data_access;

import common.Validation;
import java.util.ArrayList;
import java.util.List;
import model.Child;
import model.Parent;
import model.User;

public class TaxIncomeDAO {

    private static TaxIncomeDAO instance = null;
    Validation valid = new Validation();

    public static TaxIncomeDAO Instance() {
        if (instance == null) {
            synchronized (TaxIncomeDAO.class) {
                if (instance == null) {
                    instance = new TaxIncomeDAO();
                }
            }
        }
        return instance;
    }

    private List<Parent> inputParentInfo() {
        String choice = "Y";
        List<Parent> parentList = new ArrayList<>();
        while (choice.equalsIgnoreCase("Y")) {
            Parent new_parent = new Parent();
            new_parent.setName(valid.inputString("Enter parent name"));
            new_parent.setAge(valid.inputInt("Enter parent age", 0, 122));
            new_parent.setSex(valid.inputInt("Enter parent gender(1.male 0.female)", 0, 1));
            parentList.add(new_parent);
            choice = valid.inputStringMatch("Do you still want to add parent(Y/N)", "[ynYN]");
        }
        return parentList;
    }

    private List<Child> inputChildInfo() {
        String choice = "Y";
        List<Child> childList = new ArrayList<>();
        while (choice.equalsIgnoreCase("Y")) {
            Child new_child = new Child();
            new_child.setName(valid.inputString("Enter child name"));
            new_child.setAge(valid.inputInt("Enter child age", 0, 122));
            new_child.setSex(valid.inputInt("Enter child gender(1.male 0.female)", 0, 1));
            new_child.setIsStudied(valid.inputBoolean("Is your child still studied(1.yes 0.no)"));
            childList.add(new_child);
            choice = valid.inputStringMatch("Do you still want to add Child(Y/N)", "[ynYN]");
        }
        return childList;
    }

    public void inputInformation(List<Parent> parentList, List<Child> childList, User user) {
        user.setName(valid.inputString("Enter Name"));
        user.setAge(valid.inputInt("Enter age", 0, 122));
        user.setSex(valid.inputInt("Enter gender(1.male 0.female)", 0, 1));
        user.setTotalIncome(valid.inputIncome("Enter income"));
        user.setIsFamily(valid.inputBoolean("Does this user have family?(1.yes 0.no)"));
        if (user.isIsFamily()) {
            user.setParentList(inputParentInfo());
            user.setHasSiblingRegister(valid.inputBoolean("Does parents register user sibling?(1.yes 0.no)"));
        }
        user.setHasChild(valid.inputBoolean("Does user have a child?(1.yes 0.no)"));
        if (user.isHasChild()) {
            user.setChildList(inputChildInfo());
        }
    }

    public double childDeduction(List<Child> childList, User user) {
        if (!user.isHasChild()) {
            return 0;
        }
        double max1 = 0;
        double max2 = 0;
        for (Child child : childList) {
            if (child.getAge() < 18) {
                if (max1 < 4400000) {
                    max1 = 4400000;
                    continue;
                }
                if (max2 < 4400000) {
                    max2 = 4400000;
                }
            } else {
                if (child.isIsStudied() && child.getAge() <= 22) {
                    if (max2 < 6000000) {
                        max2 = 6000000;
                        continue;
                    }
                    if (max1 < 6000000) {
                        max1 = 6000000;
                    }
                }
            }
        }
        return max1 + max2;
    }

    public double parentDeduction(List<Parent> parentList, User user) {
        if (user.isHasSiblingRegister() || !user.isIsFamily()) {
            return 0;
        }
        double deduction = 0;
        for (Parent parent : parentList) {
            if (parent.getSex() == 1) {
                if (parent.getAge() > 60) {
                    deduction += 4400000;
                }
            } else {
                if (parent.getAge() > 55) {
                    deduction += 4400000;
                }
            }
        }
        return deduction;
    }

    public double totalDeduction(double parentDeduction, double childDeduction) {
        return 11000000 + parentDeduction + childDeduction;
    }

    public double taxIncome(double total, double deduction) {
        if(total - deduction <= 0) return 0;
        return total - deduction;
    }

    public double totalTax(double taxIncome, User user) {
        if (taxIncome <= 0) {
            return 0;
        }
        if (taxIncome < 4000000) {
            return user.getTotalIncome() * 0.05;
        }
        if (taxIncome >= 4000000 && taxIncome < 6000000) {
            return user.getTotalIncome() * 0.08;
        }
        if (taxIncome >= 6000000 && taxIncome < 10000000) {
            return user.getTotalIncome() * 0.1;
        }
        return user.getTotalIncome() * 0.2;
    }
}
